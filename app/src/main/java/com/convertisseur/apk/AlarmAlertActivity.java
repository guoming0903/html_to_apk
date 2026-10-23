package com.convertisseur.apk;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.Gravity;

import java.io.File;
import java.io.FileOutputStream;

public class AlarmAlertActivity extends Activity {
    private MediaPlayer mediaPlayer;
    private File tempAudioFile;

    private static final String SHUAKA_BASE64 = "data:audio/mpeg;base64,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чко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";

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
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAlarmAndExit();
            }
        });
        layout.addView(stopButton);

        setContentView(layout);
        playShuakaVoice();
    }

    private void playShuakaVoice() {
        try {
            String base64Data = SHUAKA_BASE64.split(",")[1];
            byte[] decodedBytes = android.util.Base64.decode(base64Data, android.util.Base64.DEFAULT);

            tempAudioFile = File.createTempFile("shuaka", ".mp3", getCacheDir());
            FileOutputStream fos = new FileOutputStream(tempAudioFile);
            fos.write(decodedBytes);
            fos.close();

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(tempAudioFile.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
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
    }

    private void stopAlarmAndExit() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (tempAudioFile != null && tempAudioFile.exists()) {
            tempAudioFile.delete();
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        stopAlarmAndExit();
    }
}