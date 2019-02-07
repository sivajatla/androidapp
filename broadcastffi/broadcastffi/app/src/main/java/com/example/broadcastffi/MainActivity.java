package com.example.broadcastffi;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //IntentFilter intentFilter;
    //PhonecallReceiver broadcastReceiver;
    PhoneCallReceiverManager phoneCallReceiverManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*intentFilter =  new IntentFilter();
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        broadcastReceiver =  new PhonecallReceiver();*/
        setContentView(R.layout.activity_main);
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

        phoneCallReceiverManager = new PhoneCallReceiverManager(getApplicationContext());
        registerReceiver(phoneCallReceiverManager.getBroadcastReceiver(),phoneCallReceiverManager.getIntentFilter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast toast = Toast.makeText(getApplicationContext(),
                "MainActivity onResume::phonestate::"+phoneCallReceiverManager.getPhoneState(),
                Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("phonecallbroadcasttest","onStop");
        //unregisterReceiver(broadcastReceiver);
    }
}
