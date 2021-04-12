package demos.android.stormdzh.com.androiddemos.gson;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2021-04-02 16:26
 */
public class GsonTestActivity extends Activity {

    String jstr="";
    Gson gson =new GsonBuilder().create();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gson_test);

//        jstr =gson.toJson(new TGBean());
        jstr="{\"a\":22,\"age\":18,\"name\":\"isNama\"}";
        Log.i("dzh",jstr);
    }

    public void testJson(View v){

        TGBean bean=gson.fromJson(jstr,TGBean.class);

        Log.i("dzh",bean.name);
    }
}
