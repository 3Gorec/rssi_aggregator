/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;

/**
 *
 * @author gorec
 */
public class MACValueRecord{
    public String mac;
    public int rssi;
    public MACValueRecord(String mac, int rssi){
        this.mac=mac;
        this.rssi=rssi;
    }
}
