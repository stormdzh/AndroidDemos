package demos.android.stormdzh.com.androiddemos.gallery;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author : dzh
 * date   : 2018/1/10l
 * desc   :
 */

public class GalleryViewpagerAdapter extends PagerAdapter implements View.OnClickListener {

    private List<Integer> mList;
    private Context mContext;

    private HashMap<Integer, View> mViews = new HashMap<>();

    public GalleryViewpagerAdapter(Context context, List<Integer> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 把要删除的view放到pool里以供复用
        pushViewToPool((View) object);
        mViews.remove(position);
        // 从view pager中删除
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // 获取复用的view给子类去用，没有可复用的view时为null
        View view = getView(container, pullViewFromPool(), position);
        mViews.put(position, view);
        // 添加到view pager
        container.addView(view);
        return view;
    }

    /**
     * 获取view方法，子类实现这个方法来获取渲染View
     *
     * @param convertView 如果是null则没有可复用的View，如果非null则是可复用的View
     * @param position
     * @return
     */
    protected View getView(ViewGroup container, View convertView, int position) {
        if (convertView == null) {
            convertView = new ViewPagerItemView(mContext);
        }
        if (mList != null || position >= 0 || position < mList.size()) {
            Integer resid = mList.get(position);
            ((ViewPagerItemView) convertView).setData(resid);
            convertView.setOnClickListener(this);
        }
        return convertView;
    }


    public void setVoiceState(int position) {
        if (mViews == null || mViews.isEmpty()) return;
        for (int i = 0; i < mViews.size(); i++) {
            ViewPagerItemView spiritView = (ViewPagerItemView) mViews.get(i);
            if (spiritView == null) continue;
            if (position == i) {
                spiritView.setVoiceVisibility(true);
            } else {
                spiritView.setVoiceVisibility(false);
            }
        }
    }


    /****************** 复用view start ******************************/
    private List<View> mPageViewPool = new ArrayList<>();

    private View pullViewFromPool() {
        View view = null;
        for (View v : mPageViewPool) {
            view = v;
            break;
        }
        if (view != null) {
            mPageViewPool.remove(view);
        }
        return view;
    }

    private void pushViewToPool(View view) {
        if (!mPageViewPool.contains(view)) {
            mPageViewPool.add(view);
        }
    }

    public void destroy() {
//        stopAllAnim();
        if (mViews != null) {
            mViews.clear();
        }
        if (mPageViewPool != null)
            mPageViewPool.clear();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onWordDetailClick();
        }
    }

    private OnWordDetailClickListener mListener;

    public void setOnWordDetailClickListener(OnWordDetailClickListener listener) {
        this.mListener = listener;
    }

    public interface OnWordDetailClickListener {
        void onWordDetailClick();
    }

//    private void stopAllAnim(){
//        for (View view:mPageViewPool){
//            ((AchieveSpiritView)view).stopAnim();
//        }
//    }
}