package demos.android.stormdzh.com.androiddemos.adapter.main;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.entity.main.MainEntity;
import demos.android.stormdzh.com.androiddemos.listener.OnMainItemClickListener;
import demos.android.stormdzh.com.androiddemos.util.AppUtil;

/**
 * 数据适配器
 * Created by a111 on 2018/9/20.
 */

public class MainAdapter extends RecyclerView.Adapter {

    public List<MainEntity> mList;
    public Context mContext;

    public MainAdapter(Context context, List<MainEntity> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (AppUtil.isEmptyColls(mList) || AppUtil.isIllegalIndex(mList, position)) return;
        final MainEntity mainEntity = mList.get(position);

        MainViewHolder mainHolder = (MainViewHolder) holder;
        mainHolder.tvTitle.setText(mainEntity.name);
        int index = position % 4;
        switch (index) {
            case 0:
                mainHolder.ll_item_root.setBackgroundColor(Color.parseColor("#22456783"));
                break;
            case 1:
                mainHolder.ll_item_root.setBackgroundColor(Color.parseColor("#22ff0000"));
                break;
            case 2:
                mainHolder.ll_item_root.setBackgroundColor(Color.parseColor("#2200ff00"));
                break;
            case 3:
                mainHolder.ll_item_root.setBackgroundColor(Color.parseColor("#220000ff"));
                break;
        }

        mainHolder.ll_item_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onMainItemClick(mainEntity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout ll_item_root;
        public TextView tvTitle;

        public MainViewHolder(View itemView) {
            super(itemView);
            ll_item_root = itemView.findViewById(R.id.ll_item_root);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }

    public OnMainItemClickListener mListener;
    public void setOnMainItemClickListener(OnMainItemClickListener listener){
        this.mListener=listener;
    }

}
