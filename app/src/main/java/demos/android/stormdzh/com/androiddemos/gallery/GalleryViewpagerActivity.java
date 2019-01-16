package demos.android.stormdzh.com.androiddemos.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.gallery.gallery.CardScaleHelper;

public class GalleryViewpagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, GalleryViewpagerAdapter.OnWordDetailClickListener {

    private ViewPager mViewPager;
    private List<Integer> mPics;
    private GalleryViewpagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grally_viewpager);
        mViewPager = findViewById(R.id.mViewPager);
        initList();
        initRecyclerView();
    }

    private void initList() {
        mPics = new ArrayList<>();
        mPics.add(R.mipmap.ic_launcher);
        mPics.add(R.mipmap.ic_launcher);
        mPics.add(R.mipmap.ic_launcher);
        mPics.add(R.mipmap.ic_launcher);
        mPics.add(R.mipmap.ic_launcher);
        mPics.add(R.mipmap.ic_launcher);
    }


    private void initRecyclerView() {
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.addOnPageChangeListener(this);

        mAdapter = new GalleryViewpagerAdapter(this, mPics);
        mAdapter.setOnWordDetailClickListener(this);
        mViewPager.setAdapter(mAdapter);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onWordDetailClick() {

    }
}
