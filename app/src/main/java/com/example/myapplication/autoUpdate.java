package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import com.example.myapplication.MainActivity;

public class autoUpdate extends Service {
    public autoUpdate() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
     /*   new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/
        MainActivity m=new MainActivity();
        m.letsDoSomeNetworking();
        AlarmManager manager=(AlarmManager) getSystemService(ALARM_SERVICE);
        int t=60*60*1000;
        long triggerAtTime = SystemClock.elapsedRealtime()+t;
        Intent i=new Intent(this,autoUpdate.class);
        PendingIntent pi=PendingIntent.getService(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent,flags,startId);
    }

}
