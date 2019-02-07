package com.indusind.upisecurity.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class PhoneCallStateReceiver extends BroadcastReceiver {

    private int lastState = TelephonyManager.CALL_STATE_IDLE;
    private Date callStartTime;
    private boolean isIncoming;
    private String savedNumber;  //because the passed incoming is only valid in ringing


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("PhonecallStateReceiver",intent.getAction());
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
        } else {
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            int state = 0;
            if (stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                state = TelephonyManager.CALL_STATE_IDLE;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                state = TelephonyManager.CALL_STATE_RINGING;
            }

            onCallStateChanged(context, state, number);
        }
    }


    public void onCallStateChanged(Context context, int state, String number) {
        Log.d("PhoneCallStateReceiver.onCallStateChanged","state::"+state);
        Log.d("PhoneCallStateReceiver.onCallStateChanged","number::"+number);
        Log.d("PhoneCallStateReceiver.onCallStateChanged","lastState::"+lastState);
        if (this.lastState == state) {
            return;
        }
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                isIncoming = true;
                callStartTime = new Date();
                savedNumber = number;
                this.lastState = state;
                // error message 'App will not work while active call. Please try again.'
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                this.lastState = state;
                if (lastState != TelephonyManager.CALL_STATE_RINGING) {
                    isIncoming = false;
                    callStartTime = new Date();
                    // error message
                }
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                break;
        }
    }

    public int getLastState() {
        return lastState;
    }

    public void setLastState(int lastState) {
        this.lastState = lastState;
    }
}
