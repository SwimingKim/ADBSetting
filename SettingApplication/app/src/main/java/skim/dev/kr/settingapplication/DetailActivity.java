package skim.dev.kr.settingapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView modelText = (TextView) findViewById(R.id.modelText);
        TextView imeiText = (TextView) findViewById(R.id.imeiText);
        TextView snText = (TextView) findViewById(R.id.snText);
        TextView versionText = (TextView) findViewById(R.id.versionText);

        modelText.setText("model = "+Build.MODEL);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        imeiText.setText("imei = "+tm.getDeviceId());
        snText.setText("sn = "+tm.getSubscriberId());
        versionText.setText("version = "+Build.VERSION.RELEASE);

    }

}
