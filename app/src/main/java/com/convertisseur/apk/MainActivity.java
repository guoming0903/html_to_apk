package com.convertisseur.apk;

import android.os.Bundle;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 註冊自定義的鬧鐘外掛，讓網頁前端的 JS 可以呼叫原生排程
        registerPlugin(AlarmPlugin.class);
    }
}