package demos.android.stormdzh.com.androiddemos.group;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2021-04-19 14:54
 */
public class LinearViewGroup extends ViewGroup {

    private int currentHeight;

    public LinearViewGroup(Context context) {
        super(context);
    }

    public LinearViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("dzh", "LinearViewGroup  onMeasure-------------------->");
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            int groupWidth = getMaxWidth();
            int groupHeight = getTotalHeight();

            setMeasuredDimension(groupWidth, groupHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getMaxWidth(), height);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, getTotalHeight());
        }
    }

    private int getMaxWidth() {
        int count = getChildCount();
        int maxWidth = 0;
        for (int i = 0; i < count; i++) {
            int currentWidth = getChildAt(i).getMeasuredWidth();
            if (maxWidth < currentWidth) {
                maxWidth = currentWidth;
            }
        }
        Log.i("dzh", "LinearViewGroup  maxWidth:" + maxWidth);
        return maxWidth;
    }

    private int getTotalHeight() {
        int count = getChildCount();
        int totalHeight = 0;
        for (int i = 0; i < count; i++) {
            totalHeight += getChildAt(i).getMeasuredHeight();
        }
        Log.i("dzh", "LinearViewGroup  totalHeight:" + totalHeight);
        return totalHeight;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {

            View childView = getChildAt(i);
            int measuredHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();

            LayoutParams layoutParams = childView.getLayoutParams();
            int topMargin = 0;
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layoutParams;
                topMargin = params.topMargin;
            }
            Log.i("dzh", "LinearViewGroup  measuredWidth:" + measuredWidth + "    measuredHeight:" + measuredHeight);
            childView.layout(0, currentHeight + topMargin, measuredWidth, currentHeight + measuredHeight + topMargin);
            currentHeight = currentHeight + measuredHeight + topMargin;

        }

    }

    //    支持Margin的固定写法，下面照抄就行了，至于为什么，可以去看源码，但是我觉得直接记住就ok了

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
}
