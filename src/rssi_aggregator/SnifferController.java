/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;
import java.io.*;
import java.net.*;
import java.util.TimerTask;
import rssi_aggregator.prot_buf.*;

/**
 *
 * @author gorec
 */
public class SnifferController {
    class Timestamp{                
        public long ts_sec;
        public long ts_usec;
        Timestamp(){
            ts_sec=0;
            ts_usec=0;
        }
        
        Timestamp(long sec, long usec){
            ts_sec=sec;
            ts_usec=usec;
        }
        
        public void SetTs(long sec, long usec){
            ts_sec=sec;
            ts_usec=usec;
        }
        
        public long GetTsSec(){
            return ts_sec;
        }
        
        public long GetTsUsec(){
            return ts_usec;
        }
    }
    
    String address;
    int serverPort;
    int read_data_period;
    TimerTask pendingTask;
    main_form form;
    public Timestamp last_ts;
    private int recvd_data_counter;
    int id;
    String name;
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    SnifferController(main_form form,int sniffer_id, String sniffer_name){
        address="127.0.0.1";
        serverPort=7989;
        this.form=form;
        last_ts=new Timestamp(0,0);
        recvd_data_counter=0;
        read_data_period=0;
        id=sniffer_id;
        name=sniffer_name;
    }
    
    public void IncRecvdDataCounter(){
        recvd_data_counter++;
    }
    
    public void ResetSniffer(){
        read_data_period=0;
        recvd_data_counter=0;
        last_ts.SetTs(0, 0);
    }
    
    public int GetRecvdDataCounter(){
        return recvd_data_counter;
    }
    public SnifferResponse GetData(){
        try{
            InetAddress ipAddress=InetAddress.getByName(address);                       
            SnifferResponse response;
            SnifferQuery query;
            query=SnifferQuery.newBuilder()
                .setType(QueryType.DATA_REQUEST)
                .build();
            response = SendSnifferQuery(form, ipAddress,query);             
            return response;
        }
        catch (UnknownHostException e){
            form.outputStatus("Don't know about host " + address );            
            return null;
        }
    }
    
    public int SnifferConnect(){
        try{
        InetAddress ipAddress=InetAddress.getByName(address);
        SnifferQuery query;        
        SnifferResponse response;
        query=SnifferQuery.newBuilder()
                .setType(QueryType.STATUS_REQUEST)
                .build();
        response = SendSnifferQuery(form, ipAddress,query);
        if(response==null){
            form.outputStatus("Connecting error: " + address+":"+ String.valueOf(serverPort));
            return -1;
        }
        switch(response.getStatus()){
           case SNIFFING_RUN:
               read_data_period=response.getAccumPeriod();
               form.setSnifferStatus("Connected");               
               break;
           case SNIFFING_STOPED:
               form.setSnifferStatus("Error");
               return -2;
           default:
               break;
        }                        
        
        return read_data_period;
        }
        catch (UnknownHostException e){
            form.outputStatus("Don't know about host " + address);            
            return -3;
        } 
    }
   
    private SnifferResponse SendSnifferQuery(main_form form, InetAddress ipAddress, SnifferQuery query){
        try{
            Socket socket = new Socket(ipAddress, serverPort);            
            DataInputStream sin = new DataInputStream(socket.getInputStream());
            DataOutputStream sout = new DataOutputStream(socket.getOutputStream());
            int response_size;

            sout.writeInt(query.getSerializedSize());
            query.writeTo(sout);

            response_size=sin.readInt();            
            byte[] buffer=new byte[response_size]; 
            sin.readFully(buffer, 0, response_size);
            SnifferResponse response=SnifferResponse.parseFrom(buffer);            
            sout.close();
            sin.close();
            socket.close();        
            return response;
        }       
        catch (IOException e){
            form.outputStatus("Couldn't get I/O for the connection to " + address);                        
            return null;
        }
    }    
}
