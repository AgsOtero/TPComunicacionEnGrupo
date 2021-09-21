package com.mycompany.comunicacionengrupo;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMulticastServer {

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
      sendUDPMessage("Este es el primer mensaje, a continuacion se enviaran 2 mensajes mas.", "230.0.0.0",
         4321);
      sendUDPMessage("Este es el primer mensaje, a continuacion se un mensaje mas.",
         "230.0.0.0", 4321);
      sendUDPMessage("Este es el ultimo mensaje enviado.",
         "230.0.0.0", 4321);
      sendUDPMessage("OK", "230.0.0.0", 4321);
   }
}