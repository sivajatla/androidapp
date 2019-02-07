package com.indusind.upisecurity.broadcast;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

import com.example.broadcastffi.PhonecallReceiver;
import com.konylabs.android.KonyMain;
import com.konylabs.vm.Function;

public class PhoneCallStateReceiverManager {

    private static final String TAG = "PhoneCallReceiverManager";
    private Function callBackFunction = null;
    private static final String konyTag = "KonyFFI";
    private Context context = null;

    public IntentFilter getIntentFilter() {
        return intentFilter;
    }

    public void setIntentFilter(IntentFilter intentFilter) {
        this.intentFilter = intentFilter;
    }

    public PhonecallReceiver getBroadcastReceiver() {
        return broadcastReceiver;
    }

    public void setBroadcastReceiver(PhonecallReceiver broadcastReceiver) {
        this.broadcastReceiver = broadcastReceiver;
    }

    private IntentFilter intentFilter;
    private PhonecallReceiver broadcastReceiver;

    public PhoneCallStateReceiverManager(){
        this.context = KonyMain.getActContext();
        this.intentFilter =  new IntentFilter();
        this.intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        this.intentFilter.addAction("android.intent.action.PHONE_STATE");
        this.broadcastReceiver =  new PhonecallReceiver();
        this.context = context;
    }

    public PhoneCallStateReceiverManager(Context context){

        this.intentFilter =  new IntentFilter();
        this.intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        this.intentFilter.addAction("android.intent.action.PHONE_STATE");
        this.broadcastReceiver =  new PhonecallReceiver();
        this.context = context;
    }
    public String getPhoneState(){
        Integer phoneCallState = broadcastReceiver.getLastState();
        Log.d("KonyFFI","lastphonestate::"+phoneCallState);
        return (phoneCallState != null)?phoneCallState.toString():"";
    }

    public void getPhoneStateVal(Function callFunc){
        try {
            Log.d("KonyFFI", "PhoneCallStateReceiverManager.getPhoneStateVal() ---> Begin");
            Log.d("KonyFFI","getPhoneStateVal::"+getPhoneState());
            Object[] returnVal = {getPhoneState()};
            callFunc.execute(returnVal);
        }catch (Exception e) {
            Log.e("KonyFFI", "PhoneCallStateReceiverManager.getPhoneStateVal() ---> EXCEPTION: " + e.getMessage());
        }
            Log.d("KonyFFI", "PhoneCallStateReceiverManager.getPhoneStateVal() ---> END");
    }
}
