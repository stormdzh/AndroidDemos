package demos.android.stormdzh.com.androiddemos.notify;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import demos.android.stormdzh.com.androiddemos.hook.HookClickActivity;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2020-08-03 17:28
 */
//点击事件接收的广播
public class ClickReceiver extends BroadcastReceiver {
    public static final String ACTION_SWITCH_CLICK = "notification.toutiao.com.notificationapp.CLICK";

    NotificationUtil mNotificationUtil;
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.i("ClickReceiver","onReceive==>"+msg);
        String action = intent.getAction();
        if (ACTION_SWITCH_CLICK.equals(action)) {
            Toast.makeText(context, "点击事件", Toast.LENGTH_SHORT).show();
        }
        Intent intent1=new Intent(context, HookClickActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                .FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);//
        context.startActivity(intent1);
//        if(mNotificationUtil==null){
//            mNotificationUtil=new NotificationUtil();
//            mNotificationUtil.init(context);
//        }
//        mNotificationUtil.cancel();

//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.cancel(type);
    }
}