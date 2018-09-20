package demos.android.stormdzh.com.androiddemos.adapter.turntable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.entity.turntable.TurnEntity;


/**
 * 抽奖数据适配
 * Created by a111 on 2018/9/5.
 */

public class TurntAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TurnEntity> mTurnList;
    private Context mContext;

    public TurntAdapter(Context context, List<TurnEntity> list) {
        this.mContext = context;
        this.mTurnList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(mContext).inflate(R.layout.item_turn_table, parent, false);
        return new TurnViewHolder(item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TurnViewHolder turnViewHolder = (TurnViewHolder) holder;
        TurnEntity turnEntity = mTurnList.get(position);

        if (position == 4) {

            turnViewHolder.tv_name.setVisibility(View.GONE);
            turnViewHolder.iv_pic.setVisibility(View.GONE);
            turnViewHolder.tv_reward.setVisibility(View.VISIBLE);

            turnViewHolder.tv_reward.setText("点击\n抽奖");

            turnViewHolder.ll_turn_content.setBackgroundResource(R.drawable.bg_turntable_start);
            turnViewHolder.ll_turn_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null)
                        mOnItemClickListener.onTurnStart();
                }
            });

        } else {

            turnViewHolder.tv_name.setVisibility(View.VISIBLE);
            turnViewHolder.iv_pic.setVisibility(View.VISIBLE);
            turnViewHolder.tv_reward.setVisibility(View.GONE);

            turnViewHolder.tv_name.setText(turnEntity.title);
//            GlideUtils.loadImageView(mContext, turnEntity.pic, turnViewHolder.iv_pic, R.mipmap.ic_launcher);
            turnViewHolder.iv_pic.setImageResource(R.mipmap.ic_launcher);

            turnViewHolder.ll_turn_content.setBackgroundResource(R.drawable.selector_turntable_item);
            if (turnEntity.state == 1) {
                turnViewHolder.ll_turn_content.setSelected(true);
            } else {
                turnViewHolder.ll_turn_content.setSelected(false);
            }
        }

    }

    @Override
    public int getItemCount() {
        return mTurnList == null ? 0 : mTurnList.size();
    }


    class TurnViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_turn_content;
        TextView tv_name;
        TextView tv_reward;
        ImageView iv_pic;

        public TurnViewHolder(View itemView) {
            super(itemView);
            ll_turn_content = itemView.findViewById(R.id.ll_turn_content);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_reward = itemView.findViewById(R.id.tv_reward);
            iv_pic = itemView.findViewById(R.id.iv_pic);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onTurnStart();

        void onTurnEnd();
    }
}
