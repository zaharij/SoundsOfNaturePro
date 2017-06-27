package life.centaurs.soundsofnature.constants;

import java.util.HashMap;

import life.centaurs.soundsofnature.R;
import life.centaurs.soundsofnature.entities.Animal;
import life.centaurs.soundsofnature.entities.SoundMakerEntity;
import life.centaurs.soundsofnature.entities.Transport;

import static life.centaurs.soundsofnature.activities.MainActivity.mContext;

/** ConstantsConfig
 * program constants
 * Created by Zakhar on 01.01.2017.
 */

public class ConstantsConfig {

    // SplashScreen constants

    public final static int SPLASH_SCREEN_TIME = 5000;
    public final static int ROTATE_DURATION_SPLASH_IMG = 1000;
    public final static int START_OFFSET_TIME_SPLASH_IMG = 2000;
    public final static int ROTATION_DEGREE_SPLASH_IMG = 180;
    public final static int CURRENT_ROTATION_DEGREE_SPLASH_IMG = 0;
    public final static int ROTATION_POSITION = 300;
    public final static int ROTATION_DEGREE_CIRCLE_IMG = 360;
    public final static int ROTATE_DURATION_WIN_IMG = 500;
    public final static int ROTATION_REPEAT_WIN_IMG = 3;
    public final static int ROTATION_POSITION_WIN = 200;
    public final static int WIN_SCREEN_TIME = 4000;

    // ListeningScreen constants

    public final static int ROW_IMAGES_NUMBER = 3;
    public final static int COLUMN_IMAGES_NUMBER = 4;
    public final static int ID_START_NUMBER = 0;
    public final static int TEXT_SIZE_DEFAULT = 30;

    public final static HashMap<Integer, SoundMakerEntity> ANIMALS_MAP = new HashMap<Integer, SoundMakerEntity>()
    {{
        int idCount = ID_START_NUMBER;
        put(idCount, new Animal("a tiger", R.drawable.animal_icon_01_small_size, new int []{R.raw.tiger_1, R.raw.tiger_2}, idCount++));
        put(idCount, new Animal("a monkey", R.drawable.animal_icon_02_small_size, new int []{R.raw.monkey_1, R.raw.monkey_2}, idCount++));
        put(idCount, new Animal("an elephant", R.drawable.animal_icon_03_small_size, new int []{R.raw.elephant_1, R.raw.elephant_2}, idCount++));
        put(idCount, new Animal("a frog", R.drawable.animal_icon_04_small_size, new int []{R.raw.frog_1, R.raw.frog_2}, idCount++));
        put(idCount, new Animal("a pig", R.drawable.animal_icon_05_small_size, new int []{R.raw.pig_1, R.raw.pig_2}, idCount++));
        put(idCount, new Animal("a horse", R.drawable.animal_icon_06_small_size, new int []{R.raw.horse_1, R.raw.horse_2}, idCount++));
        put(idCount, new Animal("a cat", R.drawable.animal_icon_07_small_size, new int []{R.raw.cat_1, R.raw.cat_2}, idCount++));
        put(idCount, new Animal("a sheep", R.drawable.animal_icon_08_small_size, new int []{R.raw.sheep_1, R.raw.sheep_2}, idCount++));
        put(idCount, new Animal("a dog", R.drawable.animal_icon_09_small_size, new int []{R.raw.dog_1, R.raw.dog_2}, idCount++));
        put(idCount, new Animal("a chicken", R.drawable.animal_icon_10_small_size, new int []{R.raw.chicken_1, R.raw.chicken_2}, idCount++));
        put(idCount, new Animal("a cow", R.drawable.animal_icon_11_small_size, new int []{R.raw.cow_1, R.raw.cow_2}, idCount++));
        put(idCount, new Animal("a lion", R.drawable.animal_icon_12_small_size, new int []{R.raw.lion_1, R.raw.lion_2}, idCount++));
    }};

    public final static HashMap<Integer, SoundMakerEntity> TRANSPORTS_MAP = new HashMap<Integer, SoundMakerEntity>()
    {{
        int idCount = ID_START_NUMBER;
        put(idCount, new Transport("a police car", R.drawable.transport_icon_01_small_size, new int[]{R.raw.police_car}, idCount++));
        put(idCount, new Transport("an ambulance", R.drawable.transport_icon_02_small_size, new int[]{R.raw.ambulance}, idCount++));
        put(idCount, new Transport("a fire engine", R.drawable.transport_icon_03_small_size, new int[]{R.raw.fire_engine}, idCount++));
        put(idCount, new Transport("a rocket", R.drawable.transport_icon_04_small_size, new int[]{R.raw.rocket}, idCount++));
        put(idCount, new Transport("an airplane", R.drawable.transport_icon_05_small_size, new int[]{R.raw.airplane}, idCount++));
        put(idCount, new Transport("a helicopter", R.drawable.transport_icon_06_small_size, new int[]{R.raw.helicopter}, idCount++));
        put(idCount, new Transport("a train", R.drawable.transport_icon_07_small_size, new int[]{R.raw.train}, idCount++));
        put(idCount, new Transport("a car", R.drawable.transport_icon_08_small_size, new int[]{R.raw.car}, idCount++));
        put(idCount, new Transport("a motorcycle", R.drawable.transport_icon_09_small_size, new int[]{R.raw.motorcycle}, idCount++));
        put(idCount, new Transport("a bicycle", R.drawable.transport_icon_10_small_size, new int[]{R.raw.bicycle}, idCount++));
        put(idCount, new Transport("a ship", R.drawable.transport_icon_11_small_size, new int[]{R.raw.ship}, idCount++));
        put(idCount, new Transport("a bus", R.drawable.transport_icon_12_small_size, new int[]{R.raw.bus}, idCount++));
    }};

    //QuizScreen constants

    public final static int QUIZ_ROW_IMAGES_NUMBER = 2;
    public final static int QUIZ_COLUMN_IMAGES_NUMBER = 3;
    public final static int REMOVE_QUIZ_IMAGES_START_NUMBER = 3;
    public final static int ADD_TO_WIN_COUNT_NUMBER = 1;
    public final static int WIN_NUMBER_TO_PLUS_IMG = 5;
    public final static int DEFAULT_WIN_NUMBER = 0;
    public final static int MAX_PICKS_NUMBER = 6;
    public final static int MIN_PICKS_NUMBER = 2;

    public final static String[] RIGHT_MESSAGE_ARRAY = new String[]{mContext.getString(R.string.right_message)
            , mContext.getString(R.string.excellent_message), mContext.getString(R.string.brilleant_message)};

    public final static int DIALOGUE_IMAGE_WIDTH = 300;
    public final static int DIALOGUE_IMAGE_HEIGHT = 300;
}
