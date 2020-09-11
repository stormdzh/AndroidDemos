package demos.android.stormdzh.com.androiddemos.notify.wx;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.hook.HookClickActivity;

/**
 * @Description: 常驻通知栏
 * @Author: dzh
 * @CreateDate: 2020-08-12 15:22
 */
public class ResidentNotificationView {

    private String TAG = "ResidentNotificationView";
    private int notifycatonid = 10085;

    private NotificationManager manager;
    private Bitmap icon;
    private Context mContext;

    private RemoteViews remoteViews;
    Notification notification = null;
    private String mChannelId = "ResidentNotificationID";
    private String mChannelName = "ResidentNotificationName";

    private int[] itemFunctionIds = new int[]{R.id.itmeFunction01, R.id.itmeFunction02, R.id.itmeFunction03, R.id.itmeFunction04};
    private int[] tvTitleIds = new int[]{R.id.tvNOtify01, R.id.tvNOtify02, R.id.tvNOtify03, R.id.tvNOtify04};
    private int[] ivImgIds = new int[]{R.id.ivNOtify01, R.id.ivNOtify02, R.id.ivNOtify03, R.id.ivNOtify04};
    private List<NotificationItem> mNotifiList; //通知数据

    /**
     * 初始化
     *
     * @param context Context
     */
    public void init(Context context) {
        this.mContext = context;
        manager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
        icon = BitmapFactory.decodeResource(mContext.getResources(),
                R.mipmap.ic_launcher);
    }

    /**
     * 更新通知栏
     *
     * @param notifiList List<NotificationItem>
     */
    public synchronized void update(List<NotificationItem> notifiList) {
        this.mNotifiList = notifiList;
        if (mNotifiList == null || mNotifiList.isEmpty()) return;
        for (NotificationItem item : mNotifiList) {
            item.isPicLoaded = false;
        }
        create();
    }

    /**
     * 根据数据构建通知栏
     */
    public void create() {
        initViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  //8.0
            createApi26();
        } else {
            createNormal();
        }

        showNotify();
    }

    /**
     * 设置ui和跳转
     */
    private synchronized void initViews() { //目前只能展示3个和4个入口的样式
        if (remoteViews == null) {
            remoteViews = new RemoteViews(mContext.getPackageName(),
                    R.layout.xes_custom_resident_notification);
        }
        int notifySize = mNotifiList.size();
        if (notifySize < 3) return;
        if (notifySize > 4) {
            mNotifiList = mNotifiList.subList(0, 4);
        }

        //处理展示和点击事件
        for (int i = 0; i < itemFunctionIds.length; i++) {
            if (i < notifySize) {
                NotificationItem item = mNotifiList.get(i);
                if (item == null) return;
                //控制那几个入口展示
                remoteViews.setViewVisibility(itemFunctionIds[i], View.VISIBLE);
                //设置标题
                remoteViews.setTextViewText(tvTitleIds[i], item.title);
                //设置图片
                loadPic(i, ivImgIds[i], item.img);
                //设置跳转和数据
                Intent intent = new Intent(mContext, HookClickActivity.class);
                intent.putExtra("xesschema", item.href);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                        .FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);//
                PendingIntent mBroadcastPendingIntent = PendingIntent.getActivity(mContext, i,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);
                remoteViews.setOnClickPendingIntent(itemFunctionIds[i],
                        mBroadcastPendingIntent);

            } else {
                remoteViews.setViewVisibility(itemFunctionIds[i], View.GONE);
            }
        }

    }

    /**
     * 创建8.0以下的ui
     *
     * 优先级在 7.1 及以下有用，8.0 则使用 channel 来设置。
     *
     * 通知栏是可以设置优先级的，它有5个常亮可以选
     *
     *
     *
     * PRIORITY_DEFAULT 默认优先级
     *
     * PRIORITY_MIN 优先级最低，某个特定场合才显示
     *
     * PRIORITY_LOW 优先级较低，系统可能将这类通知缩小，或者改变它的显示位置，比如靠后的位置
     *
     * PRIORITY_HIGH 表示较高程度，系统可能对它进行方法，或者改变位置，比如靠前的位置
     *
     * PRIORITY_MAX  优先级最高，这类通知会让用户立刻看到
     */
    private void createNormal() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setContent(remoteViews)
                .setCustomBigContentView(remoteViews)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(icon)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setShowWhen(true)
                .setAutoCancel(true)
                .setOngoing(true);
        notification = builder.build();
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
    }

    /**
     * 8.0以上创建通知栏
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createApi26() {
        if (manager == null) return;
        NotificationChannel channel = new NotificationChannel(mChannelId, mChannelName, NotificationManager.IMPORTANCE_HIGH);//FLAG_AUTO_CANCEL
        manager.createNotificationChannel(channel);

        notification = new NotificationCompat.Builder(mContext, mChannelId)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(remoteViews)
                .setCustomBigContentView(remoteViews)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();

        notification.flags |= Notification.FLAG_ONGOING_EVENT;

    }

    /**
     * 展示通知栏
     */
    private void showNotify() {
        if (manager == null || notification == null) return;
        manager.notify("aaaaaa",notifycatonid, notification);
    }


    /**
     * 加载图片
     *
     * @param viewid 展示图片空间id
     * @param url    图片地址
     */
    private void loadPic(final int position, final int viewid, String url) {
        Log.i(TAG, "图片加载开始：" + url);
//        ImageLoader
//                .with(mContext) // could be an issue!
//                .load(url)
//                .asBitmap(new SingleConfig.BitmapListener() {
//                    @Override
//                    public void onSuccess(Drawable drawable) {
//                        if (mNotifiList != null && position < mNotifiList.size()) {
//                            mNotifiList.get(position).isPicLoaded = true;
//                        }
//                        Bitmap bitmap = drawableToBitmap(drawable);
//                        if (bitmap != null) {
//                            Log.i(TAG, "图片尺寸：" + bitmap.getWidth() + ":" + bitmap.getHeight());
//                            remoteViews.setImageViewBitmap(viewid, bitmap);
//                        } else {
//                            //设置默认图片
//                            remoteViews.setImageViewBitmap(viewid, icon);
//                        }
//                        checkShowNotify();
//                    }
//
//                    @Override
//                    public void onFail() {
//                        Log.i(TAG, "图片加载失败");
//                        if (mNotifiList != null && position < mNotifiList.size()) {
//                            mNotifiList.get(position).isPicLoaded = true;
//                        }
//                        //设置默认图片
//                        remoteViews.setImageViewBitmap(viewid, icon);
//                        checkShowNotify();
//                    }
//                });
    }

    /**
     * 每次加载图片触发校验
     */
    private void checkShowNotify() {
        if (loadPicComplete()) {
            showNotify();
        }
    }

    /**
     * 图片是否都加载过了，包含成功加载和失败加载的图片
     *
     * @return true：加载完了 false：没有加载完
     */
    private boolean loadPicComplete() {
        if (mNotifiList == null || mNotifiList.isEmpty()) {
            return false;
        }
        for (NotificationItem item : mNotifiList) {
            if (!item.isPicLoaded) {
                return false;
            }
        }

        return true;
    }

    /**
     * Drawable 转 bitmap
     *
     * @param drawable Drawable
     * @return
     */
    private Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable == null) return null;
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.RGB_565
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }


    public void test(){

        List<NotificationItem> noti=new ArrayList<>();
//        noti.add(new NotificationItem());
        noti.add(new NotificationItem(2));
        noti.add(new NotificationItem(3));
        noti.add(new NotificationItem(4));
        update(noti);
    }

}
