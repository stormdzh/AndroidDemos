package demos.android.stormdzh.com.androiddemos.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.test.entity.DigitRedPointMsgEntity;

/**
 * @Description: 测试消息的层级
 * @Author: dzh
 * @CreateDate: 2020-09-21 16:36
 */
public class DigitMsgActivity extends Activity implements View.OnClickListener {
    String testData = "{\"functionId\":\"1004\",\"info\":{\"isShow\":false,\"totalUnRead\":0,\"unRead\":0},\"list\":[{\"functionId\":\"105\",\"info\":{\"isShow\":false,\"time\":\"3\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":4},{\"functionId\":\"106\",\"info\":{\"isShow\":false,\"time\":\"3\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":3},{\"functionId\":\"107\",\"info\":{\"isShow\":false,\"time\":\"0\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":4},{\"functionId\":\"108\",\"info\":{\"isShow\":false,\"time\":\"0\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":4},{\"functionId\":\"109\",\"info\":{\"isShow\":false,\"time\":\"0\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":4},{\"functionId\":\"110\",\"info\":{\"isShow\":false,\"time\":\"0\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":4},{\"functionId\":\"111\",\"info\":{\"isShow\":false,\"time\":\"0\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":4},{\"functionId\":\"112\",\"info\":{\"isShow\":false,\"time\":\"0\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":4},{\"functionId\":\"114\",\"info\":{\"isShow\":false,\"time\":\"0\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":4},{\"functionId\":\"115\",\"info\":{\"isShow\":false,\"time\":\"0\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":4},{\"functionId\":\"116\",\"info\":{\"isShow\":false,\"time\":\"0\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":4},{\"functionId\":\"117\",\"info\":{\"isShow\":false,\"time\":\"0\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"1004\",\"showType\":4},{\"functionId\":\"-1000\",\"info\":{\"isShow\":false,\"totalUnRead\":0,\"unRead\":0},\"list\":[{\"functionId\":\"101\",\"info\":{\"isShow\":true,\"time\":\"5\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"-1000\",\"showType\":4},{\"functionId\":\"102\",\"info\":{\"isShow\":true,\"time\":\"3\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"-1000\",\"showType\":3},{\"functionId\":\"103\",\"info\":{\"isShow\":true,\"time\":\"2\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"-1000\",\"showType\":3},{\"functionId\":\"104\",\"info\":{\"isShow\":true,\"time\":\"1\",\"totalUnRead\":0,\"unRead\":0},\"pid\":\"-1000\",\"showType\":3}],\"pid\":\"1004\",\"showType\":4}],\"pid\":\"-1\",\"showType\":4}";
    private Gson gson;


    DigitRedPointMsgEntity mDigitRedPointMsgEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_digit_msg);
        initData();

        findViewById(R.id.btnId).setOnClickListener(this);
        findViewById(R.id.btnPid).setOnClickListener(this);
    }

    private void initData() {
        gson = new GsonBuilder().create();
        mDigitRedPointMsgEntity = gson.fromJson(testData, DigitRedPointMsgEntity.class);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnId:
                DigitRedPointMsgEntity digitRedPointMsgEntity = queryId(mDigitRedPointMsgEntity, "101");
                if (digitRedPointMsgEntity == null) {
                    Log.i("redpoint", "没有查询到结果");
                } else {
                    Log.i("redpoint", "查询到的结果：" + digitRedPointMsgEntity.functionId);
                }
                break;
            case R.id.btnPid:
                queryPid("101");
                break;
        }
    }

    private void queryPid(String pid) {

    }

    /**
     * 递归查找id
     * @param msgEntity
     * @param id
     * @return
     */
    private DigitRedPointMsgEntity queryId(DigitRedPointMsgEntity msgEntity, String id) {
        if (msgEntity == null) return null;
        if (TextUtils.equals(msgEntity.functionId, id)) {
            return msgEntity;
        } else {
            if (msgEntity.list != null) {
                for (DigitRedPointMsgEntity digitRedPointMsgEntity : msgEntity.list) {
                    DigitRedPointMsgEntity digitRedPointMsgEntity1 = queryId(digitRedPointMsgEntity, id);
                    if (digitRedPointMsgEntity1 == null)
                        continue;

                    return digitRedPointMsgEntity1;

                }
            }
            return null;
        }

    }
}
