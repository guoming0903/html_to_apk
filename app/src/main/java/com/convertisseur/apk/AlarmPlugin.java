package com.convertisseur.apk;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "AlarmPlugin")
public class AlarmPlugin extends Plugin {

    @PluginMethod
    public void scheduleAlarm(PluginCall call) {
        Long triggerMillis = call.getLong("triggerMillis");
        String title = call.getString("title");
        String shiftName = call.getString("shiftName");
        String sound = call.getString("sound", "shuaka");

        if (triggerMillis == null) {
            call.reject("Must provide triggerMillis");
            return;
        }

        Context context = getContext();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("alarm_title", title);
        intent.putExtra("shift_name", shiftName);
        intent.putExtra("alarm_sound", sound);

        int requestCode = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
            context,
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
            call.resolve();
        } else {
            call.reject("AlarmManager is null");
        }
    }
}