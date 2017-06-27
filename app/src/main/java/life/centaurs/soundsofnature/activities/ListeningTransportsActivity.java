package life.centaurs.soundsofnature.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import life.centaurs.soundsofnature.R;
import life.centaurs.soundsofnature.enums.SoundMakerEntityEnum;
import life.centaurs.soundsofnature.objects.Squad;

import static life.centaurs.soundsofnature.constants.ConstantsConfig.COLUMN_IMAGES_NUMBER;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.ROW_IMAGES_NUMBER;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.TEXT_SIZE_DEFAULT;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.TRANSPORTS_MAP;

public class ListeningTransportsActivity extends AppCompatActivity {
    private TextView textView;
    private RelativeLayout gameBoard;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_transports);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.listening_screen_bg_color));

        gameBoard = (RelativeLayout) findViewById(R.id.activity_listening_transports);
        textView = (TextView) findViewById(R.id.textView);
        backBtn = (Button) findViewById(R.id.buttonBack);
        backBtn.setBackgroundColor(getResources().getColor(R.color.back_btn_color));
        backBtn.setTextColor(Color.WHITE);

        textView.setTextColor(Color.GREEN);
        textView.setTextSize(TEXT_SIZE_DEFAULT);

        View.OnClickListener entityOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(TRANSPORTS_MAP.get(v.getId()).getName());
                Squad.setSound(ListeningTransportsActivity.this, v.getId(), Squad.getCurrentSoundMakerMap(SoundMakerEntityEnum.TRANSPORTS));
            }
        };

        Squad.setPictures(this, entityOnClickListener, gameBoard, Squad.getCurrentSoundMakerMap(SoundMakerEntityEnum.TRANSPORTS)
                , COLUMN_IMAGES_NUMBER, ROW_IMAGES_NUMBER);
    }

    /**
     * returns to main activity
     * @param view
     */
    public void backClick(View view){
        Squad.stopSound();
        Squad.returnMainActivity(ListeningTransportsActivity.this);
    }
}
