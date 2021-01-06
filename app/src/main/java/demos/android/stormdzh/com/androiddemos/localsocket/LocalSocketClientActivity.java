package demos.android.stormdzh.com.androiddemos.localsocket;

import android.app.Activity;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStream;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: LocalSocketClient
 * @Author: dzh
 * @CreateDate: 2021-01-06 17:12
 */
public class LocalSocketClientActivity extends Activity implements View.OnClickListener {

    private EditText mEditText;

    private LocalSocket mSocket;
    private OutputStream mOut;
    private static final String SOCKET_NAME = "demos.android.stormdzh.com.androiddemos.localsocket";
    private static final String TAG = LocalSocketServerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_socket_client);
        connect();
        mEditText = (EditText) findViewById(R.id.input_msg);
        findViewById(R.id.replay_btn).setOnClickListener(this);
        findViewById(R.id.stop_btn).setOnClickListener(this);
    }

    private boolean connect() {
        if (mSocket != null) {
            return true;
        }
        try {
            mSocket = new LocalSocket();//创建LocalSocket，模拟客户端
            LocalSocketAddress address = new LocalSocketAddress(SOCKET_NAME,
                    LocalSocketAddress.Namespace.ABSTRACT);
            mSocket.connect(address);//连接TestLocalSocketServer
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    private boolean writeCommand(String cmdString) {
        final byte[] cmd = cmdString.getBytes();
        final int len = cmd.length;
        try {
            mOut = mSocket.getOutputStream();
            mOut.write(cmd, 0, len);
            Log.i(TAG, "ClientActivity write " + new String(cmd));
        } catch (IOException ex) {
            Log.e(TAG, "ClientActivity write error:" + ex.fillInStackTrace());
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.replay_btn:
                writeCommand(mEditText.getText().toString());
                break;
            case R.id.stop_btn:
                writeCommand("stop");
                break;
            default:
                break;
        }

    }
}
