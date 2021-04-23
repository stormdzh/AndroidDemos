package demos.android.stormdzh.com.androiddemos.bitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.io.InputStream;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: bitmap 的使用 inmaap、
 * @Author: dzh
 * @CreateDate: 2021/4/23 10:00 AM
 */
public class BitmapActivity extends Activity {

    private LargeBitmapView mLargeBitmapView;

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        mLargeBitmapView = findViewById(R.id.mLargeBitmapView);
        imageView = findViewById(R.id.imageView);

        //图片控制大小 和 质量
        bitmapCompress();

        //加载大图
        loadLargeImage();
        
        //测试inBitmap
        inbitMap();

    }

    private void inbitMap() {
    }

    private void loadLargeImage() {
        InputStream is = null;
        try {
//            is = getResources().getAssets().openFd("picture/earth.jpeg").createInputStream();
//            is = getResources().getAssets().open("picture/earth.jpeg");
//            需要放一张大图
            mLargeBitmapView.setInputStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void bitmapCompress() {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.img_test_face, opts);
        opts.inSampleSize = 2;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        opts.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_test_face, opts);
        imageView.setImageBitmap(bitmap);
    }


}
