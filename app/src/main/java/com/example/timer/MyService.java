package com.example.timer;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class MyService extends Service {
    static Boolean flag = false;
    private int h=0, m=0, s=0;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public int onStartCommand(Intent intent, final int flags, int startID){
        flag = intent.getBooleanExtra("flag",false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    s++;
                    if(s>=60){
                        s=0;
                        m++;
                        if(m>=60){
                            m=0;
                            h++;
                        }
                    }
                    Intent intent = new Intent("MyMessage");
                    Bundle bundle = new Bundle();
                    bundle.putInt("H",h);
                    bundle.putInt("M",m);
                    bundle.putInt("S",s);
                    intent.putExtras(bundle);

                    sendBroadcast(intent);
                }
            }
        }).start();
        return Service.START_STICKY;
    }
}
