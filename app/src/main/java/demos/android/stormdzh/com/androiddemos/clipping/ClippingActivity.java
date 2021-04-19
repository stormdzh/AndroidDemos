package demos.android.stormdzh.com.androiddemos.clipping;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: view裁剪
 * @Author: dzh
 * @CreateDate: 2021-04-19 14:06
 */
public class ClippingActivity extends Activity {

    private TextView tvCircle1, tvCircle2;
    private ImageView tvCircle3, tvCircle4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clipping);


        tvCircle1 =findViewById(R.id.tv_circle_1);
        tvCircle2 =findViewById(R.id.tv_circle_2);
        tvCircle3 =findViewById(R.id.tv_circle_3);
        tvCircle4 =findViewById(R.id.tv_circle_4);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initCircle();
        }
    }

    //设置outline 裁剪
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initCircle() {
        //获取outline
        ViewOutlineProvider viewOutlineProvider1 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //修改outline为特定形状,上下左右相当于padding
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 40);
            }
        };
        //获取outline
        ViewOutlineProvider viewOutlineProvider2 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //修改outline为特定形状
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        };
        //重新设置形状
        tvCircle1.setOutlineProvider(viewOutlineProvider1);
        tvCircle2.setOutlineProvider(viewOutlineProvider2);
        tvCircle3.setOutlineProvider(viewOutlineProvider1);
        tvCircle4.setOutlineProvider(viewOutlineProvider2);
        //添加背景或者是Imageview的时候失效，添加如下设置
        tvCircle1.setClipToOutline(true);
        tvCircle2.setClipToOutline(true);
        tvCircle3.setClipToOutline(true);
        tvCircle4.setClipToOutline(true);
    }
}
