package demos.android.stormdzh.com.androiddemos.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 加载大图
 * @Author: dzh
 * @CreateDate: 2021/4/23 10:08 AM
 */
public class LargeBitmapView extends View {

    private int mPictureWidth; //图片宽度
    private int mPictureHeight; //图片高度
    private BitmapRegionDecoder mDecoder; //图片decoder

    private volatile Rect mRect = new Rect();

    private Bitmap mBitmap;
    private Paint mPaint;

    private int mPortX, mPortY; //上次滑动的位置
    private int mViewWidth, mViewHeight;
    private int mShowWidth, mShowHeight;
    private BitmapFactory.Options options;

    public LargeBitmapView(Context context) {
        this(context, null);
    }

    public LargeBitmapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LargeBitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        options = new BitmapFactory.Options();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }


    public void setInputStream(InputStream is) throws IOException {
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, options);
        mPictureWidth = options.outWidth;
        mPictureHeight = options.outHeight;
        mDecoder = BitmapRegionDecoder.newInstance(is, false);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;

        // 实际展示部分的宽高
        mShowWidth = mPictureWidth > mViewWidth ? mViewWidth : mPictureWidth;
        mShowHeight = mPictureHeight > mViewHeight ? mViewHeight : mPictureHeight;

        // 展示图片部分左上角在大图里的坐标
        mPortX = (mPictureWidth - mShowWidth) / 2;
        mPortY = (mPictureHeight - mShowHeight) / 2;

        mBitmap = Bitmap.createBitmap(mShowWidth, mShowHeight, Bitmap.Config.ARGB_8888);
        loadPartial();
    }

    // 加载展示部分图片数据
    private void loadPartial() {
        mRect.set(mPortX, mPortY, mPortX + mShowWidth, mPortY + mShowHeight);
        options.inJustDecodeBounds = false;
        mBitmap = mDecoder.decodeRegion(mRect, options);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }


    // 记录用户上一次手指所在位置
    private int mLastX;
    private int mLastY;

    // 记录用户手指按下的位置
    private int mMotionX;
    private int mMotionY;

    // 用户是否在拖动
    private boolean mIsDragging = false;
    private int mTouchSlop;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX(), y = (int) event.getY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                // 记录按下位置
                mLastX = mMotionX = x;
                mLastY = mMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 如果用户刚按下并且移动距离超出了点击范围，确认用户在拖动
                if (!mIsDragging && isDragging(x - mMotionX, y - mMotionY)) {
                    mIsDragging = true;
                }

                if (mIsDragging) {
                    int dx = x - mLastX;
                    int dy = y - mLastY;
                    boolean changed = false;
                    // 图片能够横向拖动，就做横向拖动
                    if (canHorizontalMove() && mPortX - dx >= 0
                            && mPortX - dx <= mPictureWidth - mShowWidth) {
                        mPortX -= dx;
                        changed = true;
                    }

                    // 图片能够做竖向拖动，就做竖向拖动
                    if (canVerticalMove() && mPortY - dy >= 0 &&
                            mPortY - dy <= mPictureHeight - mShowHeight) {
                        mPortY -= dy;
                        changed = true;
                    }

                    // 如果确定拖动有效，就更新展示图片数据并刷新控件
                    if (changed) {
                        loadPartial();
                        postInvalidate();
                    }
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mIsDragging = false;
                break;
        }
        return true;
    }

    private boolean isDragging(int dx, int dy) {
        return dx * dx + dy * dy > mTouchSlop * mTouchSlop;
    }

    private boolean canVerticalMove() {
        return mPictureHeight > mViewHeight;
    }

    private boolean canHorizontalMove() {
        return mPictureWidth > mViewWidth;
    }
}
