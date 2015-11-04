/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_sniffer_client;

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
    }
    
    main_form form;
    RSSIAggregator aggregator;
}
