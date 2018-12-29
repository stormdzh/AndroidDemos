package demos.android.stormdzh.com.androiddemos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import cafe.adriel.androidaudioconverter.AndroidAudioConverter;
import cafe.adriel.androidaudioconverter.callback.ILoadCallback;

public class MyApplication extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        AndroidAudioConverter.load(this, new ILoadCallback() {
            @Override
            public void onSuccess() {
                // Great!
                Log.i("test", "支持 ffmpeg");
            }

            @Override
            public void onFailure(Exception error) {
                // FFmpeg is not supported by device
                Log.i("test", "FFmpeg is not supported by device");
            }
        });
    }

    public static Context  getContext(){
        return mContext;
    }
}
