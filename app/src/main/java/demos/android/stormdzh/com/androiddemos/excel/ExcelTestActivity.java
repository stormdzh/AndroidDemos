package demos.android.stormdzh.com.androiddemos.excel;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.util.PermissionUtil;
import jxl.write.WriteException;

/**
 * 测试Excel
 * Created by a111 on 2018/9/29.
 */

public class ExcelTestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_content;
    private String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_excel);

        tv_content = findViewById(R.id.tv_content);
        findViewById(R.id.tvAdd).setOnClickListener(this);
        findViewById(R.id.tvQuery).setOnClickListener(this);

        path = Environment.getExternalStorageDirectory().toString() + "/test.xls";///storage/sdcard0

        PermissionUtil.requestPermissions(this, new String[]{"permission:android.permission.WRITE_EXTERNAL_STORAGE"}, 100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAdd:
                add();
                break;
            case R.id.tvQuery:
                read();
                break;
        }
    }

    private void read() {

        String result = "";
        try {
            result = ExcelUtil.readExcel(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_content.setText(result);
    }

    private void add() {

        try {
            ExcelUtil.addExcel(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
