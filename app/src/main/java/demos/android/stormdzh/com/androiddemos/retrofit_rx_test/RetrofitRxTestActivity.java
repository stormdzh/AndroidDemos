package demos.android.stormdzh.com.androiddemos.retrofit_rx_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.retrofit_rx_test.Interface.NetInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Retrofit+Rx
 * Created by a111 on 2018/10/11.
 */

public class RetrofitRxTestActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retrofit_rx_test);

        findViewById(R.id.tv_test_java).setOnClickListener(this);
        findViewById(R.id.tv_test_android).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test_java:
                testjava();
                break;
            case R.id.tv_test_android:
                testAndroid();
                break;
        }
    }

    private void testAndroid() {
        NetInterface dailyService = ApiManager.getInstence().getDailyService2();
//        Subscription subscription =
                dailyService.getTestString()
                //设置事件触发在非主线程
                .subscribeOn(Schedulers.io())
                //设置事件接受在UI线程以达到UI显示的目的
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {

                    @Override
                    public void onCompleted() {
                        Log.i("test", "请求完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("test", "请求失败");
                    }

                    @Override
                    public void onNext(Response<String> s) {
                        Log.i("test", "请求成功：" + s.body());
                    }
                });

        //绑定观察对象，注意在界面的ondestory或者onpouse方法中调用presenter.unsubcription();进行解绑避免内存泄漏
//        .addSubscription(subscription);
    }

    private void testjava() {

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
