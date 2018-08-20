package skim.dev.kr.settingapplication;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;

public class DisplayActivity extends DefaultActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        value = Settings.ACTION_DISPLAY_SETTINGS;
        super.onCreate(savedInstanceState);
    }
}
