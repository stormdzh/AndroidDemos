package demos.android.stormdzh.com.androiddemos.shchema;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.Set;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 描述
 * @Author: duzhenhua3
 * @CreateDate: 7/12/21 8:15 PM
 */
public class ReciveActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acitivity_schemareceived);


        String scheme = getIntent().getScheme();
        String ds = getIntent().getDataString();
        String action = getIntent().getAction();
        Set<String> categories = getIntent().getCategories();
        Uri data = getIntent().getData();
        Log.i("ddd",ds);


        findViewById(R.id.tvTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setAction("com.stommdzn.text.web.Enter_START");
                startActivity(intent);
            }
        });


    }
}
