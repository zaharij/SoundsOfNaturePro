package life.centaurs.soundsofnature.objects;

import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import static life.centaurs.soundsofnature.constants.ConstantsConfig.*;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.CURRENT_ROTATION_DEGREE_SPLASH_IMG;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.ROTATE_DURATION_SPLASH_IMG;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.ROTATION_DEGREE_SPLASH_IMG;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.ROTATION_POSITION;
import static life.centaurs.soundsofnature.constants.ConstantsConfig.START_OFFSET_TIME_SPLASH_IMG;

/** AnimationObj
 * describes all program animation
 * Created by Zakhar on 02.01.2017.
 */

public class AnimationObj {

    /**
     * rotates given animation
     * , the default degree number is 180 (you can change it in ConstantsConfig)
     * @param view - current image to rotate
     */
    public static void rotateAnim(ImageView view){
        int mCurrRotation = CURRENT_ROTATION_DEGREE_SPLASH_IMG;
        float fromRotation = mCurrRotation;
        float toRotation = ROTATION_DEGREE_SPLASH_IMG;
        final RotateAnimation rotateAnim = new RotateAnimation(fromRotation
                , toRotation, ROTATION_POSITION, ROTATION_POSITION);
        rotateAnim.setDuration(ROTATE_DURATION_SPLASH_IMG);
        rotateAnim.setStartOffset(START_OFFSET_TIME_SPLASH_IMG);
        rotateAnim.setFillAfter(true);
        view.startAnimation(rotateAnim);
    }

    /**
     * rotates given animation
     * , the default degree number is 360 (you can change it in ConstantsConfig)
     * @param view
     */
    public static void rotationInfinity(ImageView view){
        int mCurrRotation = CURRENT_ROTATION_DEGREE_SPLASH_IMG;
        float fromRotation = mCurrRotation;
        float toRotation = ROTATION_DEGREE_CIRCLE_IMG;
        final RotateAnimation rotateAnim = new RotateAnimation(fromRotation
                , toRotation, ROTATION_POSITION_WIN, ROTATION_POSITION_WIN);
        rotateAnim.setDuration(ROTATE_DURATION_WIN_IMG);
        rotateAnim.setRepeatCount(ROTATION_REPEAT_WIN_IMG);
        rotateAnim.setFillAfter(true);
        view.startAnimation(rotateAnim);
    }
}
