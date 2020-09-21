package demos.android.stormdzh.com.androiddemos.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: handler通信案例
 * @Author: dzh
 * @CreateDate: 2020-09-11 10:29
 */
public class HandlerActivity extends Activity implements View.OnClickListener {


    private TextView tvResult;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Object obj = msg.obj;
            Toast.makeText(HandlerActivity.this, (String) obj, Toast.LENGTH_SHORT).show();
            tvResult.setText((String) obj);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler);

        tvResult = findViewById(R.id.tvResult);
        findViewById(R.id.MainToMain).setOnClickListener(this);
        findViewById(R.id.childToMain).setOnClickListener(this);
        findViewById(R.id.childToChild).setOnClickListener(this);
        findViewById(R.id.childToChild2).setOnClickListener(this);
        findViewById(R.id.MainToChild).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.MainToMain: //主线程通知主线程
                Message msg = Message.obtain();
                msg.what = 0;
                msg.obj = "主线程通知主线程";
                mHandler.sendMessage(msg);
                break;
            case R.id.childToMain:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        msg.what = 0;
                        msg.obj = "子线程通知主线程";
                        mHandler.sendMessage(msg);
                    }
                }).start();

                break;
            case R.id.childToChild: //子线程通知子线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        @SuppressLint("HandlerLeak") Handler childHandler = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);

                                Object obj = msg.obj;
                                Toast.makeText(HandlerActivity.this, (String) obj, Toast.LENGTH_SHORT).show();
                                tvResult.setText((String) obj);
                            }
                        };

                        Message msg = Message.obtain();
                        msg.what = 0;
                        msg.obj = "子线程通知子线程";
                        childHandler.sendMessage(msg);
                        Looper.loop();
                    }
                }).start();

                break;
            case R.id.childToChild2: //子线程通知子线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        @SuppressLint("HandlerLeak") Handler childHandler = new Handler(getMainLooper()) {
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);

                                Object obj = msg.obj;
                                Toast.makeText(HandlerActivity.this, (String) obj, Toast.LENGTH_SHORT).show();
                                tvResult.setText((String) obj);
                            }
                        };

                        Message msg = Message.obtain();
                        msg.what = 0;
                        msg.obj = "子线程通知子线程";
                        childHandler.sendMessage(msg);
                    }
                }).start();

                break;
            case R.id.MainToChild: //主线程通知子线程
                new Thread(new Runnable() {
                    @SuppressLint("HandlerLeak")
                    @Override
                    public void run() {
                        Looper.prepare();
                        minToChilddHandler = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);

                                Object obj = msg.obj;
                                Toast.makeText(HandlerActivity.this, (String) obj, Toast.LENGTH_SHORT).show();
                                tvResult.setText((String) obj);
                            }
                        };

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Message msg = Message.obtain();
                                msg.what = 0;
                                msg.obj = "主线程通知子线程";
                                minToChilddHandler.sendMessage(msg);
                            }
                        });

                        Looper.loop();
                    }
                }).start();
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    Handler minToChilddHandler;
}
