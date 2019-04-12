package demos.android.stormdzh.com.androiddemos.entity.lauch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class RuanjianManagerInfoProvide {


    public static List<RuanjianmanagerInfo> getInfos(Context context) {

        List<RuanjianmanagerInfo> ruanjianmanagerInfos;
        PackageManager pm = context.getPackageManager();

        List<PackageInfo> packageInfos = pm.getInstalledPackages(0);
        ruanjianmanagerInfos = new ArrayList<RuanjianmanagerInfo>();
        for (PackageInfo packinfo : packageInfos) {
            //packinfo相当于一个apk包的清单文件
            String packname = packinfo.packageName;// 获取包名
            Drawable ico = packinfo.applicationInfo.loadIcon(pm);//获取icon
            String name = packinfo.applicationInfo.loadLabel(pm).toString();//app名
            //判断应用的来源和安装位置
            int flags = packinfo.applicationInfo.flags;//应用程序信息标记
            if ((flags & ApplicationInfo.FLAG_SYSTEM) == 0) {//用户程序

            } else {//系统程序

            }

            if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == 0) {//手机内存


            } else {//手机外存储设备


            }

            RuanjianmanagerInfo info = new RuanjianmanagerInfo();
            info.setPackname(packname);
            info.setIcon(ico);
            info.setName(name);
            ruanjianmanagerInfos.add(info);


        }

        return ruanjianmanagerInfos;
    }


    /*
     *开启一个应用程序
     *
     *
     * */
    private void startApplication(Activity activity, RuanjianmanagerInfo info) {
        //查询这个应用程序的入口activity，吧他开启起来
        PackageManager pm = activity.getPackageManager();

//查询某一个应用具有启动能力的actvity---启动指定包名的应用
        Intent intent1 = pm.getLaunchIntentForPackage(info.getPackname());

        if (intent1 != null) {
            activity.startActivity(intent1);
        } else {

        }

    }

    //查询出来所有手机上具有启动能力的activity
    public void deskApp(Activity activity) {
        List<String> packnames;
        PackageManager pm = activity.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        //查询出来所有手机上具有启动能力的activity
        @SuppressLint("WrongConstant") List<ResolveInfo> infos = pm.queryIntentActivities(intent, PackageManager.GET_INTENT_FILTERS);

        packnames = new ArrayList<String>();
        for (ResolveInfo info : infos) {
            String packname = info.activityInfo.packageName;
            Intent i = pm.getLaunchIntentForPackage(packname);
            packnames.add(packname);
        }
    }

}
