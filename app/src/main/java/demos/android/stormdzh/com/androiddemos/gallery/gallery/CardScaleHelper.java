package demos.android.stormdzh.com.androiddemos.gallery.gallery;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * @author dzh
 * recyclerView实现画廊帮助类
 */
public class CardScaleHelper {
    private RecyclerView mRecyclerView;
    private Context mContext;

    private float mScale = 0.8f; // 两边视图scale
    private int mPagePadding = 15; // 卡片的padding, 卡片间的距离等于2倍的mPagePadding
    public int mShowLeftCardWidth = 60;   // 左边卡片显示大小

    private float mAlpha = 0.5f;  //透明度的alpha

    private int mCardWidth; // 卡片宽度
    private int mOnePageWidth; // 滑动一页的距离
    private int mCardGalleryWidth;

    private int mCurrentItemPos;
    private int mCurrentItemOffset;

    private CardLinearSnapHelper mLinearSnapHelper = new CardLinearSnapHelper();

    public void attachToRecyclerView(final RecyclerView mRecyclerView) {
        // 开启log会影响滑动体验, 调试时才开启
//        LogUtils.mLogEnable = false;
        this.mRecyclerView = mRecyclerView;
        mContext = mRecyclerView.getContext();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mLinearSnapHelper.mNoNeedToScroll = mCurrentItemOffset == 0 || mCurrentItemOffset == getDestItemOffset(mRecyclerView.getAdapter().getItemCount() - 1);
                    updateShowingInfo(mRecyclerView);
//                    View itemView = getItemView(mRecyclerView);
//                    Log.i("test","position:"+(int)itemView.getTag());
                } else {
                    mLinearSnapHelper.mNoNeedToScroll = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // dx>0则表示右滑, dx<0表示左滑, dy<0表示上滑, dy>0表示下滑
                if (dx != 0) {//去掉奇怪的内存疯涨问题
                    mCurrentItemOffset += dx;
                    computeCurrentItemPos();
//                    LogUtils.v(String.format("dx=%s, dy=%s, mScrolledX=%s", dx, dy, mCurrentItemOffset));
                    onScrolledChangedCallback();
                }
            }
        });

        initWidth();
        mLinearSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    private void updateShowingInfo(RecyclerView recyclerView) {

        if (recyclerView == null) return;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //判断是当前layoutManager是否为LinearLayoutManager
        // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
        if (layoutManager instanceof LinearLayoutManager) {

            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;

            //获得第一个可见的位置
            int firstCompletelyVisibleItemPosition = linearManager.findFirstCompletelyVisibleItemPosition();

            //获取最后一个可见view的位置
            int lastItemPosition = linearManager.findLastVisibleItemPosition();
            //获取第一个可见view的位置
            int firstItemPosition = linearManager.findFirstVisibleItemPosition();
            if (unChanged(firstCompletelyVisibleItemPosition)) {
                mCurrentItemPos = firstCompletelyVisibleItemPosition;
                Log.i("test", lastItemPosition + "   " + firstItemPosition + "   " + getCurrentItemPos());
                if (mOnItemChangedListener != null) {
                    mOnItemChangedListener.onItemChanged(getCurrentItemPos());
                }
            }
        }
    }

    private int lastItemPos = -1;

    private synchronized boolean unChanged(int mCurrentItemPos) {
        if (mCurrentItemPos < 0) return false;
        if (mCurrentItemPos == lastItemPos) return false;
        lastItemPos = mCurrentItemPos;
        return true;
    }

    /**
     * 初始化卡片宽度
     */
    private void initWidth() {
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mCardGalleryWidth = mRecyclerView.getWidth();
                mCardWidth = mCardGalleryWidth - ScreenUtil.dip2px(mContext, 2 * (mPagePadding + mShowLeftCardWidth));
                mOnePageWidth = mCardWidth;
                mRecyclerView.smoothScrollToPosition(mCurrentItemPos);
                onScrolledChangedCallback();
            }
        });
    }

    public void setCurrentItemPos(int currentItemPos) {
        this.mCurrentItemPos = currentItemPos;
    }

    public int getCurrentItemPos() {
        return mCurrentItemPos;
    }

    private int getDestItemOffset(int destPos) {
        return mOnePageWidth * destPos;
    }

    /**
     * 计算mCurrentItemOffset
     */
    private void computeCurrentItemPos() {
        if (mOnePageWidth <= 0) return;

        boolean pageChanged = false;
        // 滑动超过一页说明已翻页
        if (Math.abs(mCurrentItemOffset - mCurrentItemPos * mOnePageWidth) >= mOnePageWidth) {
            pageChanged = true;
        }
        if (pageChanged) {
            int tempPos = mCurrentItemPos;

            mCurrentItemPos = mCurrentItemOffset / mOnePageWidth;
//            Log.i("test",String.format("=======onCurrentItemPos Changed======= tempPos=%s, mCurrentItemPos=%s", tempPos, mCurrentItemPos));
        }
    }


    private int oldPosition;

    /**
     * RecyclerView位移事件监听, view大小随位移事件变化
     */
    private void onScrolledChangedCallback() {
        int offset = mCurrentItemOffset - mCurrentItemPos * mOnePageWidth;
        float percent = (float) Math.max(Math.abs(offset) * 1.0 / mOnePageWidth, 0.0001);

        Log.i("test", "percent=>" + percent);
//        Log.d("test",String.format("offset=%s, percent=%s", offset, percent));
        View leftView = null;
        View currentView;
        View rightView = null;
        if (mCurrentItemPos > 0) {
            leftView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos - 1);
        }
        currentView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos);
        if (mCurrentItemPos < mRecyclerView.getAdapter().getItemCount() - 1) {
            rightView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos + 1);
        }

        if (leftView != null) {
            // y = (1 - mScale)x + mScale
            leftView.setScaleY((1 - mScale) * percent + mScale);

            setViewAlpha(leftView, percent, false);
        }
        if (currentView != null) {
            // y = (mScale - 1)x + 1
            currentView.setScaleY((mScale - 1) * percent + 1);
            setViewAlpha(currentView, percent, true);
        }
        if (rightView != null) {
            // y = (1 - mScale)x + mScale
            rightView.setScaleY((1 - mScale) * percent + mScale);

            setViewAlpha(rightView, percent, false);
//            Log.i("test","right=>"+(int)rightView.getTag());
        }
    }

    private void setViewAlpha(View view, float percent, boolean isCenter) {
        if (view == null) return;
        if (isCenter) {
            view.setAlpha((mScale - 1) * percent + 1);
        } else {
            view.setAlpha((1 - mAlpha) * percent + mAlpha);
        }
    }

    public void setAlpha(float alpha) {
        mAlpha = alpha;
    }

    public void setScale(float scale) {
        mScale = scale;
    }

    public void notifydatachange(){
        initWidth();
        mLinearSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    private OnItemChangedListener mOnItemChangedListener;

    public void setOnItemChangedListener(OnItemChangedListener listener) {
        this.mOnItemChangedListener = listener;
    }

    public interface OnItemChangedListener {
        void onItemChanged(int position);
    }
}
