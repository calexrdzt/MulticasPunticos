package com.example.estudiante.multicast;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Comunicacion extends Thread {

    private boolean conectado;
    MulticastSocket socket;
    InetAddress IPgrupo;
    int puerto=5000;
    public String msj;

    public  Comunicacion(){

        conectado=false;
    }

    @Override
    public void run() {

        try {
            while (true){
                if(!conectado) {
                    IPgrupo = InetAddress.getByName("228.0.0.19");
                    socket = new MulticastSocket(puerto);
                    socket.joinGroup(IPgrupo);
                    conectado=true;
                }else {
                    recibirDatos();
                }
        }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void recibirDatos() {
        try {
            while (true) {
                byte[] buff = new byte[1000];
                DatagramPacket datagrama = new DatagramPacket(buff, buff.length, IPgrupo, puerto);
                socket.receive(datagrama);
                msj = new String(datagrama.getData());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void enviarDatos(String dato) {
        try {
            byte[] buff= dato.getBytes();
            DatagramPacket datagrama= new DatagramPacket(buff,buff.length);
            socket.send(datagrama);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
