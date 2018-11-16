package demos.android.stormdzh.com.androiddemos.catchdata;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.catchdata.entity.CatchDetaiEntity;
import demos.android.stormdzh.com.androiddemos.catchdata.entity.CatchList;
import demos.android.stormdzh.com.androiddemos.catchdata.entity.CatchListEntity;
import demos.android.stormdzh.com.androiddemos.net.OnNetListener;
import demos.android.stormdzh.com.androiddemos.net.VolleyUtil;
import demos.android.stormdzh.com.androiddemos.util.PermissionUtil;

/**
 * 尝试抓数据
 * Created by a111 on 2018/9/29.
 */

public class CatchDataActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tv_last;
    private List<CatchList> allList;
    private int curIndex;
    String url = "列表接口";
    HashMap<String, String> headers;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    String wid = getWordId(curIndex);
                    if (TextUtils.isEmpty(wid)) {
                        Toast.makeText(CatchDataActivity.this, "数据抓取完成", Toast.LENGTH_SHORT).show();
                        mHandler.removeMessages(100);
                        return;
                    }
                    getDetailNetData(wid);
                    break;
                //请求失败
                case 200:
                    int sp = getSp();
                    tv_last.setText("上次进度=》" + sp);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_data);
        curIndex = getSp();
        findViewById(R.id.tv_start).setOnClickListener(this);
        tv_last = findViewById(R.id.tv_last);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MOUNT_FORMAT_FILESYSTEMS}, 100);
//        PermissionUtil.requestPermissions(this, new String[]{"permission:android.permission.WRITE_EXTERNAL_STORAGE"}, 100);
    }

    @Override
    public void onClick(View v) {
        getListNetData();
    }


    private void getListNetData() {
        headers = new HashMap<>();
        headers.put("TOKEN", "aaa");
        VolleyUtil.requestNet(this, "CatchDataActivity-list", headers, url, new OnNetListener() {
            @Override
            public void onSucces(String result) {
                Toast.makeText(CatchDataActivity.this, result, Toast.LENGTH_SHORT).show();
                //解析数据、存到Excel中
                handlderList(result);
                writeFile(Environment.getExternalStorageDirectory().toString() + "/wordslist.txt", result + "\n");
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(CatchDataActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handlderList(String result) {
        Gson gson = new GsonBuilder().create();
        CatchListEntity catchListEntity = gson.fromJson(result, CatchListEntity.class);
        if (catchListEntity == null | catchListEntity.res == null) return;
        allList = catchListEntity.res;

//        Log.i("test","单词个数==》"+allList.size());

        mHandler.removeMessages(100);
        mHandler.sendEmptyMessageDelayed(100, 1000);

    }

    private void getDetailNetData(String wid) {

        String url="详情url";
        headers = new HashMap<>();
        headers.put("TOKEN", "token");
        VolleyUtil.requestNet(this, "CatchDataActivity-detail"+curIndex, headers, url, new OnNetListener() {
            @Override
            public void onSucces(String result) {
                Toast.makeText(CatchDataActivity.this, "详情成功=》" + curIndex, Toast.LENGTH_SHORT).show();
                Log.i("test", result);
                //解析数据、存到Excel中
                handlderDetail(result);
                savaSp(curIndex);
                curIndex++;
                mHandler.sendEmptyMessageDelayed(100, 2000);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(CatchDataActivity.this, "详情失败=》" + curIndex, Toast.LENGTH_SHORT).show();
                savaSp(curIndex);
//                curIndex++;
                mHandler.sendEmptyMessageDelayed(200, 3000);
            }
        });
    }

    private void handlderDetail(String result) {
        writeFile(Environment.getExternalStorageDirectory().toString() + "/wordsdetail.txt", result + "\n\n");
//        Gson gson = new GsonBuilder().create();
//        CatchDetaiEntity detail = gson.fromJson(result, CatchDetaiEntity.class);
//        Util.getInstance().savaData2Excel(curIndex, detail.res.name, result);
        String postUrl = "post url";
        TreeMap<String, String> params = new TreeMap<>();
        params.put("text", result);

        VolleyUtil.postNet(this, "post_add"+curIndex, new HashMap<String, String>(), postUrl, params, new OnNetListener() {

            @Override
            public void onSucces(String result) {
             Log.i("test","onSucces");
            }

            @Override
            public void onFail(String msg) {
                Log.i("test","onFail");
            }
        });

    }


    private String getWordId(int index) {
        if (allList == null || allList.size() <= 0)
            return "";
        if (index >= allList.size()) return "";
//        if (index > 5) return "";
        return allList.get(index).word_id;
    }

    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.removeMessages(100);
        }
        super.onDestroy();
    }


    private void writeFile(String path, String data) {
        File file = new File(path);

        //if file doesnt exists, then create it
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //true = append file
        FileWriter fileWritter = null;
        try {
            fileWritter = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWritter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void savaSp(int index) {
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("index", index);
        editor.commit();
    }

    private int getSp() {
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        int name = preferences.getInt("index", 0);
        return name;
    }
}
