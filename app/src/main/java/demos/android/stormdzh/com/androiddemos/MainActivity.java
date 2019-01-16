package demos.android.stormdzh.com.androiddemos;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import demos.android.stormdzh.com.androiddemos.adapter.main.MainAdapter;
import demos.android.stormdzh.com.androiddemos.audioconvert.AudioConvertActivity;
import demos.android.stormdzh.com.androiddemos.audiomerge.AudioMergeActivity;
import demos.android.stormdzh.com.androiddemos.catchdata.CatchDataActivity;
import demos.android.stormdzh.com.androiddemos.dragger2_test.Dragger2TestActivity;
import demos.android.stormdzh.com.androiddemos.entity.main.MainEntity;
import demos.android.stormdzh.com.androiddemos.excel.ExcelTestActivity;
import demos.android.stormdzh.com.androiddemos.gallery.GalleryRecycleViewActivity;
import demos.android.stormdzh.com.androiddemos.gallery.GalleryViewpagerActivity;
import demos.android.stormdzh.com.androiddemos.listener.OnMainItemClickListener;
import demos.android.stormdzh.com.androiddemos.mp4parser.MP4ParserActivity;
import demos.android.stormdzh.com.androiddemos.pathanim.PathAnimActivity;
import demos.android.stormdzh.com.androiddemos.pinyin.PingyinActivity;
import demos.android.stormdzh.com.androiddemos.retrofit_rx_test.RetrofitRxTestActivity;
import demos.android.stormdzh.com.androiddemos.retrofit_test.RetrofitTestActivity;
import demos.android.stormdzh.com.androiddemos.texthtml.TextViewHtmlActivity;
import demos.android.stormdzh.com.androiddemos.turntable.TurnTableActivity;

public class MainActivity extends AppCompatActivity implements OnMainItemClickListener {

    private List<MainEntity> mainList;
    private RecyclerView mRecyclerView;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demos.android.stormdzh.com.androiddemos.util.PermissionUtil.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        initData();
        initView();
    }

    private void initData() {
        mainList = new ArrayList<>();

        mainList.add(new MainEntity("抽奖", TurnTableActivity.class));
        mainList.add(new MainEntity("Excel表格", ExcelTestActivity.class));
        mainList.add(new MainEntity("数据抓取", CatchDataActivity.class));
        mainList.add(new MainEntity("RetrofitTest", RetrofitTestActivity.class));
        mainList.add(new MainEntity("Retrofit+RX_Test", RetrofitRxTestActivity.class));
        mainList.add(new MainEntity("Dragger2_Test", Dragger2TestActivity.class));
        mainList.add(new MainEntity("汉字拼音", PingyinActivity.class));
        mainList.add(new MainEntity("音频合并", AudioMergeActivity.class));
        mainList.add(new MainEntity("MP4Parser-Android视频编辑解析库", MP4ParserActivity.class));
        mainList.add(new MainEntity("AudioConverter - 音频格式转换", AudioConvertActivity.class));
        mainList.add(new MainEntity("path-路径动画", PathAnimActivity.class));
        mainList.add(new MainEntity("画廊效果-recycleView", GalleryRecycleViewActivity.class));
        mainList.add(new MainEntity("画廊效果-viewpager", GalleryViewpagerActivity.class));
        mainList.add(new MainEntity("TextView显示html标签", TextViewHtmlActivity.class));
        mainAdapter = new MainAdapter(this, mainList);
        mainAdapter.setOnMainItemClickListener(this);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void onMainItemClick(MainEntity entity) {
        if (entity == null) return;
        startActivity(new Intent(this, entity.clazz).putExtra("name", entity.name));
    }
}
