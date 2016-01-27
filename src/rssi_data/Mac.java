/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_data;

import java.util.Arrays;


/**
 *
 * @author gorec
 */
public class Mac{    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Arrays.hashCode(this.mac_addr);
        return hash;
    }

    @Override
    public String toString() {
        String s="";
        for(int i=0;i<mac_addr.length;i++){
            s=s.concat(String.format("%02x",mac_addr[i]));
            if(i<mac_addr.length-1){
                s=s.concat(":");
            }
        }
        return s.toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mac other = (Mac) obj;
        if (!Arrays.equals(this.mac_addr, other.mac_addr)) {
            return false;
        }
        return true;
    }

    
    public Mac(String mac) throws NullPointerException, IllegalArgumentException{
        if(mac==null){
            throw new NullPointerException();
        }
               
        mac=mac.replaceAll("\\:", "");
        System.out.println(mac);
        if(mac.length()!=12){
            throw new IllegalArgumentException("Mac addr invalid lenght");
        }        
        
        byte[] mac_byte=mac.getBytes();
        for(int i=0;i<mac_byte.length;i++){
            if(!isHexChar(mac_byte[i])){
                throw new IllegalArgumentException("Mac addr invalid symbol - "+Integer.toString(i));
            }            
        }
        
        for(int i=0;i<6;i++){                       
            mac_addr[i]=(byte) Integer.parseInt(mac.substring(i*2, i*2+2), 16);                        
        }
    }
    
    public Mac(byte[] mac){
        if(mac==null){
            throw new NullPointerException();   
        }
        
        if(mac.length!=6){
            throw new IllegalArgumentException("Mac addr invalid lenght");
        }
        
        System.arraycopy(mac, 0, mac_addr, 0, mac.length);
    }       
    
    private boolean isHexChar(byte ch){
        if(ch>='0' && ch<='9'){
            return true;
        }
        if(ch>='A' && ch<='F'){
            return true;
        }
        if(ch>='a' && ch <='f'){
            return true;
        }

        return false;
    }
    
    
    private byte[] mac_addr={0,0,0,0,0,0};        
}
