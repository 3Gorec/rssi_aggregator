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
        if(odd==1){
        form.decIntCounter();        
            odd=0;
        }
        else{            
            Date date=new Date();
            aggregator.cur_tick_ts=date.getTime();
            aggregator.aggregated_data.tick_rssi_data.add(new TickRSSIData(aggregator.cur_tick_ts));
            odd=1;
        }                
       
        if(aggregator.AggregateData()!=0){
            form.resetAggregatorState();
            form.outputStatus("Sniffing error");
        }
    }
    
    main_form form;
    RSSIAggregator aggregator;
}
