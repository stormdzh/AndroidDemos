package demos.android.stormdzh.com.androiddemos.entity.lauch;

import android.graphics.drawable.Drawable;

public class RuanjianmanagerInfo extends Object {

    private Drawable icon;//图标
    private String name;//标题
    private String packname;
    private boolean inRom;//是否在内部存储中
    private boolean userApp;//是否是系统应用

    public Drawable getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getPackname() {
        return packname;
    }

    public boolean isInRom() {
        return inRom;
    }

    public boolean isUserApp() {
        return userApp;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPackname(String packname) {
        this.packname = packname;
    }

    public void setInRom(boolean inRom) {
        this.inRom = inRom;
    }

    public void setUserApp(boolean userApp) {
        this.userApp = userApp;
    }
}
