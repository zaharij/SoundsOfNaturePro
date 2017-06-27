package life.centaurs.soundsofnature.entities;

/** SoundMakerEntity
 * describes all fields, the sames for transports and animals
 * Created by Zakhar on 02.01.2017.
 */

public abstract class SoundMakerEntity {
    private int id;
    private String name;
    private int viewId;
    private int[] soundIdArr;

    public SoundMakerEntity(String name, int viewId, int[] soundIdArr, int id){
        this.name = name;
        this.viewId = viewId;
        this.soundIdArr = soundIdArr;
        this.id = id;
    }

    public int[] getSoundIdArr() {
        return soundIdArr;
    }

    public void setSoundIdArr(int[] soundIdArr) {
        this.soundIdArr = soundIdArr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int view) {
        this.viewId = view;
    }
}
