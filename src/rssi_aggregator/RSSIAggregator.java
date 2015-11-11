/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author gorec
 */
public class RSSIAggregator {
    Boolean running_flag;
    main_form form;
    Timer timer;     
    TimerTask pendingTask;
    
    RSSIAggregator(main_form form){
        running_flag=false;
        this.form=form;
        timer=new Timer(false);
    }
    
    public int Start(){
        int status=0;
        pendingTask=new AggregatorTimerTask(form, this);        
        timer.scheduleAtFixedRate(pendingTask, 0, 1000);                
        running_flag=true;
        
        return status;
    }
    
    public int Stop(){
        int status=0;        
        running_flag=false;
        pendingTask.cancel();
        return status;        
    }
    
    
}
