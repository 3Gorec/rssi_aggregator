/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import sniffer_prot.prot_buf.*;


/**
 *
 * @author gorec
 */
public class RSSIAggregator {
    Boolean running_flag;
    main_form form;
    Timer timer;     
    TimerTask pendingTask;
    ArrayList<SnifferController> sniffers;
    int aggregate_call_counter;           
    int total_tick_count;  
    boolean sniffing_in_process;
    Mac mac_filter;
    File out_file;
    PrintWriter writer;    
    AggregatedRSSIData aggregated_data;        
    
    
    RSSIAggregator(main_form form){
        sniffers = new ArrayList<>();
        sniffers.add(new SnifferController(form,1,"AP_1"));        
        running_flag=false;
        this.form=form;
        int ar[]=new int[10];       
        timer=new Timer(false);
        aggregate_call_counter=0;
        total_tick_count=0;         
        sniffing_in_process=false;    
}   
    
    public int AggregateData(){        
        aggregated_data.tick_rssi_data.add(new TickRSSIData(new Date().getTime()));        
        
        for(int i=0;i<sniffers.size();i++){
            SnifferController sniffer=sniffers.get(i);
            SnifferResponse data=sniffer.GetData(sniffer.last_record_id);        
            if(data==null){
                return 1;
            }
            if(data.getInterrupted()==true){
               return 2;
            }                       
            
            ProcessData(data, sniffer);
        }
        
        aggregate_call_counter++;
        if(aggregate_call_counter==total_tick_count){
            FinishAgregate();            
        }
        return 0;
    }  
    
    private void InitStartRecordId(){
        for(int i=0;i<sniffers.size();i++){
            SnifferResponse data=sniffers.get(i).GetData(0);    //flush sniffer buffers
            int count=data.getRssiDataCount();
            if(count>0){
                sniffers.get(i).last_record_id=data.getRssiData(count-1).getId();
            }
            else{
                sniffers.get(i).last_record_id=0;
            }
        }
    }
    
    
    public int Start(int sniffing_interval_s, int pending_period_s, String out_file_name, Mac mac_filter){
        try{            
            this.mac_filter=mac_filter;
            out_file=new File(out_file_name);
            writer=new PrintWriter(out_file);                        
            
            for(int i=0;i<sniffers.size();i++){
                sniffers.get(i).ResetSniffer();
                if(sniffers.get(i).SnifferConnect()!=0){
                    return 1;
                }
            }   
            
            total_tick_count=sniffing_interval_s/pending_period_s;
            aggregate_call_counter=0;            
            aggregated_data=new AggregatedRSSIData(sniffing_interval_s,pending_period_s);
            InitStartRecordId();            
            pendingTask=new AggregatorTimerTask(form, this);                  
            timer.scheduleAtFixedRate(pendingTask, pending_period_s*1000, pending_period_s*1000);                
            running_flag=true;
            form.outputStatus("Sniffing in progress");
            return 0;
        }
        
        catch (FileNotFoundException e){
            form.outputStatus("File not found");            
            return 2;
        } 
        
    }
    
    public int Stop(){
        int status=0;        
        running_flag=false;        
        pendingTask.cancel();
        writer.close();
        return status;        
    }
    
        
    void CopyRecords(RSSIData sniffer_data, SnifferResponse data, int start_index, int record_cnt){          
        int i=start_index;
        while(i<record_cnt){
            SnifferResponse.RSSIRecord pb_rssi_record=data.getRssiData(i);    
            MACValueRecord new_record=new MACValueRecord(new Mac(pb_rssi_record.getMac().toByteArray()), pb_rssi_record.getRssi());
            if(FilterDataByMAC(new_record.mac)){
                sniffer_data.records.add(new_record);
            }   
            i++;
        }
        int tick_index=aggregated_data.tick_rssi_data.size()-1;
        aggregated_data.tick_rssi_data.get(tick_index).rssi_data.add(sniffer_data);      
        
    }    
    
    private int ProcessData(SnifferResponse data, SnifferController sniffer){      
        int record_cnt=data.getRssiDataCount();
        RSSIData sniffer_data;
        System.out.println("Tick"); //todo remove        
        
        if(sniffer.last_record_id!=0 && record_cnt>1){
            sniffer_data=new RSSIData(sniffer.id, sniffer.name);     
            CopyRecords(sniffer_data,data,1,record_cnt-1);
            sniffer.last_record_id=data.getRssiData(record_cnt-1).getId();
        }
        else{
            if(sniffer.last_record_id==0 && record_cnt>0){
                sniffer_data=new RSSIData(sniffer.id, sniffer.name);     
                CopyRecords(sniffer_data,data,0,record_cnt);
                sniffer.last_record_id=data.getRssiData(record_cnt-1).getId();
            }
        }
                
        return 0;
    }    

    
    
    private boolean FilterDataByMAC(Mac mac){
        if(mac_filter==null){
            return true;
        }
        return mac.equals(mac_filter);
    }
    
    public void FinishAgregate(){        
        writer.print(aggregated_data.seializeData());
        
        form.resetAggregatorState();
        form.outputStatus("Sniffing finished succesfully");
    } 
    
}


