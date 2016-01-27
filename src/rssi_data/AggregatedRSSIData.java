/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
/**
 *
 * @author gorec
 */




public class AggregatedRSSIData {
    
    public int aggregate_time_s;
    public int tick_interval_s;
    public ArrayList<TickRSSIData> tick_rssi_data;

    public AggregatedRSSIData(int aggregate_time_s, int tick_interval_s) {
        tick_rssi_data=new ArrayList<>();
        this.aggregate_time_s=aggregate_time_s;
        this.tick_interval_s=tick_interval_s;
    }
    
    public String seializeData(){
        Gson gson=new GsonBuilder()
                        .setPrettyPrinting()
			.create();
        return gson.toJson(this);        
    }
    
}
