package demos.android.stormdzh.com.androiddemos.jobservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.Calendar;

/**
 * Created by lijinhua on 2017/6/12.
 */

public class AlarmManagerUtil {

    /**
     * Log tag ：DaemonService
     */
    public static final String TAG = "AlarmService";

    public static final int REQUEST_CODE = 200;
    public static final int HOURE_20 = 20;
    public static final int MINUTE = 00;

    public final static int JOB_UN_STUDY_ID = 201;

    public static boolean isTest = false;
    public static long testMillis = 1 * 10 * 1000;


    //注册后一天没开始学习（只出现一次了）（学习后关闭）
    public static void resSetUnStudyAlarm(Context context, long second) {

        AlarmManager studyManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar currentCalendar = Calendar.getInstance();
//        Calendar newCalendar = Calendar.getInstance();
//        newCalendar.set(currentCalendar.get(Calendar.YEAR)
//                , currentCalendar.get(Calendar.MONTH)
//                , currentCalendar.get(Calendar.DAY_OF_MONTH)
//                , HOURE_20
//                , MINUTE
//                , 10);
//        if (isFirst) {
//            newCalendar.add(Calendar.DATE, 1);
//        } else {
//            newCalendar.add(Calendar.DATE, 2);
//        }

//        long tomorrow_20_00_Mills = newCalendar.getTimeInMillis(); // 1970年到明天的20:00:10秒的毫秒数
//        long currentMills = currentCalendar.getTimeInMillis(); // 当时的毫秒数
//        long delayMills = Math.abs(tomorrow_20_00_Mills - currentMills);

        long delayMills = second * 1000;
        if (isTest) {
            delayMills = testMillis;
        }

        Intent alarmIntent = new Intent();
        alarmIntent.setAction(AlaramReceiver.UN_STUDY_WAKE_ACTION);
        PendingIntent operation = PendingIntent.getBroadcast(context, REQUEST_CODE, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            // 4.4以下
            //  if (isFirst) {
            studyManager.set(AlarmManager.RTC_WAKEUP, currentCalendar.getTimeInMillis() + delayMills, operation);
            // }
        } else if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)) {
            // 4.4 - 5.0之间
            studyManager.setExact(AlarmManager.RTC_WAKEUP, currentCalendar.getTimeInMillis() + delayMills, operation);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // 5.0 - 6.0
            JobScheduler mJobScheduler = (JobScheduler) context.getApplicationContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo.Builder builder = new JobInfo.Builder(JOB_UN_STUDY_ID,
                    new ComponentName(context.getPackageName(), UnStudyJobService.class.getName()));
            builder.setMinimumLatency(delayMills);
            builder.setPersisted(true);  //设置设备重启后，是否重新执行任务
            if (mJobScheduler.schedule(builder.build()) <= 0) {
                //If something goes wrong
                Log.i(TAG, "start UnStudyJobService fail");
            } else {
                Log.i(TAG, "start UnStudyJobService success");
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // 大于等于6.0
            studyManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, currentCalendar.getTimeInMillis() + delayMills, operation);
            // registerManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, newCalendar.getTimeInMillis(), operation);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            JobScheduler mJobScheduler = (JobScheduler) context.getApplicationContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo.Builder builder = new JobInfo.Builder(JOB_UN_STUDY_ID,
                    new ComponentName(context.getPackageName(), UnStudyJobService.class.getName()));
            builder.setMinimumLatency(delayMills);
            builder.setPersisted(true);  //设置设备重启后，是否重新执行任务
            if (mJobScheduler.schedule(builder.build()) <= 0) {
                //If something goes wrong
                Log.i(TAG, "start UnStudyJobService fail");
            } else {
                Log.i(TAG, "start UnStudyJobService success");
            }
        }
    }


    //关闭未学习闹钟
    public static void cancelUnStudyAlarm(Context context) {
        Log.i(TAG, "cancelUnStudyAlarm");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            JobScheduler mJobScheduler = (JobScheduler) context.getApplicationContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
            mJobScheduler.cancel(JOB_UN_STUDY_ID);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            JobScheduler mJobScheduler = (JobScheduler) context.getApplicationContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
            mJobScheduler.cancel(JOB_UN_STUDY_ID);
        } else {

            AlarmManager registerManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent alarmIntent = new Intent();
            alarmIntent.setAction(AlaramReceiver.UN_STUDY_WAKE_ACTION);
            PendingIntent operation = PendingIntent.getBroadcast(context, REQUEST_CODE, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            //关闭闹钟
            if (operation != null) {
                registerManager.cancel(operation);
            }

        }
//        AlarmPreference.saveNeedStudyAlarm(false);

    }

}
