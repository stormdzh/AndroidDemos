package demos.android.stormdzh.com.androiddemos.gallery.gallery;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author dzh
 * RecyclerView
 */
public class SpeedRecyclerView extends RecyclerView {
    public SpeedRecyclerView(Context context) {
        super(context);
    }

    public SpeedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpeedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        return super.fling(1, 1);
    }

    //    @Override
//    public boolean fling(int velocityX, int velocityY) {
//        velocityX = solveVelocity(velocityX);
//        velocityY = solveVelocity(velocityY);
//        return super.fling(velocityX, velocityY);
//    }
    // 使用上面的方法，可以滑动很快

}
