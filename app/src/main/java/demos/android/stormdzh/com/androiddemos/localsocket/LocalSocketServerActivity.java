package demos.android.stormdzh.com.androiddemos.localsocket;

import android.app.Activity;
import android.content.Intent;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: LocalSocketServer
 * @Author: dzh
 * @CreateDate: 2021-01-06 16:53
 */
public class LocalSocketServerActivity extends Activity {

    private TextView tvMsg;

    private LocalServerSocket mServerSocket = null;
    private LocalSocket mSocket = null;

    private InputStream mInputStream = null;
    private static final String SOCKET_NAME = "demos.android.stormdzh.com.androiddemos.localsocket";
    private static final String TAG = LocalSocketServerActivity.class.getSimpleName();


    private final Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            String dispMesg = (String) msg.obj;
            tvMsg.setText(dispMesg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_local_socket_server);

        tvMsg = findViewById(R.id.tvMsg);

        createServerSocket();// 创建LocalServerSocket

        //必须要在子线程里接收消息
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void run() {
                acceptMsg();
            }
        }).start();

        findViewById(R.id.tvToSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocalSocketServerActivity.this,LocalSocketClientActivity.class));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void acceptMsg() {
        try {
            mSocket = mServerSocket.accept();//accept是个阻塞方法，这就是必须要在子线程接收消息的原因。
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        while (true) {
            try {
                byte[] buffer = new byte[1024];
                mInputStream = mSocket.getInputStream();
                int count = mInputStream.read(buffer);
                String key = new String(Arrays.copyOfRange(buffer, 0, count));
                Log.d(TAG, "ServerActivity mSocketOutStream==" + key);
                if ("stop".equals(key)) {
                    closeSocketResource();
                    break;
                }
                Message msg = mHandler.obtainMessage();
                msg.obj = key;
                msg.sendToTarget();
            } catch (IOException e) {
                Log.d(TAG, "exception==" + e.fillInStackTrace().getMessage());
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void closeSocketResource() {
        closeSlient(mInputStream);
        closeSlient(mSocket);
        try {
            if (mServerSocket != null) {
                mServerSocket.close();
                mServerSocket = null;
            }
        } catch (IOException ex) {
            Log.e(TAG, "Failed closing ServerSocket" + ex.fillInStackTrace());
        }
    }

    private void closeSlient(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
                closeable = null;
            }
        } catch (IOException ex) {
            Log.e(TAG, "Failed closing : " + closeable);
        }
    }

    private void createServerSocket() {

        if (mServerSocket == null) {
            try {
                /**注意这里new出LocalServerSocket的同时，系统层已经同步做了bind和listen。
                 * 我们看看new的过程：
                 * public LocalServerSocket(String name) throws IOException {
                 *       impl = new LocalSocketImpl();
                 *       impl.create(LocalSocket.SOCKET_STREAM);
                 *       localAddress = new LocalSocketAddress(name);
                 *       impl.bind(localAddress);
                 *       impl.listen(LISTEN_BACKLOG);
                 * }
                 */
                mServerSocket = new LocalServerSocket(SOCKET_NAME);
            } catch (IOException ex) {
            }
        }
    }
}
