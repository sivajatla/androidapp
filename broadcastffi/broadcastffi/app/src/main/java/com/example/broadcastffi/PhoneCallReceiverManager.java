package com.example.broadcastffi;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

import com.konylabs.android.KonyMain;
import com.konylabs.vm.Function;

public class PhoneCallReceiverManager{
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

    public PhoneCallReceiverManager(){
        this.context = KonyMain.getActContext();
    }

    public PhoneCallReceiverManager(Context context){

        this.intentFilter =  new IntentFilter();
        this.intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        this.intentFilter.addAction("android.intent.action.PHONE_STATE");
        this.broadcastReceiver =  new PhonecallReceiver();
        this.context = context;
    }
    public String getPhoneState(){
        Integer phoneCallState = broadcastReceiver.getLastState();
        Log.d("PhoneCallReceiverManager.getPhoneState","lastphonestate::"+phoneCallState);

        return (phoneCallState != null)?phoneCallState.toString():"";
    }

    public void getPhoneStateVal(Function callFunc){
        try {
            Log.d("PhoneCallReceiverManager.getPhoneStateVal","getPhoneStateVal::");
            Object[] returnVal = {getPhoneState()};
            callFunc.execute(returnVal);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
