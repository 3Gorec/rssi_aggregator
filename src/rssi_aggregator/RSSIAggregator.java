/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import rssi_aggregator.prot_buf.*;


/**
 *
 * @author gorec
 */
public class RSSIAggregator {
    Boolean running_flag;
    main_form form;
    Timer timer;     
    TimerTask pendingTask;
    SnifferController sniffer_controller;
    int aggregate_call_counter;
    int rssi_ds_count;    
    File out_file;
    PrintWriter writer;    
    AggregatedRSSIData aggregated_data;    
    TickRSSIData cur_tick_data;
    
    
    RSSIAggregator(main_form form){
        sniffer_controller=new SnifferController(form,1,"AP_1");
        running_flag=false;
        this.form=form;
        int ar[]=new int[10];       
        timer=new Timer(false);
        aggregate_call_counter=0;
}
    
    public int AggregateData(boolean new_tick_flag){
        if(new_tick_flag){                                                   
            if(cur_tick_data!=null){
                aggregated_data.tick_rssi_data.add(cur_tick_data);
            }
            cur_tick_data=new TickRSSIData((new Date()).getTime());
        }
        
        SnifferResponse data=sniffer_controller.GetData();        
        if(data==null){
            return 1;
        }
        if(data.getValid()==false){
           return 1;
        }
        long ts_sec=data.getTs().getTvSec();
        long ts_usec=data.getTs().getTvUsec();
        if(ts_sec!=sniffer_controller.last_ts.GetTsSec() || 
                ts_usec!=sniffer_controller.last_ts.GetTsUsec()){
            sniffer_controller.last_ts.SetTs(ts_sec, ts_usec);                        
            ProcessData(data, sniffer_controller.id, sniffer_controller.name);
            
        }
        aggregate_call_counter++;
        if(aggregate_call_counter==2*rssi_ds_count){
            FinishAgregate();            
        }
        return 0;
    }  
    
    
    
    public int Start(int sniffing_interval_s){
        try{            
            out_file=new File("output.txt");
            writer=new PrintWriter(out_file);
            
            
            int pending_period_s=sniffer_controller.SnifferConnect();
            if(pending_period_s<=0){        //todo add multi sniffer support
                IllegalArgumentException e=new IllegalArgumentException();
                throw e;
            }
            sniffer_controller.ResetSniffer();            
            rssi_ds_count=sniffing_interval_s/pending_period_s;
            aggregated_data=new AggregatedRSSIData(sniffing_interval_s,pending_period_s);
            cur_tick_data=null;
            aggregate_call_counter=0;
            
            pendingTask=new AggregatorTimerTask(form, this);        
            timer.scheduleAtFixedRate(pendingTask, 0, 500*pending_period_s);                
            running_flag=true;
            form.outputStatus("Sniffing in progress");
            return 0;
        }
        catch (IllegalArgumentException e){
            return 1;
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
    
    private int ProcessData(SnifferResponse data, int sniffer_id, String sniffer_name){
        int record_cnt=data.getRssiDataCount();
        RSSIData sniffer_data=new RSSIData(sniffer_id, sniffer_name);        
        for(int i=0;i<record_cnt;i++){
            SnifferResponse.RSSIRecord record=data.getRssiData(i); 
            if(FilterDataByMAC(record)){
                sniffer_data.records.add(new MACValueRecord(record.getMac().toString(), record.getRssi()));
            }                
        }
        cur_tick_data.rssi_data.add(sniffer_data);        
        return 0;
    }    
    
    private boolean FilterDataByMAC(SnifferResponse.RSSIRecord record){
        return true;
    }
    
    public void FinishAgregate(){        
        int tick_count=aggregated_data.tick_rssi_data.size();
        if(tick_count>0){
            if(aggregated_data.tick_rssi_data.get(tick_count-1).timestamp!=cur_tick_data.timestamp){
                aggregated_data.tick_rssi_data.add(cur_tick_data);
            }
        }
        else{
            aggregated_data.tick_rssi_data.add(cur_tick_data);
        }
        
        Gson gson=new Gson();
        String json=gson.toString();
        System.out.println(aggregated_data.tick_rssi_data.get(0).rssi_data.size());
        writer.print(json);
        
        
        form.resetAggregatorState();
        form.outputStatus("Sniffing finished succesfully");
    } 
    
}


