package com.example.estudiante.multicast;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    DrawView lienzo;
    Comunicacion com;
    String d;
    String[] pos;
    int x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pos=new String[2];

        com =new Comunicacion();
        com.start();

        //HABILITAR MULTICAST
        WifiManager wm= (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiManager.MulticastLock multicastLock=wm.createMulticastLock( "mydebuginfo");
        multicastLock.acquire();

        lienzo = findViewById(R.id.lienzo);

        lienzo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                pos= com.msj.split(";");
                x= Integer.parseInt(pos[0]);
                y= Integer.parseInt(pos[1]);

                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN:

                        break;

                    case MotionEvent.ACTION_MOVE:
                        break;

                    case MotionEvent.ACTION_UP:

                       /* lienzo.addPoint(motionEvent.getX(), motionEvent.getY()); */
                        lienzo.addPoint(x, y);
                        d = motionEvent.getX()+"-"+motionEvent.getY();
                        com.enviarDatos(d);


                        break;
                }

                return true;
            }
        });

    }
}
