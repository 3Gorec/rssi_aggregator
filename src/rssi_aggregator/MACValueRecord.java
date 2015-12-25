/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;

import com.google.protobuf.ByteString;

/**
 *
 * @author gorec
 */
public class MACValueRecord{
    public ByteString mac;
    public int rssi;
    public MACValueRecord(ByteString mac, int rssi){
        this.mac=mac;
        this.rssi=rssi;
    }
}
