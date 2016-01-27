/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_data;


/**
 *
 * @author gorec
 */
public class MACValueRecord{
    public Mac mac;
    public int rssi;
    public MACValueRecord(Mac mac, int rssi){
        this.mac=mac;
        this.rssi=rssi;
    }
}
