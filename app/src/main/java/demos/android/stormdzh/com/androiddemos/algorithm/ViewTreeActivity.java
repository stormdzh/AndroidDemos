package demos.android.stormdzh.com.androiddemos.algorithm;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.Queue;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * @Description: 遍历viewgroup 下面的所有子view
 * @Author: dzh
 * @CreateDate: 2021/5/12 5:26 PM
 */
public class ViewTreeActivity extends Activity {


    private LinearLayout llRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewtree);

        llRoot = findViewById(R.id.llRoot);

        //递归-设置imageview的背景为图片
//        imageViewBg1(llRoot);
        //非递归（广度遍历）-设置imageview的背景为图片
        imageViewBg2(llRoot);

        //获取子view的个数
//        int count = getViewCount1(llRoot);
        int count = getViewCount2(llRoot);
        Log.i("dzh", "count:" + count);
    }


    /**
     * 广度遍历获取所有的view个数
     *
     * @param view
     * @return
     */
    private int getViewCount2(ViewGroup view) {

        Queue<ViewGroup> queue = new LinkedList<>();

        queue.offer(view);

        int result = 0;

        while (!queue.isEmpty()) {
            result++;
            ViewGroup tvg = queue.poll();
            int childCount = tvg.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = tvg.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    queue.offer((ViewGroup) childAt);
                } else {
                    result++;
                }
            }

        }

        return result;

    }


    /**
     * 递归获取所有的view个数
     *
     * @param view
     * @return
     */
    private int getViewCount1(ViewGroup view) {
        int allViewCount = 0;
        if (view == null) return 0;
        if (view instanceof ViewGroup) {
            allViewCount++;
            int childCount = view.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = view.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    allViewCount += getViewCount1((ViewGroup) childAt);
                } else {
                    allViewCount++;
                }
            }

        }

        return allViewCount;

    }

    /**
     * 非递归那就是树的广度优先遍历，每遍历到一个，计数器加一
     *
     * @param view
     */
    private void imageViewBg2(ViewGroup view) {
        Queue<ViewGroup> queue = new LinkedList<>();
        queue.offer(view);
        while (!queue.isEmpty()) {
            ViewGroup tvg = queue.poll();
            int childCount = tvg.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = tvg.getChildAt(i);
                if (childAt instanceof ImageView) {
                    childAt.setBackgroundResource(R.drawable.img_test_face);
                } else if (childAt instanceof ViewGroup) {
                    queue.offer((ViewGroup) childAt);
                }
            }
        }
    }


    /**
     * 递归给所有元素设置背景
     *
     * @param view
     */
    private void imageViewBg1(View view) {

        if (view == null) return;
        int childCount = ((ViewGroup) view).getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = ((ViewGroup) view).getChildAt(i);
            if (childAt instanceof ImageView) {
                childAt.setBackgroundResource(R.drawable.img_test_face);
            } else if (childAt instanceof ViewGroup) {
                imageViewBg1(childAt);
            }
        }

    }
}
