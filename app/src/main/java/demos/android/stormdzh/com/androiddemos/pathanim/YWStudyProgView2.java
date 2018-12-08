package demos.android.stormdzh.com.androiddemos.pathanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.util.AppUtil;

/**
 * 简单的动画曲线
 */
public class YWStudyProgView2 extends RelativeLayout {

    Paint paint = new Paint(); //北京画笔
    Paint paintAnim = new Paint(); //动画画笔

    Path mPathAnim = new Path();
    Path mPath = new Path();

    private ObjectAnimator pathAnim; // 路径动画


    private float inCreate;

    public YWStudyProgView2(@NonNull Context context) {
        this(context, null);
    }

    public YWStudyProgView2(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YWStudyProgView2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_pathanim_layout2, this);


        int fullLineWidth = AppUtil.dip2px(getContext(), 8);
        int dashedLineWidth = AppUtil.dip2px(getContext(), 30);
        DashPathEffect dpe = new DashPathEffect(new float[]{fullLineWidth, dashedLineWidth}, 0);
//        int strokeWidth = dip2px(lineStrokeWidthHalf * 2);
        int strokeWidth = AppUtil.dip2px(context, 10);

        paint.setPathEffect(dpe);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.parseColor("#dcdcdc"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);

        paintAnim.setPathEffect(dpe);
        paintAnim.setStrokeWidth(strokeWidth);
        paintAnim.setColor(Color.RED);
        paintAnim.setStyle(Paint.Style.STROKE);
        paintAnim.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        drawPathBg(canvas);

        drawPathProg(canvas);

        super.dispatchDraw(canvas);
    }

    float oldx = 0;
    float oldy = 0;

    private void drawPathProg(Canvas canvas) {

        mPath.reset();
        mPath.moveTo(0, 0);
        mPath.quadTo(100, 100, 200, 200);
        mPath.quadTo(400, 400, 800, 800);

        mPathAnim.reset();
        mPathAnim.moveTo(0, 0);
//        float newOneCenterX = oneCenterX + (inCreate * controlArr[0]);
//        float newOneCenterY = oneCenterY + (inCreate * controlArr[1]);
//        float newTwoCenterX = ((1 - inCreate) * (1 - inCreate) * oneCenterX + 2 * inCreate * (1 - inCreate) * (oneCenterX + controlArr[0]) + inCreate * inCreate * TwoCenterX);
//        float newwoCenterY = ((1 - inCreate) * (1 - inCreate) * oneCenterY + 2 * inCreate * (1 - inCreate) * (oneCenterY + controlArr[1]) + inCreate * inCreate * TwoCenterY);
        float newOneCenterX = oldx;
        float newOneCenterY = oldy;
        float newTwoCenterX = oldx + 800 * inCreate;
        float newwoCenterY = oldy + 800 * inCreate;

        mPathAnim.quadTo(newOneCenterX, newOneCenterY, newTwoCenterX, newwoCenterY);
        canvas.drawPath(mPath, paint);
        canvas.drawPath(mPathAnim, paintAnim);

    }

    private void drawPathBg(Canvas canvas) {

        mPath.reset();
        mPath.moveTo(0, 0);
        mPath.quadTo(100, 100, 200, 200);
        mPath.quadTo(400, 400, 800, 800);
        canvas.drawPath(mPath, paint);


        mPathAnim.reset();
        mPathAnim.moveTo(0, 0);
        mPathAnim.quadTo(100, 100, 200, 200);
//        mPath.quadTo(400, 400, 800, 800);
//        canvas.drawPath(mPathAnim, paintAnim);
    }


    public void setInCreate(float centerY) {
        this.inCreate = centerY;
        invalidate();
    }

    // 启动路径动画
    public void startPathAnim() {
        if (pathAnim != null) {
            pathAnim.cancel();
        }
//        currentStatues = SHOW_PATH_ANIM;
        if (pathAnim == null) {
            PropertyValuesHolder centerXPvh = PropertyValuesHolder.ofFloat("inCreate", 0, 1);
            pathAnim = ObjectAnimator.ofPropertyValuesHolder(this, centerXPvh);
            pathAnim.setDuration(1500);
            pathAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
//                    isStartWalkAnim = false;
//                    pathAnimEnd();
//                    // 更新界面的view
//                    pathAnimEndUpdateView();
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
//                    isStartWalkAnim = true;
                }
            });
        }
        pathAnim.start();
    }
}
