package life.centaurs.soundsofnature.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import life.centaurs.soundsofnature.R;

public class MainActivity extends AppCompatActivity {
    private ImageButton listeningBtn, quizBtn;
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.main_screen_backgr_color));

        listeningBtn = (ImageButton) findViewById(R.id.imageButtonListening);
        listeningBtn.setBackgroundColor(Color.TRANSPARENT);
        quizBtn = (ImageButton) findViewById(R.id.imageButtonQuiz);
        quizBtn.setBackgroundColor(Color.TRANSPARENT);

        listeningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MainActivity.this, TabsListeningActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });

        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MainActivity.this, TabsQuizActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}
