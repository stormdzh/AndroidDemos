package demos.android.stormdzh.com.androiddemos.dragger2_test.presenter;

import android.util.Log;

import javax.inject.Inject;

import dagger.Provides;

/**
 * Created by a111 on 2018/10/12.
 */

public class EntityTest {

    @Inject
    public EntityTest() {

    }

    public void printText(String text) {
        Log.i("test", text);
    }
}
