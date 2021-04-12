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
import demos.android.stormdzh.com.androiddemos.algorithm.AlgorithmActivity;
import demos.android.stormdzh.com.androiddemos.audioconvert.AudioConvertActivity;
import demos.android.stormdzh.com.androiddemos.audiomerge.AudioMergeActivity;
import demos.android.stormdzh.com.androiddemos.bezier.BezierActivity;
import demos.android.stormdzh.com.androiddemos.bezier.BezierActivity2;
import demos.android.stormdzh.com.androiddemos.bezier.BezierActivity3;
import demos.android.stormdzh.com.androiddemos.bezier.BezierActivity4;
import demos.android.stormdzh.com.androiddemos.broadcast.LocalBroadCastActivity;
import demos.android.stormdzh.com.androiddemos.broadcast.NormalBroadCastActivity;
import demos.android.stormdzh.com.androiddemos.catchdata.CatchDataActivity;
//import demos.android.stormdzh.com.androiddemos.crop.SmartCropperActivity;
import demos.android.stormdzh.com.androiddemos.dragger2_test.Dragger2TestActivity;
import demos.android.stormdzh.com.androiddemos.entity.main.MainEntity;
import demos.android.stormdzh.com.androiddemos.excel.ExcelTestActivity;
import demos.android.stormdzh.com.androiddemos.facepic.FacePicActivity;
import demos.android.stormdzh.com.androiddemos.floattop.FloatTopActivity;
import demos.android.stormdzh.com.androiddemos.gallery.GalleryRecycleViewActivity;
import demos.android.stormdzh.com.androiddemos.gallery.GalleryViewpagerActivity;
import demos.android.stormdzh.com.androiddemos.gson.GsonTestActivity;
import demos.android.stormdzh.com.androiddemos.hook.HookClickActivity;
import demos.android.stormdzh.com.androiddemos.interview.HandlerThreadActivity;
import demos.android.stormdzh.com.androiddemos.jobservice.JobServiceActivity;
import demos.android.stormdzh.com.androiddemos.listener.OnMainItemClickListener;
import demos.android.stormdzh.com.androiddemos.localsocket.LocalSocketClientActivity;
import demos.android.stormdzh.com.androiddemos.localsocket.LocalSocketServerActivity;
import demos.android.stormdzh.com.androiddemos.mp4parser.MP4ParserActivity;
import demos.android.stormdzh.com.androiddemos.notify.NotificationUtil;
import demos.android.stormdzh.com.androiddemos.notify.NotifyActivity;
import demos.android.stormdzh.com.androiddemos.notify.wx.ResidentNotificationView;
import demos.android.stormdzh.com.androiddemos.pathanim.PathAnimActivity;
import demos.android.stormdzh.com.androiddemos.pinyin.PingyinActivity;
import demos.android.stormdzh.com.androiddemos.porterduff.PorterDuffActivity;
import demos.android.stormdzh.com.androiddemos.retrofit_rx_test.RetrofitRxTestActivity;
import demos.android.stormdzh.com.androiddemos.retrofit_test.RetrofitTestActivity;
import demos.android.stormdzh.com.androiddemos.test.BubbleAnimActivity;
import demos.android.stormdzh.com.androiddemos.test.DigitMsgActivity;
import demos.android.stormdzh.com.androiddemos.test.HandlerActivity;
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

        testNotify();
    }

    private void testNotify() {
//        NotificationUtil mNotificationUtil=new NotificationUtil();
//        mNotificationUtil.init(this);
//        mNotificationUtil.showBroacast();
        ResidentNotificationView mNotificationUtil=new ResidentNotificationView();
        mNotificationUtil.init(this);
        mNotificationUtil.test();
    }

    private void initData() {
        mainList = new ArrayList<>();

        mainList.add(new MainEntity("测试JobService", JobServiceActivity.class));
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
        mainList.add(new MainEntity("颜色叠加模式定义控件", PorterDuffActivity.class));
//        mainList.add(new MainEntity("基于opencv图片裁剪", SmartCropperActivity.class));
        mainList.add(new MainEntity("算法-java", AlgorithmActivity.class));
        mainList.add(new MainEntity("TAB悬浮", FloatTopActivity.class));
        mainList.add(new MainEntity("人脸图片", FacePicActivity.class));
        mainList.add(new MainEntity("Hook Click", HookClickActivity.class));
        mainList.add(new MainEntity("常驻通知栏", NotifyActivity.class));
        mainList.add(new MainEntity("Handler源码分析", HandlerActivity.class));
        mainList.add(new MainEntity("消息层级", DigitMsgActivity.class));
        mainList.add(new MainEntity("气泡动画", BubbleAnimActivity.class));
        mainList.add(new MainEntity("全局广播", NormalBroadCastActivity.class));
        mainList.add(new MainEntity("本地广播", LocalBroadCastActivity.class));
        mainList.add(new MainEntity("Local Socket 服务端", LocalSocketServerActivity.class));
        mainList.add(new MainEntity("Local Socket 客户端", LocalSocketClientActivity.class));
        mainList.add(new MainEntity("贝塞尔曲线", BezierActivity.class));
        mainList.add(new MainEntity("贝塞尔曲线2", BezierActivity2.class));
        mainList.add(new MainEntity("贝塞尔曲线3", BezierActivity3.class));
        mainList.add(new MainEntity("贝塞尔曲线4", BezierActivity4.class));
        mainList.add(new MainEntity("HandlerThread", HandlerThreadActivity.class));
        mainList.add(new MainEntity("测试Gson原理", GsonTestActivity.class));
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
