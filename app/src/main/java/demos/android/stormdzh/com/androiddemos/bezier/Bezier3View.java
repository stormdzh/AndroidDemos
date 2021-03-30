package demos.android.stormdzh.com.androiddemos.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2021-03-15 15:10
 */
public class Bezier3View extends View {
    private Point controlPoint = new Point(200, 200);

    public Bezier3View(Context context) {
        super(context);
    }

    public Bezier3View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Bezier3View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        Path path = new Path();
        path.moveTo(50, 540);
//        path.quadTo(controlPoint.x, controlPoint.y, 700, 500);
        path.quadTo(controlPoint.x, controlPoint.y, 540, 50);
        //绘制路径
        canvas.drawPath(path, paint);
        //绘制辅助点
        canvas.drawPoint(controlPoint.x,controlPoint.y,paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                controlPoint.x = (int) event.getX();
                controlPoint.y = (int) event.getY();

                Log.i("dzh","controlPoint.x:"+controlPoint.x+"   controlPoint.y:"+controlPoint.y);
                invalidate();
                break;
        }
        return true;
    }
}