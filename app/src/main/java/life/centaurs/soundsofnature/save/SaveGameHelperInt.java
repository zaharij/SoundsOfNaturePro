package life.centaurs.soundsofnature.save;

import android.content.SharedPreferences;

import life.centaurs.soundsofnature.enums.SoundMakerEntityEnum;

/**
 * Created by Zakhar on 04.01.2017.
 */

public interface SaveGameHelperInt {
    public void updPicNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum);
    public int getPicNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum);
    public void nullPicNumbers(SharedPreferences sharedPreferences, SoundMakerEntityEnum soundMakerEntityEnum);
}
