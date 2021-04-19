package demos.android.stormdzh.com.androiddemos.group;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2021-04-19 14:55
 */
public class LinearViewGroupActivity extends Activity {

    private LinearViewGroup mLinearViewGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.avtivity_linearviewgroup);

        mLinearViewGroup = findViewById(R.id.mLinearViewGroup);



    }
}
