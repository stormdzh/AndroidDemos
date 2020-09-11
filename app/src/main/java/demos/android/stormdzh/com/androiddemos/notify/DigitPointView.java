package demos.android.stormdzh.com.androiddemos.notify;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.util.AppUtil;

/**
 * @Description: 数字红点控件
 * @Author: dzh
 * @CreateDate: 2020-08-26 11:49
 */
public class DigitPointView extends AppCompatTextView {

    public DigitPointView(Context context) {
        this(context, null);
    }

    public DigitPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DigitPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void init(Context context) {
        setTextColor(Color.WHITE);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
    }

    /**
     * 设置数据
     *
     * @param data ObserverData
     */
    public void setData(ObserverData data) {

        if (data == null) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);
        if (data.showType == 1) { //展示类型1数字红点 2小圆点
            int pxValue = AppUtil.dip2px(getContext(), 12);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
            layoutParams.height = pxValue;
            layoutParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
            setLayoutParams(layoutParams);

            setText(data.unRead > 99 ? "99+" : String.valueOf(data.unRead));
        } else {
            int pxValue = AppUtil.dip2px(getContext(), 8);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
            layoutParams.height = pxValue;
            layoutParams.width = pxValue;
            setLayoutParams(layoutParams);
        }
    }

}
