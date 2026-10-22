package com.convertisseur.apk;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.widget.TextView tv = new android.widget.TextView(this);
        tv.setText("值班萬年曆 - 原生鬧鐘背景服務執行中\n請透過排程設定鬧鐘");
        tv.setGravity(android.view.Gravity.CENTER);
        setContentView(tv);
    }

    public void scheduleExactAlarm(long triggerMillis, String title, String shiftName) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("alarm_title", title);
        intent.putExtra("shift_name", shiftName);

        int requestCode = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
            this,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        if (alarmManager != null) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerMillis,
                pendingIntent
            );
        }
    }
}