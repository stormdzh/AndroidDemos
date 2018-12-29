package demos.android.stormdzh.com.androiddemos.mp4parser;

import android.media.MediaFormat;
import android.util.Log;

import com.pocketdigi.utils.FLameUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import demos.android.stormdzh.com.androiddemos.mp4parser.utils.AudioCodec;
import demos.android.stormdzh.com.androiddemos.mp4parser.utils.Mp4ParseUtil;

public class Demo {


    /**
     * 执行MP4的追加合成
     */
    public void doMp4Append() {
        String v1="/storage/emulated/0/ffmpeg/v1.mp4";
        String v2="/storage/emulated/0/ffmpeg/v2.mp4";
        String outpath="/storage/emulated/0/ffmpeg/hebing_mp4_mpapaser.mp4";
//        String rootPath="";
        try {
            List<String> mp4PathList = new ArrayList<>();
            mp4PathList.add(v1);
            mp4PathList.add(v2);
//            mp4PathList.add(rootPath + "/resource/" + "video1" + ".mp4");
//            mp4PathList.add(rootPath + "/resource/" + "video2" + ".mp4");
//            String outPutPath = rootPath + "/output/" + "outVideo" + ".mp4";
//            Mp4ParseUtil.appendMp4List(mp4PathList, outPutPath);
            Mp4ParseUtil.appendMp4List(mp4PathList, outpath);
            Log.e("test","合并追加完成");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test","异常");
        }
    }



    public void AacTmp3(){
//        String path=Environment.getExternalStorageDirectory().getAbsolutePath();
        final AudioCodec audioCodec=AudioCodec.newInstance();
        audioCodec.setEncodeType(MediaFormat.MIMETYPE_AUDIO_MPEG);
        audioCodec.setIOPath( "/storage/emulated/0/ffmpeg/audio_mp3ToAac.mp3","/storage/emulated/0/ffmpeg/audio.aac");
        audioCodec.prepare();
        audioCodec.startAsync();
        audioCodec.setOnCompleteListener(new AudioCodec.OnCompleteListener() {
            @Override
            public void completed() {
                audioCodec.release();
                Log.i("test","格式转化完成");
            }
        });
    }
    public void aacTmp3(){
//        String path=Environment.getExternalStorageDirectory().getAbsolutePath();
//        final AudioCodec audioCodec=AudioCodec.newInstance();
//        audioCodec.setEncodeType(MediaFormat.MIMETYPE_AUDIO_MPEG);
//        audioCodec.setIOPath( "/storage/emulated/0/ffmpeg/audio_mp3ToAac.mp3","/storage/emulated/0/ffmpeg/audio.aac");
//        audioCodec.prepare();
//        audioCodec.startAsync();
//        audioCodec.setOnCompleteListener(new AudioCodec.OnCompleteListener() {
//            @Override
//            public void completed() {
//                audioCodec.release();
//                Log.i("test","格式转化完成");
//            }
//        });

        String p1="/storage/emulated/0/ffmpeg/audio.aac";
        String p2="/storage/emulated/0/ffmpeg/Lame_audio_mp3ToAac.mp3";

        FLameUtils lameUtils = new FLameUtils(1, 16000, 96);

        lameUtils.raw2mp3(p1, p2);
    }

}
