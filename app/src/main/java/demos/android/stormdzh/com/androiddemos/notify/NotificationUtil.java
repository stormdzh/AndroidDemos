package demos.android.stormdzh.com.androiddemos.notify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.hook.HookClickActivity;

/**
 * @Description: 常驻通知栏
 * 参考文章:https://blog.csdn.net/weixin_43233747/article/details/89366419
 * https://www.jb51.net/article/36567.htm
 * https://blog.csdn.net/wangbaochu/article/details/50591994
 * https://www.jianshu.com/p/5c8f8510e8d5
 *
 *
 *
 * https://www.cnblogs.com/suliang-com/p/7089151.html
 * https://www.jianshu.com/p/f974fdc34b9e?tdsourcetag=s_pcqq_aiomsg
 *
 *
 *
 * https://blog.csdn.net/u013541140/article/details/84822317
 *
 *
 * @Author: dzh
 * @CreateDate: 2020-08-03 13:57
 */
public class NotificationUtil {
    private NotificationManager manager;
    private Bitmap icon;
    private Context mContext;

    private static final int NOTIFICATION_ID_8 = 7;

    public void init(Context context) {
        this.mContext = context;
        // 获取通知服务
        manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        icon = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_launcher);
    }


    public void showCustomView() {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                R.layout.custom_notification);
        Intent intent = new Intent(mContext, HookClickActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0,
                intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.paly_pause_music,
                pendingIntent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setContent(remoteViews).setSmallIcon(R.drawable.music_icon)
                .setLargeIcon(icon).setOngoing(true)
                .setTicker("music is playing");
        manager.notify(NOTIFICATION_ID_8, builder.build());
    }


    public void showTest() {
        Notification notification = null;
        String myChannelId = "myChannel_01";
        String myChannelName = "myChannel";
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(myChannelId, myChannelName,
                    NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
        }
        Toast.makeText(mContext, myChannelId, Toast.LENGTH_SHORT).show();

//        notification = new NotificationCompat.Builder(mContext, myChannelId)
        notification = new NotificationCompat.Builder(mContext)
                .setContentTitle("我是测试")
                .setContentText("你有一个新通知")
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                .build();

        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        manager.notify(7, notification);
    }

    public void cancel() {
        manager.cancel(7);
    }


    public void showActivity() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //>=8.0
            Notification notification = null;
            String myChannelId = "myChannel_01";
            String myChannelName = "myChannel";
            Toast.makeText(mContext, myChannelId, Toast.LENGTH_SHORT).show();
            NotificationChannel channel = null;
            channel = new NotificationChannel(myChannelId, myChannelName,
                    NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);


            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                    R.layout.custom_wx_notification);

//            Intent intent = new Intent(mContext, ClickReceiver.class);
            Intent intent = new Intent(mContext, HookClickActivity.class);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);//关键的一步，设置启动模式
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//关键的一步，设置启动模式
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                    .FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);//
            // 关键的一步，设置启动模式

//            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0,
//                    intent, 0);

            PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 1,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            remoteViews.setOnClickPendingIntent(R.id.tvZhichu,
                    pendingIntent);

            notification = new NotificationCompat.Builder(mContext, myChannelId)
                    .setWhen(System.currentTimeMillis())
                    .setShowWhen(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContent(remoteViews)
                    .setCustomBigContentView(remoteViews)
                    .build();

            notification.flags |= Notification.FLAG_ONGOING_EVENT;
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
            manager.notify(7, notification);
        }
//        notification = new NotificationCompat.Builder(mContext, myChannelId)
//                .setContentTitle("我是测试")
//                .setContentText("你有一个新通知")
//                .setWhen(System.currentTimeMillis())
//                .setShowWhen(true)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.ic_launcher))
//                .build();


    }


    public void showBroacast() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //>=8.0
            Notification notification = null;
            String myChannelId = "myChannel_01";
            String myChannelName = "myChannel";
            Toast.makeText(mContext, myChannelId, Toast.LENGTH_SHORT).show();
            NotificationChannel channel = null;
//            channel = new NotificationChannel(myChannelId, myChannelName, NotificationManager
//                    .IMPORTANCE_DEFAULT);//FLAG_AUTO_CANCEL
            channel = new NotificationChannel(myChannelId, myChannelName, NotificationManager
                    .IMPORTANCE_HIGH);//FLAG_AUTO_CANCEL
            manager.createNotificationChannel(channel);


            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                    R.layout.custom_wx_notification);

//            remoteViews.setImageViewUri(R.id.ivIcon, Uri.parse("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=180811274,3179596559&fm=26&gp=0.jpg"));

            Intent intent = new Intent(mContext, ClickReceiver.class);
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);//关键的一步，设置启动模式
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//关键的一步，设置启动模式
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                    .FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);//
            // 关键的一步，设置启动模式

//            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 2,
//                    intent, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 2,
                    intent, PendingIntent.FLAG_ONE_SHOT);

//            remoteViews.setOnClickPendingIntent(R.id.tvZhichu,
//                    pendingIntent);
//            remoteViews.setOnClickFillInIntent(R.id.tvZhichu,
//                    intent);
            remoteViews.setPendingIntentTemplate(R.id.tvZhichu,
                    pendingIntent);

            notification = new NotificationCompat.Builder(mContext, myChannelId)
                    .setWhen(System.currentTimeMillis())
                    .setShowWhen(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContent(remoteViews)
                    .setCustomBigContentView(remoteViews)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .build();
            notification.when = System.currentTimeMillis();

//            notification.flags|= Notification.FLAG_AUTO_CANCEL;
//            notification.flags |= Notification.FLAG_NO_CLEAR;
            notification.flags |= Notification.FLAG_ONGOING_EVENT;
            manager.notify(7, notification);
        } else {

            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                    R.layout.custom_wx_notification);
            Intent intent = new Intent(mContext, ClickReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0,
                    intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.tvZhichu,
                    pendingIntent);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
            builder.setContent(remoteViews).setSmallIcon(R.drawable.music_icon)
                    .setLargeIcon(icon).setOngoing(true)
                    .setTicker("music is playing");
            manager.notify(7, builder.build());

        }
    }


}
