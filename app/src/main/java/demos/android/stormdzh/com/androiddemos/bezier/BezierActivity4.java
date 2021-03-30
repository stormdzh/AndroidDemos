package demos.android.stormdzh.com.androiddemos.bezier;

import android.animation.Animator;
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
import android.widget.ImageView;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2021-03-12 15:23
 */
public class BezierActivity4 extends Activity {

    /**
     * 动画
     */
    private ValueAnimator mAnimator;

    /**
     * 移动物体
     */
    private ImageView mView;

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


    private boolean isStart=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bezier2);

        DisplayMetrics dm =getResources().getDisplayMetrics();
        w_screen = dm.widthPixels;
        h_screen = dm.heightPixels;
        Log.i("ee", "屏幕尺寸：宽度 = " + w_screen + "高度 = " + h_screen + "密度 = " + dm.densityDpi);


        width =  dip2px(this, w_screen);
        height = dip2px(this, h_screen);

        Log.v("ee","width=" + width + " .............. height = " + height);

        mView = (ImageView) findViewById(R.id.button1);

        mView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart =false;
                mView.setRotation(0);
                mAnimator.start();
            }
        });


        int p0x =50;
        int p0y = (int) (w_screen*0.5);
        int p3y =50;

        mAnimator = ValueAnimator.ofObject(new BezierEvaluator(), new PointF(p0x,p0y),new PointF(p0y,p3y));
        mAnimator.setDuration(1500);
        mAnimator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF)animation.getAnimatedValue();
                mView.setX(pointF.x);
                mView.setY(pointF.y);
            }
        });
        mAnimator.setTarget(mView);
        mAnimator.setRepeatCount(0);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);


        //设置空间的默认位置
        mView.setX(p0x);
        mView.setY(h_screen/2);

    }
    class BezierEvaluator implements TypeEvaluator<PointF> {

        @Override
        public PointF evaluate(float fraction, PointF startValue,
                               PointF endValue) {
            Log.d("ee", "fraction:"+fraction+"  startValue:("+startValue.x+"."+startValue.y+")   endValue:("+endValue.x+"."+endValue.y+")");

            PointF point = new PointF();
            if(fraction<0.33){
                int dy = h_screen / 2 - w_screen / 2;
                float ddy= (float) (dy *fraction*3);
                Log.e("ddd","ddy:"+ddy   +"dy:"+dy+"   fraction:"+fraction );
                point.y=(h_screen/2 - ddy);
                point.x=50;
                return point;
            }



            final float t = fraction;
            float v = (float) Math.max(fraction - 0.3,0);
            mView.setRotation(90*v);

            float oneMinusT = 1.0f - t;

            PointF point0 = (PointF)startValue;

            int p0x =50;
            int p0y = (int) (w_screen*0.5);
            int p3y =50;

            PointF point1 = new PointF();
//            point1.set(320, 0);
//            point1.set(dip2px(BezierActivity2.this,320), 0);
            point1.set(p0x, p0y);

            PointF point2 = new PointF();
//            point2.set(0, 320);
//            point2.set(0, dip2px(BezierActivity2.this,320));
            point2.set(82,82);

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