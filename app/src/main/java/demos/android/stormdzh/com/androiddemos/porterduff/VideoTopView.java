package demos.android.stormdzh.com.androiddemos.porterduff;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demos.android.stormdzh.com.androiddemos.util.AppUtil;

public class VideoTopView extends View {


    private Bitmap mRectBitmap; //背景矩形bitmap
    private Paint mRectPaint;
    private int mWidth;
    private int mHeight;
    private int mRadius = AppUtil.dip2px(getContext(), 10);

    public VideoTopView(Context context) {
        this(context, null);
    }

    public VideoTopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoTopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

        //设置圆角层
//        GradientDrawable gradientDrawable = new GradientDrawable();
//        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
//        gradientDrawable.setColor(Color.RED);
//        gradientDrawable.setCornerRadius(AppUtil.dip2px(getContext(), 10));
//        gradientDrawable.setStroke(AppUtil.dip2px(getContext(), 2), Color.BLUE);


        mRectPaint = new Paint();
        mRectPaint.setColor(Color.parseColor("#ff0000"));
        mRectPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWidth = getWidth();
        mHeight = getHeight();

        if (mRectBitmap == null) {
            mRectBitmap = getBitMap(mWidth, mHeight);
        }

        if (mRectBitmap == null) return;


        Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        //产生一个同样大小的画布
        Canvas _canvas = new Canvas(target);
        RectF rect = new RectF(0, 0, mWidth, mHeight);
        //画圆角
        _canvas.drawRoundRect(rect, mRadius, mRadius, mRectPaint);
        //设置模式
        mRectPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        _canvas.drawBitmap(mRectBitmap, 0, 0, mRectPaint);
        //设置给view
        canvas.drawBitmap(target, 0, 0, null);
    }


    /**
     * 拿到矩形bitmap
     *
     * @param mWidth
     * @param mHeight
     * @return
     */
    private Bitmap getBitMap(int mWidth, int mHeight) {
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#35998c"));
//        Bitmap.Config config =
//                colorDrawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
//                        : Bitmap.Config.RGB_565;
        Bitmap.Config config =
                Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, config);
        //注意，下面三行代码要用到，否则在View或者SurfaceView里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        colorDrawable.setBounds(0, 0, mWidth, mHeight);
        colorDrawable.draw(canvas);
        return bitmap;
    }

    public void setmRadius(int radius){
        this.mRadius=radius;
    }
}
