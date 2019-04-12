package demos.android.stormdzh.com.androiddemos.facepic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import demos.android.stormdzh.com.androiddemos.R;

public class FacePicActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivFace;
    Bitmap grayBitmap;

    private FacePicUtil facePicUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_pic);

        facePicUtil = new FacePicUtil();

        ivFace = findViewById(R.id.ivFace);

        ivFace.setImageResource(R.drawable.img_test_face);

        findViewById(R.id.btnGray).setOnClickListener(this);
        findViewById(R.id.btnZero).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGray:
                Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.img_test_face);
                grayBitmap = facePicUtil.convert2Gray(bitmapOrg);
                ivFace.setImageBitmap(grayBitmap);
                break;
            case R.id.btnZero:
                if (grayBitmap == null) {
                    Toast.makeText(this, "轻先处理了灰度图片", Toast.LENGTH_SHORT).show();
                    return;
                }
//                Bitmap bitmap = facePicUtil.zeroAndOne(grayBitmap);
                Bitmap bitmap = facePicUtil.convertToBMW(grayBitmap,80);
                ivFace.setImageBitmap(bitmap);

                break;
        }
    }
}
