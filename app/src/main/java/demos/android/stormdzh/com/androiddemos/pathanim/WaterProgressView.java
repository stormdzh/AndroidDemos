package demos.android.stormdzh.com.androiddemos.pathanim;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.widget.ProgressBar;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.util.AppUtil;

/**
 * 水波动画控件
 * Author: dzh
 */
public class WaterProgressView extends ProgressBar {

    //默认圆的背景色
    public static final int DEFAULT_CIRCLE_COLOR = 0xff239493;
    //默认进度的颜色
    public static final int DEFAULT_PROGRESS_COLOR = 0xffffffff;
    //默认文字的颜色
    public static final int DEFAULT_TEXT_COLOR = 0xffffffff;
    //默认文字的大小
    public static final int DEFAULT_TEXT_SIZE = 18;
    public static final int DEFAULT_RIPPLE_TOPHEIGHT = 10;

    private Context mContext;

    private Canvas mPaintCanvas;
    private Bitmap mBitmap;

    //画圆的画笔
    private Paint mCirclePaint;
    //画圆的画笔的颜色
    private int mCircleColor = 0xff00CCCC;

    //画进度的画笔
    private Paint mProgressPaint;
    //画进度的画笔的颜色
    private int mProgressColor = 0xff00CC66;
    //画进度的path
    private Path mProgressPath;
    //进度文字的画笔
    private Paint mTextPaint;
    //进度文字的颜色
    private int mTextColor = 0xffffffff;
    private int mTextSize = 18;
    //当前进度
    private int mTargetProgress = 100;

    private GestureDetector mGestureDetector;
    //单击动画进行的次数，默认为50
    private int mSingleTapAnimationCount = 50;

    public WaterProgressView(Context context) {
        this(context, null);
    }

    public WaterProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;

        getAttrValue(attrs);

        //初始化画笔的相关属性
        initPaint();
        mProgressPath = new Path();
    }

    /**
     * 获取自定义属性的值
     *
     * @param attrs
     */
    private void getAttrValue(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.WaterProgressView);

        mCircleColor = ta.getColor(R.styleable.WaterProgressView_circle_color, DEFAULT_CIRCLE_COLOR);
        mProgressColor = ta.getColor(R.styleable.WaterProgressView_progress_color, DEFAULT_PROGRESS_COLOR);
        mTextColor = ta.getColor(R.styleable.WaterProgressView_text_color, DEFAULT_TEXT_COLOR);
        mTextSize = (int) ta.getDimension(R.styleable.WaterProgressView_text_size, AppUtil.dip2px(mContext, DEFAULT_TEXT_SIZE));
        ta.recycle();
    }


    private Handler doubleTapHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    /**
     * 实现双击动画
     */
    public void startDoubleTapAnimation() {
        setProgress(0);
        doubleTapHandler.postDelayed(doubleTapRunnable, 60);
    }

    //双击处理线程，隔60ms发送一次数据
    private Runnable doubleTapRunnable = new Runnable() {
        @Override
        public void run() {
            if (getProgress() < mTargetProgress) {
                invalidate();
                setProgress(getProgress() + 1);
                doubleTapHandler.postDelayed(doubleTapRunnable, 60);
            } else {
                doubleTapHandler.removeCallbacks(doubleTapRunnable);
            }
        }
    };

    /**
     * 初始化画笔的相关属性
     */
    private void initPaint() {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);

        mProgressPaint = new Paint();
        mProgressPaint.setColor(mProgressColor);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setDither(true);
        mProgressPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setTextSize(mTextSize);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);

        mBitmap = Bitmap.createBitmap(width - getPaddingLeft() - getPaddingRight(), height - getPaddingTop() - getPaddingBottom(), Bitmap.Config.ARGB_8888);
        mPaintCanvas = new Canvas(mBitmap);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        float ratio = getProgress() * 1.0f / getMax();

        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        //画圆
        mPaintCanvas.drawCircle(width / 2, height / 2, height / 2, mCirclePaint);

        //画进度
        mProgressPath.reset();
        //从右上边开始draw path
        int rightTop = (int) ((1 - ratio) * height);
        mProgressPath.moveTo(width, rightTop);
        mProgressPath.lineTo(width, height);
        mProgressPath.lineTo(0, height);
        mProgressPath.lineTo(0, rightTop);
        mProgressPath.close();
        mPaintCanvas.drawPath(mProgressPath, mProgressPaint);

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }
}
