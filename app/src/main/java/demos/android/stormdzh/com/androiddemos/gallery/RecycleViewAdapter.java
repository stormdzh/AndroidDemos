package demos.android.stormdzh.com.androiddemos.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.gallery.gallery.AdapterMeasureHelper;

/**
 * @author dzh
 * 适配器 - 多音字
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private List<Integer> mList;
    private Context mContext;
    private RecyclerView mRecyclerView;

    private AdapterMeasureHelper mCardAdapterHelper = new AdapterMeasureHelper();

    public RecycleViewAdapter(Context ctx, RecyclerView recyclerView, List<Integer> list) {
        this.mList = list;
        this.mContext = ctx;
        this.mRecyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gallery_recycleview, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());


        holder.iv_big.setImageResource(mList.get(position));
        holder.iv_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "当前条目：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_big;

        public ViewHolder(final View view) {
            super(view);
            iv_big = view.findViewById(R.id.iv_big);
        }
    }


//    public void setVoiceState(int position) {
//
//        if (mRecyclerView == null || mRecyclerView.getLayoutManager() == null) return;
//        int childCount = mRecyclerView.getLayoutManager().getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View spiritView = (View) mRecyclerView.getChildAt(i);
//            if (spiritView == null) continue;
//            if (position == i) {
//                spiritView.setVoiceVisibility(true);
//            } else {
//                spiritView.setVoiceVisibility(false);
//            }
//        }
//    }


//    public ImageView getVoiceView(int position) {
//        if (mRecyclerView == null || mRecyclerView.getLayoutManager() == null) return null;
//        View wordView = (View) mRecyclerView.getLayoutManager().findViewByPosition(position);
//        if (wordView == null) return null;
//        return wordView.getVoiceView();
//    }


    private OnWordDetailClickListener mListener;

    public void setOnWordDetailClickListener(OnWordDetailClickListener listener) {
        this.mListener = listener;
    }

    public interface OnWordDetailClickListener {
        void onWordDetailClick(int position);
    }
}
