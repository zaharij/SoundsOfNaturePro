package life.centaurs.soundsofnature.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import life.centaurs.soundsofnature.R;
import life.centaurs.soundsofnature.objects.AnimationObj;
import life.centaurs.soundsofnature.objects.Squad;

import static life.centaurs.soundsofnature.constants.ConstantsConfig.WIN_SCREEN_TIME;

public class WinActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = WIN_SCREEN_TIME;
    private ImageView winImageView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.listening_screen_bg_color));

        winImageView = (ImageView) findViewById(R.id.imageViewWin);

        mediaPlayer = MediaPlayer.create(this, R.raw.win);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
            }
        });

        AnimationObj.rotationInfinity(winImageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Squad.returnMainActivity(WinActivity.this);
            }
        }, SPLASH_TIME_OUT);
    }
}
