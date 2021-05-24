package demos.android.stormdzh.com.androiddemos.jobservice;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2020-12-23 18:24
 */
public class JobServiceActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_job_service);

        findViewById(R.id.tvStartJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManagerUtil.resSetUnStudyAlarm(JobServiceActivity.this,20);
            }
        });

        TestLoadStep step =new TestLoadStep();
    }



}
