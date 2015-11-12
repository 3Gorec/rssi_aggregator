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
    
    public class MACValueRecord{
        public String MAC;
        public int RSSI;
        public MACValueRecord(){
            MAC="00:00:00:00:00:00";
            RSSI=0;
        }
    }
    
    int snifferId;
    String SnifferName;
    ArrayList<MACValueRecord>  records;
    
    public RSSIData(){
        records=new ArrayList<MACValueRecord>();
    }
}

