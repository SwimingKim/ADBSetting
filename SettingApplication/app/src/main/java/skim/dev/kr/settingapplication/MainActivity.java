package skim.dev.kr.settingapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private String tagName = "skim";
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> list;
    private HashMap<String, String> mapSettings;

    // 언어설정, 화면해상도변경, swift 해제, 와이파이 설정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            String cmd = "adb tcpip 5555";
//            Runtime.getRuntime().exec("su");
//            Runtime.getRuntime().exec(cmd);
//            Toast.makeText(getApplicationContext(), cmd, Toast.LENGTH_LONG).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Change Screen Timeout
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 10 * 60 * 1000);

        mapSettings = new LinkedHashMap<>();
//        mapSettings.put("update", "android.settings.ACTION_SETTINGS");
        mapSettings.put("update", Settings.ACTION_SETTINGS);
        mapSettings.put("edge", "com.samsung.android.app.cocktailbarservice/com.samsung.android.app.cocktailbarservice.settings.EdgePanels");

        mapSettings.put("notification", Settings.ACTION_SETTINGS);
        mapSettings.put("connection", Settings.ACTION_SETTINGS);
        mapSettings.put("loaction", Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        mapSettings.put("scanning ", Settings.ACTION_SETTINGS);
        mapSettings.put("sound", Settings.ACTION_SOUND_SETTINGS);
        mapSettings.put("display", Settings.ACTION_DISPLAY_SETTINGS);
        mapSettings.put("google", Settings.ACTION_VOICE_INPUT_SETTINGS);
        mapSettings.put("swipe", "android.app.action.SET_NEW_PASSWORD");
//        mapSettings.put("update", "android.settings.SYSTEM_UPDATE_SETTINGS");
        mapSettings.put("auto time", Settings.ACTION_DATE_SETTINGS);

        mapSettings.put("info", "");
        mapSettings.put("lang", Settings.ACTION_LOCALE_SETTINGS);
        mapSettings.put("apk install", "apk");
        mapSettings.put("개발자 설정", Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);

        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<String>(mapSettings.keySet());
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(tagName, list.get(position));
        Log.d(tagName, mapSettings.get(list.get(position)));

        String key = list.get(position);
        String value = mapSettings.get(key);

        if ("".equals(value)) {
            String model = Build.MODEL;
            String version = Build.VERSION.RELEASE;
            TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            String sn = tm.getSimSerialNumber();
            Toast.makeText(getApplicationContext(), model+"/"+imei+"/"+sn+"/"+version, Toast.LENGTH_LONG).show();
            return;
        }

        if ("apk".equals(value)) {

            File apkFile = new File(Environment.getExternalStorageDirectory().toString() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + "test.apk");
            Uri apkUri = Uri.fromFile(apkFile);
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                startIntent(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        if (value.contains("/")) {
//            ComponentName cn = new ComponentName(value.split("/")[0], value.split("/")[1]);
//            Intent intent = new Intent();
//            intent.setComponent(cn);
//            startIntent(intent);

            Intent intent = getPackageManager().getLaunchIntentForPackage(value.split("/")[0]);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }

        Intent intent = new Intent(value);
        startIntent(intent);
    }

    private void startIntent(Intent intent) {
        startActivity(intent);
//        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(getApplicationContext(), keyCode + "", Toast.LENGTH_LONG).show();
        return super.onKeyDown(keyCode, event);
    }

    //
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Button btn = (Button) findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                changeLanguage();
//
//                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
//                intent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
//                startActivity(intent);
//
//            }
//        });
//    }


}
