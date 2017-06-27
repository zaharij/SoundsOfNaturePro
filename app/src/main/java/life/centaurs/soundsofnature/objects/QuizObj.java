package life.centaurs.soundsofnature.objects;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.HashMap;

import life.centaurs.soundsofnature.entities.SoundMakerEntity;
import life.centaurs.soundsofnature.enums.SoundMakerEntityEnum;
import life.centaurs.soundsofnature.save.SaveGameHelper;

import static life.centaurs.soundsofnature.constants.ConstantsConfig.DEFAULT_WIN_NUMBER;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.ID_START_NUMBER;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.MAX_PICKS_NUMBER;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.QUIZ_COLUMN_IMAGES_NUMBER;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.QUIZ_ROW_IMAGES_NUMBER;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.WIN_NUMBER_TO_PLUS_IMG;

/** QuizObj
 * Quiz describing
 * Created by Zakhar on 04.01.2017.
 */

public class QuizObj {
    private HashMap<Integer, SoundMakerEntity> soundMakerMap;
    private HashMap<Integer, SoundMakerEntity> soundMakerMapToOutput;
    private int[] picNums;
    private int winId;
    private int winNumber = DEFAULT_WIN_NUMBER;
    private SaveGameHelper saveGameHelper = new SaveGameHelper();

    public int getWinId(){
        return winId;
    }

    public int getPicNumsLength() {
        return picNums.length;
    }

    public HashMap<Integer, SoundMakerEntity> getSoundMakerMapToOutput() {
        return soundMakerMapToOutput;
    }

    /**
     * puts random entities to the quizScreen
     * @param appCompatActivity - current activity
     * @param onClickListener
     * @param relativeLayout
     * @param sharedPreferences
     * @param soundMakerEntityEnum
     */
    public void putRandomImages(AppCompatActivity appCompatActivity, View.OnClickListener onClickListener
            , RelativeLayout relativeLayout, SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum){
        soundMakerMapToOutput = new HashMap<Integer, SoundMakerEntity>();
        soundMakerMap = Squad.getCurrentSoundMakerMap(soundMakerEntityEnum);
        picNums = getPicturesNumbers(sharedPreferences, soundMakerEntityEnum);
        for(int i = 0; i < picNums.length; i++){
            soundMakerMapToOutput.put(i, soundMakerMap.get(picNums[i]));
        }
        winId = getVinIdNumber();
        Squad.setPictures(appCompatActivity, onClickListener, relativeLayout, soundMakerMapToOutput, QUIZ_COLUMN_IMAGES_NUMBER, QUIZ_ROW_IMAGES_NUMBER);
    }

    /**
     * starts sound
     * @param appCompatActivity
     */
    public void startSound(AppCompatActivity appCompatActivity){
        int id = picNums[winId];
        Squad.setSound(appCompatActivity, winId, soundMakerMapToOutput);
    }

    /**
     * returns the result of playing
     * @param id
     * @return
     */
    public boolean getResult(int id){
        if (id == winId){
            return true;
        } else {
            return false;
        }
    }

    /**
     * counts how many times user wins
     * , if count gets max number - calls the update method
     * , returns the result if the picture numbers is increased
     * @param sharedPreferences
     * @param soundMakerEntityEnum
     * @param isVin
     * @return
     */
    public boolean isVin(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum, boolean isVin){
        boolean result = false;
        if(isVin){
            winNumber++;
        } else {
            winNumber = DEFAULT_WIN_NUMBER;
        }
        if(winNumber >= WIN_NUMBER_TO_PLUS_IMG){
            if (saveGameHelper.getPicNumbers(sharedPreferences, soundMakerEntityEnum) >= MAX_PICKS_NUMBER){
                result = true;
            }
            saveGameHelper.updPicNumbers(sharedPreferences, soundMakerEntityEnum);
            winNumber = DEFAULT_WIN_NUMBER;
        }
        return result;
    }

    /**
     * returns the random entity
     * , which sound to play
     * @return - the id of entity
     */
    private int getVinIdNumber(){
        int winId = Squad.getRandomToGivenNumber(ID_START_NUMBER, picNums.length - 1);
        return winId;
    }

    /**
     * returns the array of entity ids
     * , which takes place in quiz
     * @param sharedPreferences
     * @param soundMakerEntityEnum
     * @return the array of entities (int[])
     */
    private int[] getPicturesNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum){
        int picNum = saveGameHelper.getPicNumbers(sharedPreferences, soundMakerEntityEnum);
        int[] picNums = new int[picNum];
        boolean zeroIsIn = false;//if zero is in the array, because the default value in the array is zero

        start:for(int i = 0; i < picNum; i++){
            int number = Squad.getRandomToGivenNumber(ID_START_NUMBER, soundMakerMap.size() - 1);
            if (number == 0){//if random number equals to zero - put it into array, and make sure that it number will be not repeated
                zeroIsIn = true;
            }
            for(int j = 0; j < picNums.length; j++) {
                if(zeroIsIn ? number == picNums[j] : number != 0 && number == picNums[j]){// if current random number is already in the array - continue
                    if (i > 0){
                        i--;
                    }
                    continue start;
                }
            }
            picNums[i] = number;
        }
        return picNums;
    }
}
