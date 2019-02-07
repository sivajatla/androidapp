package com.example.broadcastffi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityUpiBraoadCastSecurity extends AppCompatActivity {
    private PhoneCallReceiverManager phoneCallReceiverManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},1);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},2);
        }
        //phoneCallReceiverManager = new PhoneCallReceiverManager(getApplicationContext());
        //registerReceiver(phoneCallReceiverManager.getBroadcastReceiver(),phoneCallReceiverManager.getIntentFilter());
    }
}
