package demos.android.stormdzh.com.androiddemos.shchema;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 描述
 * @Author: duzhenhua3
 * @CreateDate: 7/12/21 8:15 PM
 */
public class SchemaSendActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acitivity_schemasend);

        findViewById(R.id.tvTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ss="tttt://abc/test";
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ss));
                startActivity(intent);
            }
        });
    }
}
