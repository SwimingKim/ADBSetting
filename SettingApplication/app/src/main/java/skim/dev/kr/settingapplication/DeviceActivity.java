package skim.dev.kr.settingapplication;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;

public class DeviceActivity extends DefaultActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        value = Settings.ACTION_DEVICE_INFO_SETTINGS;
        super.onCreate(savedInstanceState);
    }
}
