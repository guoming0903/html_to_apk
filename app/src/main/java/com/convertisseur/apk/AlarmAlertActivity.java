package com.convertisseur.apk;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.Gravity;

public class AlarmAlertActivity extends Activity {
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        );

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundColor(0xFF0F172A);
        layout.setPadding(40, 40, 40, 40);

        TextView iconView = new TextView(this);
        iconView.setText("⏰");
        iconView.setTextSize(64);
        iconView.setGravity(Gravity.CENTER);
        layout.addView(iconView);

        TextView titleView = new TextView(this);
        titleView.setText("鬧鐘響起");
        titleView.setTextSize(24);
        titleView.setTextColor(0xFFDC2626);
        titleView.setGravity(Gravity.CENTER);
        titleView.setPadding(0, 20, 0, 10);
        layout.addView(titleView);

        String title = getIntent().getStringExtra("alarm_title");
        String shiftName = getIntent().getStringExtra("shift_name");

        TextView detailView = new TextView(this);
        detailView.setText((title != null ? title : "值班鬧鐘") + "\n班別 [" + (shiftName != null ? shiftName : "值班") + "] 時間到了！");
        detailView.setTextSize(18);
        detailView.setTextColor(0xFFFFFFFF);
        detailView.setGravity(Gravity.CENTER);
        detailView.setPadding(0, 0, 0, 40);
        layout.addView(detailView);

        Button stopButton = new Button(this);
        stopButton.setText("確定 / 關閉鬧鐘");
        stopButton.setTextSize(18);
        stopButton.setTextColor(0xFFFFFFFF);
        stopButton.setBackgroundColor(0xFFDC2626);
        stopButton.setPadding(30, 30, 30, 30);
        stopButton.setOnClickListener(v -> stopAlarmAndExit());
        layout.addView(stopButton);

        setContentView(layout);
        playShuakaVoice();
    }

    private void playShuakaVoice() {
        try {
            // 直接從 res/raw/shuaka.mp3 建立 MediaPlayer
            mediaPlayer = MediaPlayer.create(this, R.raw.shuaka);
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            } else {
                playDefaultAlarm();
            }
        } catch (Exception e) {
            e.printStackTrace();
            playDefaultAlarm();
        }
    }

    private void playDefaultAlarm() {
        try {
            mediaPlayer = MediaPlayer.create(this, android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI);
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void stopAlarmAndExit() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        stopAlarmAndExit();
    }
}