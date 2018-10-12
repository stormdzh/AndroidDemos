package demos.android.stormdzh.com.androiddemos.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by a111 on 2018/9/29.
 */

public class VolleyUtil {

    public static void requestNet(Context context, String tag, final HashMap<String, String> headers, String url, final OnNetListener netListener) {
        //1.创建出请求队列
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        //2.创建出来字符串请求对象： StringRequest
        /**
         * 1param: 请求方式 get/post
         * 2p:请求的url地址
         * 3p:请求成功后的接口回调
         * 4p:请求失败后回调
         * 5p:成功的监听，通过参数返回请求到的数据
         * 6p:失败的监听，失败在这里处理
         */
        StringRequest mStrReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (netListener != null)
                            netListener.onSucces(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (netListener != null)
                    netListener.onFail("请求失败");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };
        //设置Tag值
        mStrReq.setTag(tag);
        mRequestQueue.add(mStrReq);
    }
}
