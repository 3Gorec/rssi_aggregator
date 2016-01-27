package rssi_data;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gorec
 */
public class TickRSSIData{
    public long timestamp;
    public ArrayList<RSSIData> rssi_data;                

    public TickRSSIData(long timestamp){
        rssi_data=new ArrayList<>();            
        this.timestamp=timestamp;
    }
}
