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
    int odd;  //for decrement second counter each second run;
    boolean first_tick;
    
    AggregatorTimerTask(main_form form, RSSIAggregator aggregator){
        this.aggregator=aggregator;
        this.form=form;
        first_tick=true;
        odd=0;
    }
    
    @Override
    public void run() {
        boolean new_tick_flag=false;        
        if(odd==0){
            new_tick_flag=true;               
            odd=1;            
        }                  
        else{
            if(first_tick){     //workaround situation when first two ticks aggreagte data from different intervals(i want only one data for 2 ticks)
                first_tick=false;
                new_tick_flag=true;               
            }
            form.decIntCounter();        
            odd=0;
        }                     
        
        if(first_tick==false){
            if(aggregator.AggregateData(new_tick_flag)!=0){
                form.resetAggregatorState();
                form.outputStatus("Sniffing error");
            }
        }
    }
    
    main_form form;
    RSSIAggregator aggregator;
}
