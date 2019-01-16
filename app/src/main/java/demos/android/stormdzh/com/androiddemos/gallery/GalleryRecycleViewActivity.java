package demos.android.stormdzh.com.androiddemos.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.gallery.gallery.CardScaleHelper;

public class GalleryRecycleViewActivity extends AppCompatActivity implements CardScaleHelper.OnItemChangedListener {

    private CardScaleHelper mCardScaleHelper;
    private RecyclerView mRecyclerView;
    private List<Integer> mPics;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grally);
        mRecyclerView=findViewById(R.id.recyclerview);
        initList();
        initRecyclerView();
    }

    private void initList() {
        mPics=new ArrayList<>();
        mPics.add(R.mipmap.ic_launcher);
        mPics.add(R.mipmap.ic_launcher);
        mPics.add(R.mipmap.ic_launcher);
        mPics.add(R.mipmap.ic_launcher);
        mPics.add(R.mipmap.ic_launcher);
        mPics.add(R.mipmap.ic_launcher);
    }


    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new RecycleViewAdapter(this, mRecyclerView,mPics));

        // mRecyclerView绑定scale效果
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setOnItemChangedListener(this);
//        mCardScaleHelper.setPagePadding(ScreenUtil.dip2px(this,15));
        mCardScaleHelper.setCurrentItemPos(1);

        mCardScaleHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onItemChanged(int position) {
        Toast.makeText(this,"选中位置："+position,Toast.LENGTH_SHORT).show();
    }
}
