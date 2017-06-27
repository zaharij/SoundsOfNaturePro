package life.centaurs.soundsofnature.objects;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

import life.centaurs.soundsofnature.R;
import life.centaurs.soundsofnature.activities.MainActivity;
import life.centaurs.soundsofnature.activities.WinActivity;
import life.centaurs.soundsofnature.entities.SoundMakerEntity;
import life.centaurs.soundsofnature.enums.SoundMakerEntityEnum;

import static life.centaurs.soundsofnature.constants.ConstantsConfig.ANIMALS_MAP;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.DIALOGUE_IMAGE_HEIGHT;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.DIALOGUE_IMAGE_WIDTH;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.ID_START_NUMBER;

import static life.centaurs.soundsofnature.constants.ConstantsConfig.RIGHT_MESSAGE_ARRAY;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.TRANSPORTS_MAP;


/** Squad
 * commands, which are uses in program
 * Created by Zakhar on 03.01.2017.
 */

public class Squad {
    private static MediaPlayer mediaPlayer;

    /**
     * sets pictures to the screen
     *
     * @param appCompatActivity
     * @param onClickListener
     * @param relativeLayout
     * @param soundMakerMap - entities resource to use
     * @param columnsNumber - the number of columns
     * @param rowsNumber - the number of rows
     */
    public static void setPictures(AppCompatActivity appCompatActivity, View.OnClickListener onClickListener
            , RelativeLayout relativeLayout, HashMap<Integer, SoundMakerEntity> soundMakerMap, int columnsNumber, int rowsNumber) {

        int[] imgSize = appropriateImageSize(columnsNumber, rowsNumber);
        int idCount = ID_START_NUMBER;
        for(int i = 0 ; i <  columnsNumber ; i++){
            for (int j = 0; j < rowsNumber; j++) {
                int viewId;
                try{
                    viewId = soundMakerMap.get(idCount).getViewId();
                } catch (NullPointerException e){//if the number - rows*columns is greater than the number of entities
                    break;
                }
                ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(0);
                ImageButton soundMakerImageBtn = new ImageButton(appCompatActivity);
                soundMakerImageBtn.setImageResource(viewId);
                soundMakerImageBtn.setOnClickListener(onClickListener);
                soundMakerImageBtn.setBackgroundColor(Color.TRANSPARENT);
                soundMakerImageBtn.setTag(idCount);
                soundMakerImageBtn.setId(idCount);
                soundMakerImageBtn.setX(i * imgSize[1]);
                soundMakerImageBtn.setY(j * imgSize[0]);
                soundMakerImageBtn.setLayoutParams(layoutParams);

                soundMakerImageBtn.getLayoutParams().height = imgSize[0];//set appropriate sizes
                soundMakerImageBtn.getLayoutParams().width = imgSize[1];

                soundMakerImageBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);

                relativeLayout.addView(soundMakerImageBtn);
                idCount++;
            }
        }
    }

    /**
     * starts the sound of current entity
     *
     * @param appCompatActivity
     * @param id - the entity id
     * @param soundMakerMap - entity map (HashMap<Integer, SoundMakerEntity>)
     */
    public static void setSound(AppCompatActivity appCompatActivity, int id, HashMap<Integer, SoundMakerEntity> soundMakerMap){
        mediaPlayer = MediaPlayer.create(appCompatActivity.getBaseContext()
                , soundMakerMap.get(id).getSoundIdArr()[getRandomToGivenNumber(0, soundMakerMap.get(id).getSoundIdArr().length - 1)]);
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
    }

    /**
     * stops sound
     */
    public static void stopSound(){
        try{
            mediaPlayer.stop();
        } catch (NullPointerException e){
            return;
        } catch (IllegalStateException ex){
            return;
        }
    }

    /**
     * after next sound stops, set null text to the text view
     * @param textView
     */
    public static void nullRightText(final TextView textView){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                textView.setText(R.string.empty_string);
            }
        });
    }

    /**
     * returns the map of entities, which user works with
     *
     * @param soundMakerEntityEnum the name of entity map (enum)
     * @return - map of entities (HashMap<Integer, SoundMakerEntity>)
     */
    public static HashMap<Integer, SoundMakerEntity> getCurrentSoundMakerMap(SoundMakerEntityEnum soundMakerEntityEnum) {
        HashMap<Integer, SoundMakerEntity> soundMakerMap;

        switch(soundMakerEntityEnum){
            case ANIMALS:
                soundMakerMap = ANIMALS_MAP;
                break;
            case TRANSPORTS:
                soundMakerMap = TRANSPORTS_MAP;
                break;
            default:
                soundMakerMap = ANIMALS_MAP;
        }
        return soundMakerMap;
    }

    /**
     * returns the width of the user's screen
     * @return - width (int)
     */
    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * returns the height of the user's screen
     * @return height (int)
     */
    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * finds the appropriate size for the entity picture
     * @param rowImagesNumber
     * @param columnImagesNumber
     * @return
     */
    private static int[] appropriateImageSize(int rowImagesNumber, int columnImagesNumber){
        int height;
        if (rowImagesNumber > 2){
            height = (getScreenHeight() / (columnImagesNumber)) * 70 / 100;// means 70%
        } else {
            height = (getScreenHeight() / (columnImagesNumber)) * 90 / 100;
        }
        int width = (getScreenWidth() / (rowImagesNumber)) * 50 / 100;
        int[] size = new int[]{height, width};
        return size;
    }

    /**
     * returns the random number in given range
     *
     * @param minNumber - min number
     * @param maxNumber - max number
     * @return - random number (int)
     */
    public static int getRandomToGivenNumber(int minNumber, int maxNumber){
        int maxn = maxNumber;
        Random ran = new Random();
        int random = ran.nextInt(maxNumber - minNumber + 1) + minNumber;
        return random;
    }

    /**
     * returns dialogue window
     * @param appCompatActivity
     */
    public static void getDialogWinWindow(final AppCompatActivity appCompatActivity){
        AlertDialog.Builder builder = new AlertDialog.Builder(appCompatActivity);
        builder.setTitle(R.string.win_message)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setNegativeButton(R.string.ok_message,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent homeIntent = new Intent(appCompatActivity, WinActivity.class);
                                appCompatActivity.startActivity(homeIntent);
                                appCompatActivity.finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * returns main activity
     */
    public static void returnMainActivity(final AppCompatActivity appCompatActivity){
        Intent homeIntent = new Intent(appCompatActivity, MainActivity.class);
        appCompatActivity.startActivity(homeIntent);
        appCompatActivity.finish();
    }

    /**
     * returns the right variant
     * @param appCompatActivity
     * @param drawableImg
     */
    public static void dialogNotRight(final AppCompatActivity appCompatActivity, Drawable drawableImg, String entityName){
        SpannableString titleMessage = new SpannableString(appCompatActivity.getString(R.string.not_right_message) + entityName);
        titleMessage.setSpan(new ForegroundColorSpan(Color.RED), 0, titleMessage.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        drawableImg = resize(appCompatActivity, drawableImg);
        AlertDialog.Builder builder = new AlertDialog.Builder(appCompatActivity);
        builder.setTitle(titleMessage)
                .setIcon(drawableImg)
                .setCancelable(false)
                .setNegativeButton(R.string.ok_message,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * resizes the images
     * @param appCompatActivity
     * @param image
     * @return resized image (Drawable)
     */
    private static Drawable resize(AppCompatActivity appCompatActivity, Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, DIALOGUE_IMAGE_WIDTH, DIALOGUE_IMAGE_HEIGHT, false);
        return new BitmapDrawable(appCompatActivity.getResources(), bitmapResized);
    }

    /**
     * returns the random message
     * @return
     */
    public static String generateRightMessage(){
        int messageNum = getRandomToGivenNumber(0, RIGHT_MESSAGE_ARRAY.length - 1);
        return RIGHT_MESSAGE_ARRAY[messageNum];
    }
}
