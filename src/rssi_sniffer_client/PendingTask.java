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
public class PendingTask extends TimerTask{    
    PendingTask(RSSI_SnifferController sniffer_ctrl){
        this.sniffer_ctrl=sniffer_ctrl;
    }
    
    @Override
    public void run() {
        sniffer_ctrl.GetData(new Date());
    }
    
    RSSI_SnifferController sniffer_ctrl;
}
