package demos.android.stormdzh.com.androiddemos.notify;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2020-08-11 15:01
 */
public class NotifyActivity extends Activity implements View.OnClickListener {

    private NotificationUtil mNotificationUtil;
    private DigitPointView mDigitPointView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        findViewById(R.id.tvStart).setOnClickListener(this);
        findViewById(R.id.tvStop).setOnClickListener(this);

        mNotificationUtil=new NotificationUtil();
        mNotificationUtil.init(this);

        mDigitPointView=findViewById(R.id.mDigitPointView);

        ObserverData observerData=new ObserverData();
        observerData.showType=1;
        observerData.unRead=100;
        mDigitPointView.setData(observerData);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvStart:
                mNotificationUtil.showBroacast();
                break;
            case R.id.tvStop:
                mNotificationUtil.cancel();
                break;
        }
    }
}
