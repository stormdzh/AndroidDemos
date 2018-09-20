package demos.android.stormdzh.com.androiddemos.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import java.util.Collection;

/**
 * Created by a111 on 2018/9/20.
 */

public class AppUtil {


    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return true：空  false：不为空
     */
    public static boolean isEmptyColls(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断角标是否越界
     *
     * @param collection 集合
     * @param index      角标
     * @return true：越界  fase：不越界
     */
    public static boolean isIllegalIndex(Collection collection, int index) {
        return isEmptyColls(collection) || index < 0 || index >= collection.size();
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, r.getDisplayMetrics());
        return (int) px;
    }

    public static int dipTopx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
