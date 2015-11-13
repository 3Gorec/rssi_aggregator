/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    int rssi_ds_count;
    File out_file;
    PrintWriter writer;
    int cur_tick_index; //timestamp of current second tick
    AggregatedRSSIData aggregated_data;
    
    RSSIAggregator(main_form form){
        sniffer_controller=new SnifferController(form,1,"AP_1");
        running_flag=false;
        this.form=form;
        int ar[]=new int[10];       
        timer=new Timer(false);
        cur_tick_index=0;
        
    }
    
    public int AggregateData(){
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
            sniffer_controller.IncRecvdDataCounter();
            ProcessData(data);
            
        }
        if(sniffer_controller.GetRecvdDataCounter()==rssi_ds_count){
            FinishAgregate();            
        }
        return 0;
    }
    
    public void FinishAgregate(){
        
        form.resetAggregatorState();
        form.outputStatus("Sniffing finished succesfully");
    } 
    
    private int ProcessData(SnifferResponse data){
        aggregated_data.tick_interval_s
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
            cur_tick_index=0;
            rssi_ds_count=sniffing_interval_s/pending_period_s;
            aggregated_data=new AggregatedRSSIData(sniffing_interval_s,pending_period_s);

            
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
    
    
}
