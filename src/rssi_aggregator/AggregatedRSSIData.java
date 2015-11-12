/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;

import java.util.ArrayList;

/**
 *
 * @author gorec
 */
public class AggregatedRSSIData {
    public class TickRSSIData{
        long timestamp;
        ArrayList<RSSIData> rssi_data;                
        
        public TickRSSIData(){
            
        }
    }
    
    int aggregate_time_s;
    int tick_interval_s;
    ArrayList<TickRSSIData> tick_rssi_data;
    
}
