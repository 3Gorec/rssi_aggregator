/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;
import java.lang.String;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import rssi_aggregator.prot_buf.*;

/**
 *
 * @author gorec
 */
public class SnifferController {
    String address;
    int serverPort;
    int read_data_period;
    TimerTask pendingTask;
    main_form form;
    /**
     * @param args the command line arguments
     */
    SnifferController(main_form form){
        address="127.0.0.1";
        serverPort=7989;
        this.form=form;
    }
    
    public int GetData(Date timestamp){
        try{
            InetAddress ipAddress=InetAddress.getByName(address);            
            File out_file=new File("output_"+timestamp.getTime()/1000+".txt");
            PrintWriter writer=new PrintWriter(out_file);
            SnifferResponse response;
            SnifferQuery query;
            query=SnifferQuery.newBuilder()
                .setType(QueryType.DATA_REQUEST)
                .build();
            response = SendSnifferQuery(form, ipAddress,query);
            writer.printf("***Received message***\n%s",response.toString());
            writer.close();          
            return 0;
        }
        catch (UnknownHostException e){
            form.outputStatus("Don't know about host " + address );            
            return 2;
        }
        catch (FileNotFoundException e){
            form.outputStatus("File not found");            
            return 3;
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
               form.setSnifferStatus("connected");               
               break;
           case SNIFFING_STOPED:
               form.setSnifferStatus("error");
               break;
           default:
               break;
        }        
        
        pendingTask=new PendingTask(this);
        Timer timer=new Timer(false);
        timer.scheduleAtFixedRate(pendingTask, 0, read_data_period*1000);
        form.outputStatus("Connecting connected: " + address);
        return read_data_period;
        }
        catch (UnknownHostException e){
            form.outputStatus("Don't know about host " + address);            
            return -2;
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
