/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comunicacionengrupo.grupo;

import com.mycompany.comunicacionengrupo.UDPMulticastClient;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Agustin
 */
public class Nodo2 implements Runnable {
    public static void main(String[] args) {
      Thread t=new Thread(new UDPMulticastClient());
      t.start();
   }

   public void receiveUDPMessage(String ip, int port) throws
         IOException {
      byte[] buffer=new byte[1024];
      MulticastSocket socket=new MulticastSocket(4322);
      InetAddress group=InetAddress.getByName("230.0.0.0");
      socket.joinGroup(group);
      while(true){
         System.out.println("Esperando por un mensaje multicast...");
         DatagramPacket packet=new DatagramPacket(buffer,
            buffer.length);
         socket.receive(packet);
         String msg=new String(packet.getData(),
         packet.getOffset(),packet.getLength());
         System.out.println("Mensaje UDP recibido!---->" +msg);
         if("OK".equals(msg)) {
            System.out.println("Ya no quedan mensajes, saliendo : "+msg);
            break;
         }
      }
      socket.leaveGroup(group);
      socket.close();
   }

   @Override
   public void run(){
   try {
      receiveUDPMessage("230.0.0.0", 4321);
   }catch(IOException ex){
   }
}
}