package skim.dev.kr.settingapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
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
//        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 10 * 60 * 1000);

//        try {
//            String deviceId = Settings.System.getString(getContentResolver(),
//                    Settings.System.ANDROID_ID);
//            String idBySerialNumber = (String) Build.class.getField("SERIAL").get(null);
//            Toast.makeText(getApplicationContext(), Build.SERIAL+"", Toast.LENGTH_LONG).show();
//        } catch (Exception e) {
//            e.printStackTrace();;
//        }


        mapSettings = new LinkedHashMap<>();
        mapSettings.put("lang", Settings.ACTION_LOCALE_SETTINGS);
        mapSettings.put("[1] display", Settings.ACTION_DISPLAY_SETTINGS);
        mapSettings.put("[2] update", Settings.ACTION_SETTINGS);
        mapSettings.put("[3] notification", Settings.ACTION_SETTINGS);
//        mapSettings.put("update", "android.settings.ACTION_SETTINGS");
        mapSettings.put("[4] time", Settings.ACTION_DISPLAY_SETTINGS);
        mapSettings.put("[5] edge", Settings.ACTION_DISPLAY_SETTINGS);
        mapSettings.put("[6] navi", Settings.ACTION_DISPLAY_SETTINGS);
//        mapSettings.put("edge", "com.samsung.android.app.cocktailbarservice/com.samsung.android.app.cocktailbarservice.settings.EdgeScreenSettingsMain");

        mapSettings.put("[7] connection", Settings.ACTION_SETTINGS);
        mapSettings.put("[8] scanning ", Settings.ACTION_SETTINGS);
        mapSettings.put("[9] sound", Settings.ACTION_SOUND_SETTINGS);
        mapSettings.put("[10] auto time", Settings.ACTION_DATE_SETTINGS);

        mapSettings.put("[11] ok google", Settings.ACTION_VOICE_INPUT_SETTINGS);
        mapSettings.put("[12] swipe", "android.app.action.SET_NEW_PASSWORD");

        mapSettings.put("개발자 설정", Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
//        mapSettings.put("apk install", "apk");
        mapSettings.put("info", Settings.ACTION_DEVICE_INFO_SETTINGS);

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

        if ("apk".equals(value)) {

            File apkFile = new File(Environment.getExternalStorageDirectory().toString() + "/imfine/galaxy.apk");
            Uri apkUri = Uri.fromFile(apkFile);
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        if (value.contains("/")) {
            ComponentName cn = new ComponentName(value.split("/")[0], value.split("/")[1]);
            Intent intent = new Intent();
            intent.setComponent(cn);
            startIntent(intent);
            return;
        }

        Intent intent = new Intent(value);
        startIntent(intent);
    }

    private void startIntent(Intent intent) {
        startActivity(intent);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        finish();
    }

}
