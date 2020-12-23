package demos.android.stormdzh.com.androiddemos.test.bubble;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 消息-气泡控件
 * @Author: dzh
 * @CreateDate: 2020-10-12 15:16
 */
public class MsgBubbleView extends RelativeLayout {


    private int dArrMarginRight; //箭头marginright
    private int dArrMarginLeft; //箭头marginleft
    private long lastShowLocalId; //该控件上次展示的id

    private TextView tvMsgBubbleText; //文本
    private ImageView msgBubbleAvatar; //头像
    private ImageView msgBubbleArrow;   //箭头
    private LinearLayout llCommonMsgBubble; //内容区域

    private CountDownTimer countDownTimer; //倒计时
    private View tvView;

    public MsgBubbleView(Context context) {
        this(context, null);
    }

    public MsgBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MsgBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        LayoutInflater.from(context).inflate(R.layout.view_redpoint_msg_bubble, this);

        tvMsgBubbleText = findViewById(R.id.tvMsgBubbleText);
        msgBubbleAvatar = findViewById(R.id.msgBubbleAvatar);
        msgBubbleArrow = findViewById(R.id.msgBubbleArrow);
        llCommonMsgBubble = findViewById(R.id.llCommonMsgBubble);

        countDownTimer = new CountDownTimer(5000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                cancelBubble(tvView);
            }
        };
    }


    /**
     * 设置数据
     */
    public void setData(View tvView) {
        this.tvView = tvView;
        setVisibility(VISIBLE);
        new MsgScaleAnimation(getContext()).msgBubbleEnterAnim(this, msgBubbleArrow);
        setBubble();

    }

    private void setBubble() {
        int [] location =new int[2];
        tvView.getLocationOnScreen(location);

        int width = this.getWidth();
        int height = this.getHeight();
        int left = this.getLeft();
        int top = this.getTop();

        int ax = left + (width / 2);
        int ay = top + (height / 2);

        int i = location[0] - ax;


        //设置箭头的位置
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) msgBubbleArrow.getLayoutParams();
        if (dArrMarginLeft == 0 && dArrMarginRight != 0) {
            layoutParams.addRule(ALIGN_RIGHT, llCommonMsgBubble.getId());
            layoutParams.setMargins(0, 0, dArrMarginRight, 0);
        } else if (dArrMarginLeft != 0 && dArrMarginRight == 0) {
            layoutParams.addRule(ALIGN_LEFT, llCommonMsgBubble.getId());
            layoutParams.setMargins(0, 0, dArrMarginLeft, 0);
        }
        msgBubbleArrow.setLayoutParams(layoutParams);
    }

    /**
     * 取消
     */
    public void cancelBubble(View tvView) {
        new MsgScaleAnimation(getContext()).msgBubbleExitAnim(this, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

}
