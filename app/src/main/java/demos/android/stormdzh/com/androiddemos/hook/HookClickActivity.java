package demos.android.stormdzh.com.androiddemos.hook;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2020-08-06 18:21
 */
public class HookClickActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook_click);


        Button viewById = findViewById(R.id.btnClick);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HookClickActivity.this,"轻查看点击log",Toast.LENGTH_SHORT).show();
            }
        });
//        HookUtils.hookClick(this,viewById);
        HookUtils.hookClickStatic(this,viewById);


//        EpicUtils.hook();
    }
}
