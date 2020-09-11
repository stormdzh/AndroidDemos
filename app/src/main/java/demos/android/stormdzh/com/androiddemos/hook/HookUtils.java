package demos.android.stormdzh.com.androiddemos.hook;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 代理
 * @Author: dzh
 * @CreateDate: 2020-08-06 18:26
 */
public class HookUtils {

    /**
     * 动态代理
     * @param context
     * @param view
     */
    public static void hookClick(Context context, View view) {

        Class<View> viewClass = View.class;

        try {
            Method getListenerInfo = viewClass.getDeclaredMethod("getListenerInfo");

            getListenerInfo.setAccessible(true);

            //获取到了View里面的mListenerInfo字段
            Object mListenerInfo = getListenerInfo.invoke(view);


            Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
            Field fieldClick = listenerInfoClz.getDeclaredField("mOnClickListener");
            //获取到之前设置的onclick事件
            final View.OnClickListener originOnClick = (View.OnClickListener) fieldClick.get(mListenerInfo);


            Object newClick = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{View.OnClickListener.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Log.d("hookClick", "点击事件被hook到了,我偷偷的加了点逻辑");//加入自己的逻辑
                    return method.invoke(originOnClick, args);
                }
            });


            fieldClick.set(mListenerInfo, newClick);


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }


    /**
     * 代理
     * @param context
     * @param view
     */
    public static void hookClickStatic(Context context, View view) {

        Class<View> viewClass = View.class;

        try {
            Method getListenerInfo = viewClass.getDeclaredMethod("getListenerInfo");

            getListenerInfo.setAccessible(true);

            //获取到了View里面的mListenerInfo字段
            Object mListenerInfo = getListenerInfo.invoke(view);


            Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
            Field fieldClick = listenerInfoClz.getDeclaredField("mOnClickListener");
            //获取到之前设置的onclick事件
            final View.OnClickListener originOnClick = (View.OnClickListener) fieldClick.get(mListenerInfo);

            //创建一个代理类
            ProxyOnClickListener proxyOnClickListener = new ProxyOnClickListener(originOnClick);

            //赋值
            fieldClick.set(mListenerInfo, proxyOnClickListener);


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    static class ProxyOnClickListener implements View.OnClickListener {

        View.OnClickListener mClickListener;

        public ProxyOnClickListener(View.OnClickListener oriLis) {
            this.mClickListener = oriLis;
        }

        @Override
        public void onClick(View v) {
            Log.d("hookClick", "静态代理被无情的拦截了！！！");//加入自己的逻辑
            mClickListener.onClick(v);
        }
    }

    ;
}
