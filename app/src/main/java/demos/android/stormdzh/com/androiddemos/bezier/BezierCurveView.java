package demos.android.stormdzh.com.androiddemos.bezier;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2021-03-12 15:22
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BezierCurveView extends View {

    /**
     * 画笔
     */
    private Paint paint;

    /**
     * 路径
     */
    private Path path;


    public BezierCurveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierCurveView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(0.3f);

        path = new Path();
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.v("ee", "width = " + MeasureSpec.getSize(widthMeasureSpec) + "| height = " + MeasureSpec.getSize(heightMeasureSpec));
    }

    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        path.reset();
        path.moveTo(0, 0);
//        path.cubicTo(320, 0, 0, 320, 320, 320);
        path.cubicTo(dip2px(getContext(),320), 0, 0, dip2px(getContext(),320), dip2px(getContext(),320), dip2px(getContext(),320));
        Log.d("ee", getHeight() + "    " + getWidth() + "      getMeasuredWidth:" + getMeasuredWidth() + "      getMeasuredHeight:" + getMeasuredHeight());
        canvas.drawPath(path, paint);
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
