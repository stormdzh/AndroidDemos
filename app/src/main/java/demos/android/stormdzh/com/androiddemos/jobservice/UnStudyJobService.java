package demos.android.stormdzh.com.androiddemos.jobservice;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Created by lijinhua on 2017/6/12.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class UnStudyJobService extends JobService {

    /**
     * @param params 如果返回值是false,系统假设这个方法返回时任务已经执行完毕。如果返回值是true,那么系统假定这个任务正要被执行，
     *               执行任务的重担就落在了你的肩上。当任务执行完毕时你需要调用jobFinished(JobParameters params, boolean needsRescheduled)来通知系统
     * @return
     */
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(AlarmManagerUtil.TAG, "UnStudyJobService onStartJob");
        JobScheduler mJobScheduler = (JobScheduler) getApplicationContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        mJobScheduler.cancel(AlarmManagerUtil.JOB_UN_STUDY_ID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            GeiTuiNotification.getInstance().showNewAlarmNotify(getApplicationContext().getString(R.string.alarm_un_study_msg_title),
//                    getApplicationContext().getString(R.string.alarm_un_study_msg_content),  GeiTuiType.LOCAL_PUSH_UNSTUDY);
            //AlarmManagerUtil.resSetUnStudyAlarm(getApplicationContext(), false);
            Log.i(AlarmManagerUtil.TAG, "大于26~~~~~~UnStudyJ26obService");
        } else {
            // 显示通条栏
            Logger.i(AlarmManagerUtil.TAG, "小于26~~~~~~UnStudyJ26obService");
            sendBroadcast(new Intent(AlaramReceiver.UN_STUDY_WAKE_ACTION));
        }
        return true;
    }

    /**
     * 当系统接收到一个取消请求时，系统会调用onStopJob(JobParameters params)方法取消正在等待执行的任务。
     * 很重要的一点是如果onStartJob(JobParameters params)返回false,
     * 那么系统假定在接收到一个取消请求时已经没有正在运行的任务。换句话说，onStopJob(JobParameters params)在这种情况下不会被调用
     *
     * @param params
     * @return
     */
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(AlarmManagerUtil.TAG, "UnStudyJobService onStopJob");
        return false;
    }
}
