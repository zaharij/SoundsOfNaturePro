package life.centaurs.soundsofnature.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import life.centaurs.soundsofnature.R;
import life.centaurs.soundsofnature.objects.AnimationObj;
import life.centaurs.soundsofnature.objects.Squad;

import static life.centaurs.soundsofnature.constants.ConstantsConfig.SPLASH_SCREEN_TIME;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = SPLASH_SCREEN_TIME;
    private ImageView splashImageView, imgLogoView;
    private TextView textViewGameVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.splash_screen_backgr_color));

        splashImageView = (ImageView) findViewById(R.id.splashImageView);
        imgLogoView = (ImageView) findViewById(R.id.imageViewLogo);

        textViewGameVersion = (TextView) findViewById(R.id.textViewGameVersion);
        textViewGameVersion.setText(R.string.game_name_version);
        textViewGameVersion.setTextColor(getResources().getColor(R.color.game_name_version_color));

        imgLogoView.setImageResource(R.mipmap.ic_launcher);
        AnimationObj.rotateAnim(splashImageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Squad.returnMainActivity(SplashScreenActivity.this);
            }
        }, SPLASH_TIME_OUT);
    }
}
