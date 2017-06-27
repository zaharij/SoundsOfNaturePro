package life.centaurs.soundsofnature.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

import life.centaurs.soundsofnature.R;
import life.centaurs.soundsofnature.enums.SoundMakerEntityEnum;
import life.centaurs.soundsofnature.objects.Squad;

public class TabsListeningActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.tabs_listening_bg_color));

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec(SoundMakerEntityEnum.ANIMALS.toString());
        tabSpec.setIndicator(SoundMakerEntityEnum.ANIMALS.toString());
        tabSpec.setContent(new Intent(this, ListeningAnimalsActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(SoundMakerEntityEnum.TRANSPORTS.toString());
        tabSpec.setIndicator(SoundMakerEntityEnum.TRANSPORTS.toString());
        tabSpec.setContent(new Intent(this, ListeningTransportsActivity.class));
        tabHost.addTab(tabSpec);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                Squad.stopSound();
            }
        });
    }
}
