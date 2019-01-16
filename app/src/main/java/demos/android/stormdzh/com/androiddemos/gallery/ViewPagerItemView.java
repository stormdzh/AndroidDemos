package demos.android.stormdzh.com.androiddemos.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import demos.android.stormdzh.com.androiddemos.R;

public class ViewPagerItemView extends RelativeLayout {

    private ImageView iv_big;

    public ViewPagerItemView(Context context) {
        this(context, null);
    }

    public ViewPagerItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.adapter_gallery_recycleview, this);

        iv_big = findViewById(R.id.iv_big);
    }


    public void setVoiceVisibility(Boolean b) {

    }

    public void setData(int resid) {
        iv_big.setImageResource(resid);
    }
}
