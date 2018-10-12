package demos.android.stormdzh.com.androiddemos.retrofit_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.retrofit_test.Interface.NetInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Retrofit 使用
 * Created by a111 on 2018/10/11.
 */

public class RetrofitTestActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);

        findViewById(R.id.tv_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        NetInterface dailyService = ApiManager.getInstence().getDailyService();

        Call<String> call = dailyService.getString();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("test", "请求成功：" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("test", "请求失败");
            }
        });
    }
}
