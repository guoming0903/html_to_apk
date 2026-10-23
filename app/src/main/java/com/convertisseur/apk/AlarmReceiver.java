package com.convertisseur.apk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(
            PowerManager.FULL_WAKE_LOCK |
            PowerManager.ACQUIRE_CAUSES_WAKEUP |
            PowerManager.ON_AFTER_RELEASE,
            "ShiftCalendar:AlarmWakeLock"
        );
        wakeLock.acquire(10 * 60 * 1000L);

        String title = intent.getStringExtra("alarm_title");
        String shiftName = intent.getStringExtra("shift_name");
        String sound = intent.getStringExtra("alarm_sound");

        Intent alertIntent = new Intent(context, AlarmAlertActivity.class);
        alertIntent.putExtra("alarm_title", title);
        alertIntent.putExtra("shift_name", shiftName);
        alertIntent.putExtra("alarm_sound", sound);
        // 關鍵：必須加上 NEW_TASK 與 CLEAR_TOP，允許背景觸發
        alertIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | 
                             Intent.FLAG_ACTIVITY_CLEAR_TOP | 
                             Intent.FLAG_ACTIVITY_SINGLE_TOP);
                             
        context.startActivity(alertIntent);
    }
}