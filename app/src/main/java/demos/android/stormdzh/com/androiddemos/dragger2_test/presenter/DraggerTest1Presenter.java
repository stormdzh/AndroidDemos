package demos.android.stormdzh.com.androiddemos.dragger2_test.presenter;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import demos.android.stormdzh.com.androiddemos.dragger2_test.dragger.ICommonView;

/**
 * Created by a111 on 2018/10/12.
 */

public class DraggerTest1Presenter {

    ICommonView iView;

    @Inject
    public DraggerTest1Presenter(ICommonView iView) {

        this.iView = iView;
    }

    public void login(String adc) {

        Context mContext = iView.getContext();
        Toast.makeText(mContext, "login......", Toast.LENGTH_SHORT).show();
    }

}
