package demos.android.stormdzh.com.androiddemos.bezier;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 描述
 *
 * int cx = mSpaceX - 100;
 *         int cy = mSpaceY + (location[1] - mSpaceY) / 2;
 *
 *         Path path = new Path();
 *         path.moveTo(location[0] - halfenergyWidth, location[1] - halfenergyWidth);
 *         path.quadTo(cx, cy, mSpaceX - halfenergyWidth, mSpaceY - halfenergyWidth);
 *         final PathMeasure pathMeasure = new PathMeasure();
 *         // false表示path路径不闭合
 *         pathMeasure.setPath(path, false);
 *         // ofFloat是一个生成器
 *         ValueAnimator mAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
 *         mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
 *         mAnimator.setDuration(1400);
 * @Author: dzh
 * @CreateDate: 2021-03-12 15:23
 */
public class BezierActivity extends Activity {

    /**
     * 动画
     */
    private ValueAnimator mAnimator;

    /**
     * 移动物体
     */
    private Button mView;

    /**
     * 三次贝塞尔曲线起始点，2控制点，终点
     */
    private Point[] mPoints=new Point[4];
    /**
     * 屏幕宽
     */
    private int w_screen;
    /**
     * 屏幕高
     */
    private int h_screen;
    /**
     * 贝塞尔曲线
     */
    private BezierCurveView mBezierCurveView;

    private int width, height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bezier);

        DisplayMetrics dm =getResources().getDisplayMetrics();
        w_screen = dm.widthPixels;
        h_screen = dm.heightPixels;
        Log.i("ee", "屏幕尺寸：宽度 = " + w_screen + "高度 = " + h_screen + "密度 = " + dm.densityDpi);


        width =  dip2px(this, w_screen);
        height = dip2px(this, h_screen);

        Log.v("ee","width=" + width + " .............. height = " + height);

        mView = (Button) findViewById(R.id.button1);

        mView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimator.start();
            }
        });

        mAnimator = ValueAnimator.ofObject(new BezierEvaluator(), new PointF(0,0),new PointF(dip2px(this, 320),dip2px(this,320)));
        mAnimator.setDuration(2000);
        mAnimator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF)animation.getAnimatedValue();
                mView.setX(pointF.x);
                mView.setY(pointF.y);
            }
        });
        mAnimator.setTarget(mView);
        mAnimator.setRepeatCount(1);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);

    }
    class BezierEvaluator implements TypeEvaluator<PointF> {

        @Override
        public PointF evaluate(float fraction, PointF startValue,
                               PointF endValue) {
            Log.d("ee", "fraction:"+fraction+"  startValue:("+startValue.x+"."+startValue.y+")   endValue:("+endValue.x+"."+endValue.y+")");
            final float t = fraction;
            float oneMinusT = 1.0f - t;
            PointF point = new PointF();

            PointF point0 = (PointF)startValue;

            PointF point1 = new PointF();
//            point1.set(320, 0);
            point1.set(dip2px(BezierActivity.this,320), 0);

            PointF point2 = new PointF();
//            point2.set(0, 320);
            point2.set(0, dip2px(BezierActivity.this,320));

            PointF point3 = (PointF)endValue;

            point.x = oneMinusT * oneMinusT * oneMinusT * (point0.x)
                    + 3 * oneMinusT * oneMinusT * t * (point1.x)
                    + 3 * oneMinusT * t * t * (point2.x)
                    + t * t * t * (point3.x);

            point.y = oneMinusT * oneMinusT * oneMinusT * (point0.y)
                    + 3 * oneMinusT * oneMinusT * t * (point1.y)
                    + 3 * oneMinusT * t * t * (point2.y)
                    + t * t * t * (point3.y);
            return point;
        }
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     *
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}