package demos.android.stormdzh.com.androiddemos.mp4parser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.mp4parser.utils.Mp4ParseUtil;

public class MP4ParserActivity extends AppCompatActivity implements View.OnClickListener {

    private Demo demo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mp4parser);
        demo = new Demo();
        findViewById(R.id.bnt_merge_mp4).setOnClickListener(this);
        findViewById(R.id.bnt_geshi_audio).setOnClickListener(this);
        findViewById(R.id.mp4Addaac).setOnClickListener(this);
        findViewById(R.id.splitMp4).setOnClickListener(this);
        findViewById(R.id.addZimu).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            追加mp4
            case R.id.bnt_merge_mp4:
                demo.doMp4Append();
                break;
                //aacTmp3
            case R.id.bnt_geshi_audio:
                demo.aacTmp3();
                break;
                //mp4Addaac
            case R.id.mp4Addaac:
                mp4Addaac();
                break;
                //splitMp4
            case R.id.splitMp4:
                splitMp4();
                break;
                //addZimu
            case R.id.addZimu:
                addZimu();
                break;
        }
    }

    private void addZimu() {

        String srcPath = "/storage/emulated/0/ffmpeg/v2_and_atest1_quit.mp4";
        String outPath = "/storage/emulated/0/ffmpeg/v2_and_atest1_quit_zimu.mp4";
        try {
            Mp4ParseUtil.addSubtitles(srcPath, outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void splitMp4() {
        String srcPath = "/storage/emulated/0/ffmpeg/v2_and_atest1.mp4";
        String outPath = "/storage/emulated/0/ffmpeg/v2_and_atest1_quit.mp4";
        try {
            Mp4ParseUtil.splitMp4(srcPath, outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void mp4Addaac() {

        String aacPath = "/storage/emulated/0/aaaa/aacTowav.wav";
//        String aacPath = "/storage/emulated/0/ffmpeg/audio1.mp3";  //不能使用mp3合并
        String mp4Path = "/storage/emulated/0/aaaa/video.mp4";
        String outPath = "/storage/emulated/0/aaaa/video-and-wav.mp4";
        Mp4ParseUtil.muxAacMp4(aacPath, mp4Path, outPath);
    }
}
