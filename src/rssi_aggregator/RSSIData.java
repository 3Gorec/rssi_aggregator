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
public class RSSIData {
    
    int snifferId;
    String SnifferName;
    ArrayList<MACValueRecord>  records;
    
    public RSSIData(int id, String name){
        records=new ArrayList<MACValueRecord>();
        snifferId=id;
        SnifferName=name;
    }
}

