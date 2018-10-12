package demos.android.stormdzh.com.androiddemos.dragger2_test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import demos.android.stormdzh.com.androiddemos.dragger2_test.dragger.CommonComponent;
import demos.android.stormdzh.com.androiddemos.dragger2_test.dragger.CommonModule;
import demos.android.stormdzh.com.androiddemos.dragger2_test.dragger.DaggerCommonComponent;
import demos.android.stormdzh.com.androiddemos.dragger2_test.dragger.ICommonView;
import demos.android.stormdzh.com.androiddemos.dragger2_test.presenter.DraggerTest1Presenter;
import demos.android.stormdzh.com.androiddemos.dragger2_test.presenter.EntityTest;

/**
 * Dragger2Test
 * Created by a111 on 2018/10/12.
 */

public class Dragger2TestActivity extends AppCompatActivity implements ICommonView {

    @Inject
    DraggerTest1Presenter presenter;

    @Inject
    EntityTest entityTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        DaggerCommonComponent.
//                builder().
//                commonModule(new CommonModule(this)).
//                build().
//                inject(this);

        CommonComponent ad = DaggerCommonComponent.
                builder().
                commonModule(new CommonModule(this)).
                build();

        ad.inject(this);


        presenter.login("qqq");
        entityTest.printText("我是dragger2 打印出来的数据");
    }

    @Override
    public Context getContext() {
        return this;
    }
}
