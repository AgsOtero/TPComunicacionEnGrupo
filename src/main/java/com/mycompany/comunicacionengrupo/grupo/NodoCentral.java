/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comunicacionengrupo.grupo;

import static com.mycompany.comunicacionengrupo.UDPMulticastServer.sendUDPMessage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Agustin
 */
public class NodoCentral {
    
     public static void sendUDPMessage(String message,
        String ipAddress, int port) throws IOException {
      DatagramSocket socket = new DatagramSocket();
      InetAddress group = InetAddress.getByName(ipAddress);
      byte[] msg = message.getBytes();
      DatagramPacket packet = new DatagramPacket(msg, msg.length,
         group, port);
      socket.send(packet);
      socket.close();
   }

   public static void main(String[] args) throws IOException {
      sendUDPMessage("Enviando mensaje al grupo 230.0.0.0", "230.0.0.0",
         4321);     
      sendUDPMessage("OK", "230.0.0.0", 4321);
   }
}
    

