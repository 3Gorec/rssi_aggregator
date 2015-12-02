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
    
    AggregatorTimerTask(main_form form, RSSIAggregator aggregator){
        this.aggregator=aggregator;
        this.form=form;            
    }
    
    @Override
    public void run() {
        form.decIntCounter();                    
        
        int status=aggregator.AggregateData();
        switch(status){
            case 0:
                //Everything is ok
                break;
            case 1:
                form.resetAggregatorState();
                form.outputStatus("Data error");
                break;
            case 2:
                form.resetAggregatorState();
                form.outputStatus("Too much pending period: sniffer buffer overflowed");
                break;
                
        }
        
    }
    
    main_form form;
    RSSIAggregator aggregator;
}
