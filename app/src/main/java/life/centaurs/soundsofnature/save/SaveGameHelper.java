package life.centaurs.soundsofnature.save;

import android.content.SharedPreferences;

import life.centaurs.soundsofnature.enums.SoundMakerEntityEnum;

import static life.centaurs.soundsofnature.constants.ConstantsConfig.ADD_TO_WIN_COUNT_NUMBER;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.MAX_PICKS_NUMBER;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.MIN_PICKS_NUMBER;

/** SaveGameHelper
 * implements save/load methods, using SharePreferences (like dao)
 * Created by Zakhar on 04.01.2017.
 */

public class SaveGameHelper implements  SaveGameHelperInt{
    private final String PIC_NUM_ANIMALS_SH_PREF = "picNumAnimals";
    private final String PIC_NUM_TRANSPORTS_SH_PREF = "picNumTransports";
    private final int START_WIN_COUNT_NUMBER = 0;


    /**
     * saves the number of pictures to load to quizScreen
     * @param sharedPreferences
     * @param soundMakerEntityEnum - which entity upgrade
     */
    @Override
    public void updPicNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int count = ADD_TO_WIN_COUNT_NUMBER;
        switch (soundMakerEntityEnum){
            case ANIMALS:
                count += getPicNumbers(sharedPreferences, soundMakerEntityEnum);
                editor.putInt(PIC_NUM_ANIMALS_SH_PREF, count);
                break;
            case TRANSPORTS:
                count += getPicNumbers(sharedPreferences, soundMakerEntityEnum);
                editor.putInt(PIC_NUM_TRANSPORTS_SH_PREF, count);
        }
        editor.commit();
    }

    /**
     * returns the number of pictures to load to quizScreen
     * @param sharedPreferences
     * @param soundMakerEntityEnum - which entity upgrade
     * @return
     */
    @Override
    public int getPicNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum) {
        int resultPickNum = START_WIN_COUNT_NUMBER;
        switch (soundMakerEntityEnum) {
            case ANIMALS:
                resultPickNum = sharedPreferences.getInt(PIC_NUM_ANIMALS_SH_PREF, 0);
                if(resultPickNum > MAX_PICKS_NUMBER || resultPickNum < MIN_PICKS_NUMBER){
                    nullPicNumbers(sharedPreferences, soundMakerEntityEnum);
                    resultPickNum = MIN_PICKS_NUMBER;
                }
                break;
            case TRANSPORTS:
                resultPickNum = sharedPreferences.getInt(PIC_NUM_TRANSPORTS_SH_PREF, 0);
                if(resultPickNum > MAX_PICKS_NUMBER || resultPickNum < MIN_PICKS_NUMBER){
                    nullPicNumbers(sharedPreferences, soundMakerEntityEnum);
                    resultPickNum = MIN_PICKS_NUMBER;
                }
        }
        return resultPickNum;
    }

    /**
     * puts the min number of pictures to load to quizScreen
     * @param sharedPreferences
     * @param soundMakerEntityEnum
     */
    @Override
    public void nullPicNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (soundMakerEntityEnum){
            case ANIMALS:
                editor.putInt(PIC_NUM_ANIMALS_SH_PREF, MIN_PICKS_NUMBER);
                break;
            case TRANSPORTS:
                editor.putInt(PIC_NUM_TRANSPORTS_SH_PREF, MIN_PICKS_NUMBER);
        }
        editor.commit();
    }
}
