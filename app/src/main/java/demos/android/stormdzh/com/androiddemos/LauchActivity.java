package demos.android.stormdzh.com.androiddemos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demos.android.stormdzh.com.androiddemos.entity.lauch.RuanjianManagerInfoProvide;
import demos.android.stormdzh.com.androiddemos.entity.lauch.RuanjianmanagerInfo;

//https://blog.csdn.net/u011146511/article/details/80093578
//https://blog.csdn.net/AndroidDevWjm/article/details/7764740
public class LauchActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    List<RuanjianmanagerInfo> infos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lauch);

        mRecyclerView = findViewById(R.id.mRecyclerView);

        infos = RuanjianManagerInfoProvide.getInfos(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(new MyAdapter(infos));

    }

    class MyAdapter extends RecyclerView.Adapter {

        private List<RuanjianmanagerInfo> infos;

        public MyAdapter(List<RuanjianmanagerInfo> infos) {
            this.infos = infos;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainTopicViewHolder(LayoutInflater.from(LauchActivity.this).inflate(R.layout.item_lauch_test, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            RuanjianmanagerInfo info = infos.get(position);
            MainTopicViewHolder mholder = (MainTopicViewHolder) holder;
            mholder.tv_topic.setText(info.getName());
            mholder.ivLauch.setImageDrawable(info.getIcon());
        }

        @Override
        public int getItemCount() {
            return infos == null ? 0 : infos.size();
        }

        public class MainTopicViewHolder extends RecyclerView.ViewHolder {
            TextView tv_topic;
            ImageView ivLauch;

            public MainTopicViewHolder(View itemView) {
                super(itemView);
                tv_topic = (TextView) itemView.findViewById(R.id.tv_topic);
                ivLauch = itemView.findViewById(R.id.ivLauch);
            }
        }

    }

    @Override
    public void onBackPressed() {
        //吧手机系统的返回键注释掉
        // super.onBackPressed();
    }
}
