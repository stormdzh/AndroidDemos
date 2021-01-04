package demos.android.stormdzh.com.androiddemos.jobservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * Created by lijinhua on 2017/6/12.
 * 定时提醒的广播接收者
 */
public class AlaramReceiver extends BroadcastReceiver {

    public static final String UN_STUDY_WAKE_ACTION = "com.tongxue.tiku.service.alarm.study";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(AlarmManagerUtil.TAG, intent.getAction() + "~~~~~~ AlaramReceiver");
        switch (intent.getAction()) {

            case UN_STUDY_WAKE_ACTION:
                Log.i(AlarmManagerUtil.TAG, "学习的action走到这了~~~~~~AlaramReceiver");
                //只执行一次
                AlarmManagerUtil.cancelUnStudyAlarm(context);
                break;
        }

        Log.i(AlarmManagerUtil.TAG, "AlaramReceiver  start");
    }
}
