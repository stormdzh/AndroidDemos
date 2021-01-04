package demos.android.stormdzh.com.androiddemos.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2021-01-04 15:25
 */
public class NormalBroadCastActivity extends Activity {

    private TextView tvReceiver;
    private final String ACTION_TEST = "demos.android.stormdzh.com.androiddemos.broadcast.NormalBroadCastActivity.TestBroadcastReceiver";
    private int index =0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.adapter_broadcast_normal);

        tvReceiver = findViewById(R.id.tvReceiver);

        findViewById(R.id.tvSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send("参数："+index);
                index++;
            }
        });

        //注册广播
        registerBroadCast();
    }

    private void send(String param) {

        try {
            Intent intent = new Intent();
            intent.putExtra("param", param);
            intent.setAction(ACTION_TEST);
            sendBroadcast(intent);//发送标准广播
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TestBroadcastReceiver mTestBroadcastReceiver;

    private void registerBroadCast() {
        try {
            //注册点击事件广播
            IntentFilter filter = new IntentFilter(ACTION_TEST);
            filter.setPriority(Integer.MAX_VALUE); //设置级别
            mTestBroadcastReceiver = new TestBroadcastReceiver();
            registerReceiver(mTestBroadcastReceiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class TestBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent == null) return;
            if (!TextUtils.equals(intent.getAction(), ACTION_TEST)) {
                return;
            }
            final String param = intent.getStringExtra("param");
            Log.i("dzh", " 广播里面收到参数：" + param);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvReceiver.setText(" 广播里面收到参数：" + param);
                }
            });

        }
    }
}
