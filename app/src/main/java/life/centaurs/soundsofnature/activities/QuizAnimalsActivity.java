package life.centaurs.soundsofnature.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import life.centaurs.soundsofnature.R;
import life.centaurs.soundsofnature.enums.SoundMakerEntityEnum;
import life.centaurs.soundsofnature.objects.QuizObj;
import life.centaurs.soundsofnature.objects.Squad;
import life.centaurs.soundsofnature.save.SaveGameHelper;

import static life.centaurs.soundsofnature.constants.ConstantsConfig.REMOVE_QUIZ_IMAGES_START_NUMBER;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.TEXT_SIZE_DEFAULT;

public class QuizAnimalsActivity extends AppCompatActivity {
    private TextView textView;
    private RelativeLayout gameBoard;
    private QuizObj quiz = new QuizObj();
    private SharedPreferences sharedPreferences;
    private SaveGameHelper saveGameHelper;
    private SoundMakerEntityEnum soundMakerEntityEnum;
    private Button backBtn, soundBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.listening_screen_bg_color));

        sharedPreferences = getPreferences(MODE_PRIVATE);
        saveGameHelper = new SaveGameHelper();

        gameBoard = (RelativeLayout) findViewById(R.id.activity_quiz);
        textView = (TextView) findViewById(R.id.textView);
        backBtn = (Button) findViewById(R.id.buttonBack);
        backBtn.setBackgroundColor(getResources().getColor(R.color.back_btn_color));
        backBtn.setTextColor(Color.WHITE);

        soundBtn = (Button) findViewById(R.id.buttonRepeat);
        soundBtn.setBackgroundColor(getResources().getColor(R.color.back_btn_color));
        soundBtn.setTextColor(Color.WHITE);



        textView.setTextSize(TEXT_SIZE_DEFAULT);

        View.OnClickListener entityOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quiz.getResult(v.getId())){
                    if(!quiz.isVin(sharedPreferences, SoundMakerEntityEnum.ANIMALS, true)){
                        Squad.stopSound();
                        gameBoard.removeViewsInLayout(REMOVE_QUIZ_IMAGES_START_NUMBER, quiz.getPicNumsLength());
                        startQuiz(this);
                        textView.setTextColor(Color.GREEN);
                        textView.setText(Squad.generateRightMessage());
                        Squad.nullRightText(textView);
                    } else {
                        Squad.getDialogWinWindow(QuizAnimalsActivity.this);
                    }
                } else {
                    quiz.isVin(sharedPreferences, SoundMakerEntityEnum.ANIMALS, false);
                    textView.setText(R.string.empty_string);
                    quiz.startSound(QuizAnimalsActivity.this);
                    Drawable drawable = null;
                    drawable = QuizAnimalsActivity.this.getResources().getDrawable(quiz.getSoundMakerMapToOutput().get(quiz.getWinId()).getViewId());
                    Squad.dialogNotRight(QuizAnimalsActivity.this, drawable, quiz.getSoundMakerMapToOutput().get(quiz.getWinId()).getName());
                }
            }
        };

        startQuiz(entityOnClickListener);
    }

    /**
     * starts quiz
     * @param entityOnClickListener
     */
    private void startQuiz(View.OnClickListener entityOnClickListener) {
        quiz.putRandomImages(this, entityOnClickListener, gameBoard, sharedPreferences, SoundMakerEntityEnum.ANIMALS);
        quiz.startSound(this);
    }

    /**
     * returns to main activity
     * @param view
     */
    public void backClick(View view){
        Squad.stopSound();
        Squad.returnMainActivity(QuizAnimalsActivity.this);
    }

    /**
     * repeats the sound
     * @param view
     */
    public void repeatClick(View view){
        quiz.startSound(this);
    }
}
