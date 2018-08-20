package skim.dev.kr.settingapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

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

        mapSettings = new HashMap<>();
        mapSettings.put("sound", Settings.ACTION_SOUND_SETTINGS);
        mapSettings.put("AUTO TIME OFF", Settings.ACTION_DATE_SETTINGS);
        mapSettings.put("개발자 설정", Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
        mapSettings.put("언어 설정", Settings.ACTION_LOCALE_SETTINGS);
        mapSettings.put("화면 해상도", Settings.ACTION_DISPLAY_SETTINGS);
        mapSettings.put("Swife 해제", "android.app.action.SET_NEW_PASSWORD");
//        ComponentName cn = new ComponentName("com.samsung.android.app.cocktailbarservice","com.samsung.android.app.cocktailbarservice.settings.EdgePanels");
        mapSettings.put("edge", "com.samsung.android.app.cocktailbarservice/com.samsung.android.app.cocktailbarservice.settings.EdgePanels");
        mapSettings.put("notification", Settings.ACTION_SETTINGS);

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

        if (value.contains("/")) {
            ComponentName cn = new ComponentName(value.split("/")[0], value.split("/")[1]);
            Intent intent = new Intent();
            intent.setComponent(cn);
            startActivity(intent);
            return;
        }

        Intent intent = new Intent(value);
        startActivity(intent);
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
