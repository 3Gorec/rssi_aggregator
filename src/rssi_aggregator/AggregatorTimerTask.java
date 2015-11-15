/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;

import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author gorec
 */
public class AggregatorTimerTask extends TimerTask{
    int odd=0;  //for decrement second counter each second run;
    
    
    AggregatorTimerTask(main_form form, RSSIAggregator aggregator){
        this.aggregator=aggregator;
        this.form=form;
    }
    
    @Override
    public void run() {
        boolean new_tick_flag=false;
        if(odd==0){
            new_tick_flag=true;            
            odd=1;
        }                  
        else{
            form.decIntCounter();        
            odd=0;
        }              
       
        if(aggregator.AggregateData(new_tick_flag)!=0){
            form.resetAggregatorState();
            form.outputStatus("Sniffing error");
        }
    }
    
    main_form form;
    RSSIAggregator aggregator;
}
