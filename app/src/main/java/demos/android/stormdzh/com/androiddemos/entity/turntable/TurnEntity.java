package demos.android.stormdzh.com.androiddemos.entity.turntable;

/**
 * Created by a111 on 2018/9/5.
 */

public class TurnEntity {
    public String pic;
    public String title;
    public String id;
    public int state;

    public TurnEntity(String id, String title, String pic){
        this.id=id;
        this.title=title;
        this.pic=pic;
    }
}
