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
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.util.AppUtil;

/**
 * 进度控件
 */
public class YWStudyProgView extends FrameLayout {

    Paint paint = new Paint(); //北京画笔
    Paint paintAnim = new Paint(); //动画画笔

    Path mPathAnim = new Path();
    Path mPath = new Path();

    private ObjectAnimator pathAnim; // 路径动画
    private float inCreate;


    private int linWidth;
    private int line1Startx;
    private int line2Startx;

    private WaterProgressView iv_left;
    private WaterProgressView iv_center;
    private WaterProgressView iv_right;

    private boolean startWalkAnim;

    private int linMargintop = AppUtil.dip2px(getContext(), 120) / 2;

    public YWStudyProgView(@NonNull Context context) {
        this(context, null);
    }

    public YWStudyProgView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YWStudyProgView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_pathanim_layout, this);


        iv_left = findViewById(R.id.iv_left);
        iv_center = findViewById(R.id.iv_center);
        iv_right = findViewById(R.id.iv_right);


        int fullLineWidth = AppUtil.dip2px(getContext(), 4);
        int dashedLineWidth = AppUtil.dip2px(getContext(), 10);
        DashPathEffect dpe = new DashPathEffect(new float[]{fullLineWidth, dashedLineWidth}, 0);
//        int strokeWidth = dip2px(lineStrokeWidthHalf * 2);
        int strokeWidth = AppUtil.dip2px(context, 4);

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

        getParams();

        drawPathBg(canvas);

        drawPathProg(canvas,line1Startx,line1Startx+linWidth);

        super.dispatchDraw(canvas);
    }

    private void getParams() {

        if (linWidth > 0) return;

        int left = iv_left.getLeft();
        int right = iv_left.getRight();

        Log.i("test", "iv_left left:" + left + "   right:" + right);

        int cleft = iv_center.getLeft();
        int cright = iv_center.getRight();

        Log.i("test", "iv_center left:" + cleft + "   right:" + cright);

        Log.i("test", "line width:" + (cleft - right));

        line1Startx = right;
        line2Startx = cright;
        linWidth = cleft - right;

    }

    float oldx = 0;

    private void drawPathProg(Canvas canvas,int startx,int endx) {
        if(!startWalkAnim) return;
//        mPath.reset();
//        mPath.moveTo(0, 0);
//        mPath.quadTo(100, 100, 200, 200);
//        mPath.quadTo(400, 400, 800, 800);
        oldx=startx;
        mPathAnim.reset();
        mPathAnim.moveTo(startx, linMargintop);
//        float newOneCenterX = oneCenterX + (inCreate * controlArr[0]);
//        float newOneCenterY = oneCenterY + (inCreate * controlArr[1]);
//        float newTwoCenterX = ((1 - inCreate) * (1 - inCreate) * oneCenterX + 2 * inCreate * (1 - inCreate) * (oneCenterX + controlArr[0]) + inCreate * inCreate * TwoCenterX);
//        float newwoCenterY = ((1 - inCreate) * (1 - inCreate) * oneCenterY + 2 * inCreate * (1 - inCreate) * (oneCenterY + controlArr[1]) + inCreate * inCreate * TwoCenterY);
        float newOneCenterX = startx;
        float newOneCenterY = linMargintop;
        float newTwoCenterX = oldx + (endx-startx) * inCreate;
        float newwoCenterY = linMargintop;

        oldx=newTwoCenterX;

        mPathAnim.quadTo(newOneCenterX, newOneCenterY, newTwoCenterX, newwoCenterY);
//        canvas.drawPath(mPath, paint);
        canvas.drawPath(mPathAnim, paintAnim);

    }

    private void drawPathBg(Canvas canvas) {


        mPath.reset();
        mPath.moveTo(line1Startx, linMargintop);
        mPath.quadTo(line1Startx, linMargintop, line1Startx + linWidth, linMargintop);
//        mPath.quadTo(400, 400, 800, 800);

        mPath.moveTo(line2Startx,linMargintop);
        mPath.quadTo(line2Startx,linMargintop,line2Startx+linWidth,linMargintop);
        canvas.drawPath(mPath, paint);


//        mPathAnim.reset();
//        mPathAnim.moveTo(0, 0);
//        mPathAnim.quadTo(100, 100, 200, 200);
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
                    startWalkAnim = false;
//                    pathAnimEnd();
//                    // 更新界面的view
//                    pathAnimEndUpdateView();
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    startWalkAnim = true;
                }
            });
        }
        pathAnim.start();
    }
}
