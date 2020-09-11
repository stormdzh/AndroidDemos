package demos.android.stormdzh.com.androiddemos.notify.wx;

public class NotificationItem {

    public NotificationItem(int id){
        this.id=id;
    }
    /**
     * 跳转链接
     */
    public String href = "";

    /**
     * 展示内容id
     */
    public int id ;
    /**
     * 图像
     */
    public String img = "";

    /**
     * 标题
     */
    public String title = "";

    /**
     * 类型
     *
     * 0：不跳转 1：h5 2：原生
     */
    public int type ;

    public boolean isPicLoaded;
}
