/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comunicacionengrupo.grupo;

import com.mycompany.comunicacionengrupo.UDPMulticastClient;
import static com.mycompany.comunicacionengrupo.grupo.NodoCentral.sendUDPMessage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Agustin
 */
public class Nodo1 implements Runnable {
    public static void main(String[] args) throws IOException {
    //Asi como esta, el Nodo no envia nada, solo espera que se ejecute el NodoCentral que es el encargado de mandar el mensaje desde afuera al grupo.
        Thread t=new Thread(new UDPMulticastClient());
      t.start();
//  Para que se envie un mensaje interno desde un nodo perteneciente al grupo, hay que descomentar esta linea, correr los Nodos 2 y 3, finalmente correr el Nodo 1 que es el que va a enviar el mesnsaje      
//      sendUDPMessage("Este es un mensaje interno al grupo 230.0.0.0", "230.0.0.0",
//         4321);     
//      sendUDPMessage("OK", "230.0.0.0", 4321);
//      
      
   }
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
    
    

   public void receiveUDPMessage(String ip, int port) throws
         IOException {
      byte[] buffer=new byte[1024];
      MulticastSocket socket=new MulticastSocket(4321);
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
    
