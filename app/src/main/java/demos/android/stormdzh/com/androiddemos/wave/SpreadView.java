package demos.android.stormdzh.com.androiddemos.wave;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * Created by louliang on 2018/10/29.
 */

public class SpreadView extends View{
    private Paint centerPaint; //中心圆paint
    private int radius = 100; //中心圆半径
    private Paint spreadPaint; //扩散圆paint
    private float centerX;//圆心x
    private float centerY;//圆心y
    private int distance = 5; //每次圆递增间距
    private int maxRadius = 80; //最大圆半径
    private int delayMilliseconds = 100;//扩散延迟间隔，越大扩散越慢
    private int count=2;
    private List<Integer> spreadRadius = new ArrayList<>(count);//扩散圆层级数，元素为扩散的距离
    private List<Integer> alphas = new ArrayList<>(count);//对应每层圆的透明度

    public SpreadView(Context context) {
        this(context,null,0);
    }

    public SpreadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SpreadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShapeSpreadView, defStyleAttr, 0);
        radius = a.getDimensionPixelSize(R.styleable.ShapeSpreadView_shapespread_radius, radius);
        maxRadius = a.getDimensionPixelSize(R.styleable.ShapeSpreadView_shapespread_max_radius, maxRadius);
        int centerColor = a.getColor(R.styleable.ShapeSpreadView_shapespread_center_color, ContextCompat.getColor(context, R.color.colorAccent));
        int spreadColor = a.getColor(R.styleable.ShapeSpreadView_shapespread_spread_color, ContextCompat.getColor(context, R.color.colorAccent));
        distance = a.getInt(R.styleable.ShapeSpreadView_shapespread_distance, distance);
        a.recycle();
        centerPaint = new Paint();
        centerPaint.setColor(centerColor);
        centerPaint.setStyle(Paint.Style.FILL);
        centerPaint.setAntiAlias(true);
        spreadPaint = new Paint();
        spreadPaint.setAntiAlias(true);
        spreadPaint.setStyle(Paint.Style.STROKE);
        spreadPaint.setAlpha(255);
        spreadPaint.setStrokeWidth(2);
        spreadPaint.setColor(spreadColor);

        for(int i=0;i<count;i++){
            alphas.add(i,255);
            int max = Math.max(i * 2, 1);
            spreadRadius.add(0,radius/max);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //圆心位置
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < spreadRadius.size(); i++) {
            int alpha = alphas.get(i);
            spreadPaint.setAlpha(alpha);
            //需要绘制的半径宽度
            int width = spreadRadius.get(i);
            //绘制扩散的圆
            canvas.drawCircle(centerX, centerY,  width, spreadPaint);
            //每次扩散圆半径递增，圆透明度递减
            if (alpha > 0 && width < maxRadius) {
                int nextradius= width + distance;
                alpha = (int) ((float)nextradius/((float)maxRadius*2)*255);
                alphas.set(i, alpha);
//                Log.i("codedzh"," nextradius:"+nextradius+"   alpha:"+alpha);
                if(nextradius>=maxRadius) { //半径比最大半径大
                    alphas.set(i,255);
                    spreadRadius.set(i, 0);
                }else{
                    spreadRadius.set(i, nextradius);
                }
            }
        }

        //中间的圆
        canvas.drawCircle(centerX, centerY, radius, centerPaint);
        //延迟更新，达到扩散视觉差效果
        postInvalidateDelayed(delayMilliseconds);
    }
}
