package demos.android.stormdzh.com.androiddemos.retrofit_rx_test.Interface;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * 创建 用于描述网络请求 的接口
 * Created by a111 on 2018/10/11.
 */

public interface NetInterface {

    @GET("test.do?keyfrom=ddddddd")
    Call<String> getString();

    @GET("test.do?keyfrom=cccccccccccccccc")
    Call<ResponseBody> getCall();

    @GET("test.do?keyfrom=cccccccccccccccc")
    Observable<Response<String>> getTestString();


    //这里可以返回多种类型，我使用最简单的返回字符串

    // @GET注解的作用:采用Get方法发送网络请求
    // getCall() = 接收网络请求数据的方法
    // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>
}
