package demos.android.stormdzh.com.androiddemos.hook;

import android.util.Log;

import com.taobao.android.dexposed.DexposedBridge;
import com.taobao.android.dexposed.XC_MethodHook;

/**
 * @Description: epic
 * @Author: dzh
 * @CreateDate: 2020-08-06 19:27
 */
public class EpicUtils {

    private static String TAG = "hookClick";


    static class  ThreadMethodHook extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            super.beforeHookedMethod(param);
            Thread t = (Thread) param.thisObject;
            Log.i(TAG, "thread:" + t + ", started..");
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            super.afterHookedMethod(param);
            Thread t = (Thread) param.thisObject;
            Log.i(TAG, "thread:" + t + ", exit..");
        }
    }

    public static void hook() {

        DexposedBridge.hookAllConstructors(Thread.class, new

                XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Thread thread = (Thread) param.thisObject;
                        Class<?> clazz = thread.getClass();
                        if (clazz != Thread.class) {
                            Log.d(TAG, "found class extend Thread:" + clazz);
                            DexposedBridge.findAndHookMethod(clazz, "run", new ThreadMethodHook());
                        }
                        Log.d(TAG, "Thread: " + thread.getName() + " class:" + thread.getClass() + " is created.");
                    }
                });

        DexposedBridge.findAndHookMethod(Thread.class, "run", new ThreadMethodHook());
    }
}
