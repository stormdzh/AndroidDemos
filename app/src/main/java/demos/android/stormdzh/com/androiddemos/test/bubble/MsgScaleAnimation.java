package demos.android.stormdzh.com.androiddemos.test.bubble;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;

/**
 * @Author: dzh
 * @CreateDate: 2020/10/13
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/10/13
 * @description:
 */
public class MsgScaleAnimation {

    private Context context;

    public MsgScaleAnimation(Context context) {
        this.context = context;
    }

    public void interMsgAnim(View startView, View endView, Interpolator interpolator) {

        Animation inScaleAnim = new ScaleAnimation(0, 1.0f, 0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        inScaleAnim.setDuration(600);//动画时间
        inScaleAnim.setRepeatCount(0);//动画的反复次数
        inScaleAnim.setInterpolator(interpolator); // 设置插入器
        inScaleAnim.setFillAfter(true);//设置为true，动画转化结束后被应用

        Animation outScaleAnim = new ScaleAnimation(1.0f, 0f, 1.0f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        outScaleAnim.setDuration(600);//动画时间
        outScaleAnim.setRepeatCount(0);//动画的反复次数
        outScaleAnim.setInterpolator(interpolator); // 设置插入器
        outScaleAnim.setFillAfter(true);//设置为true，动画转化结束后被应用


        startView.startAnimation(outScaleAnim);
        endView.startAnimation(inScaleAnim);

    }


    /**
     * 气泡进入动画
     */
    public void msgBubbleEnterAnim(View view, View msgBubbleArrow){
        Animation enterScaleAnim = new ScaleAnimation(0, 1.0f, 0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.8f, Animation.RELATIVE_TO_SELF, 0.9f);
        enterScaleAnim.setDuration(600);//动画时间
        enterScaleAnim.setRepeatCount(0);//动画的反复次数
//        enterScaleAnim.setInterpolator(interpolator); // 设置插入器
        enterScaleAnim.setFillAfter(true);//设置为true，动画转化结束后被应用
//        view.startAnimation(enterScaleAnim);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(600);//动画时间
        alphaAnimation.setRepeatCount(0);//动画的反复次数
        alphaAnimation.setFillAfter(true);//设置为true，动画转化结束后被应用

        //构造方法的入参如果是“true”，则代表使用默认的interpolator，如果是“false”则代表使用自定义interpolator
        AnimationSet animationSet =new AnimationSet(true);
        animationSet.addAnimation(enterScaleAnim);
        animationSet.addAnimation(alphaAnimation);

        view.startAnimation(animationSet);
//        msgBubbleArrow.startAnimation(animationSet);

    }

    /**
     * 气泡退出动画
     * @param view
     */
    public void msgBubbleExitAnim(View view, Animation.AnimationListener animationListener){
        Animation exitScaleAnim = new ScaleAnimation(1.0f, 0f, 1.0f, 0f,
                Animation.RELATIVE_TO_SELF, 0.8f, Animation.RELATIVE_TO_SELF, 0.9f);
        exitScaleAnim.setDuration(600);//动画时间
        exitScaleAnim.setRepeatCount(0);//动画的反复次数
//        exitScaleAnim.setInterpolator(interpolator); // 设置插入器
        exitScaleAnim.setFillAfter(true);//设置为true，动画转化结束后被应用
        exitScaleAnim.setAnimationListener(animationListener);
        view.startAnimation(exitScaleAnim);
    }



}
