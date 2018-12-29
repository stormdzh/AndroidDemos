package demos.android.stormdzh.com.androiddemos.audioconvert;

import android.media.MediaFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.pocketdigi.utils.FLameUtils;

import java.io.File;
import java.io.IOException;

import cafe.adriel.androidaudioconverter.AndroidAudioConverter;
import cafe.adriel.androidaudioconverter.callback.IConvertCallback;
import cafe.adriel.androidaudioconverter.model.AudioFormat;
import demos.android.stormdzh.com.androiddemos.R;
import it.sauronsoftware.jave.EncoderException;

public class AudioConvertActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_audio_convert);
        findViewById(R.id.wavTomp3).setOnClickListener(this);
        findViewById(R.id.accTomp3).setOnClickListener(this);
        findViewById(R.id.mp3Toacc).setOnClickListener(this);
        findViewById(R.id.accTowav).setOnClickListener(this);

        findViewById(R.id.audioCodeTest).setOnClickListener(this);

        findViewById(R.id.lame_wavTomp3).setOnClickListener(this);


        findViewById(R.id.c_mp3ToWav).setOnClickListener(this);
        findViewById(R.id.c_wavToMp3).setOnClickListener(this);
        findViewById(R.id.c_pcmToWav).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wavTomp3:
                wavTomp3();
                break;
            case R.id.mp3Toacc:
                mp3Toacc();
                break;
            case R.id.accTomp3:
                accTomp3();
                break;
            case R.id.accTowav:
                accTowav();
                break;
            case R.id.lame_wavTomp3:
                lame_wavTomp3();
                break;
            //audioCode
            case R.id.audioCodeTest:
                pcmToaac();
                break;

            case R.id.c_mp3ToWav:
                c_mp3ToWav();
                break;
            case R.id.c_wavToMp3:
                break;
            case R.id.c_pcmToWav:
                break;
        }
    }

    private void c_mp3ToWav() {

        String mp3 = "/storage/emulated/0/aaaa/mp3.mp3";
        String c_mp3_wav = "/storage/emulated/0/aaaa/c_mp3_wav.wav";
        File targetFile = new File(c_mp3_wav);
        if (!targetFile.exists()) {
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            AudioFormatConvertUtil.mp3ToWav(new File(mp3), targetFile, false);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }


    private void lame_wavTomp3() {

        String src = "/storage/emulated/0/aaaa/wav.wav";
        String des = "/storage/emulated/0/aaaa/lame_wav_mp3.mp3";

        FLameUtils lameUtils = new FLameUtils(1, 8000, 16);
//        FLameUtils lameUtils = new FLameUtils();

//        lameUtils.raw2mp3(Environment.getExternalStorageDirectory() + "/20130306172218.raw", Environment.getExternalStorageDirectory + "/bbbb.mp3");
        boolean b = lameUtils.raw2mp3(src, des);
        Log.i("test", "lame_wavTomp3==>" + b);

    }


    private void accTowav() {

        Log.i("test", " accTomp3 开始");
//        File flacFile = new File(Environment.getExternalStorageDirectory(), "my_audio.flac");
        File aacFile = new File("/storage/emulated/0/aaaa/aac.aac");
        IConvertCallback callback = new IConvertCallback() {
            @Override
            public void onSuccess(File convertedFile) {
                // So fast? Love it!
                if (convertedFile != null && convertedFile.exists()) {
                    Log.i("test", "onSuccess  转换完成=》" + convertedFile.getAbsolutePath());
                }

            }

            @Override
            public void onFailure(Exception error) {
                // Oops! Something went wrong
                Log.i("test", "onFailure  转换失败=》");
            }
        };
        AndroidAudioConverter.with(this)
                // Your current audio file
                .setFile(aacFile)

                // Your desired audio format
                .setFormat(AudioFormat.WAV)

                // An callback to know when conversion is finished
                .setCallback(callback)

                // Start conversion
                .convert();
    }

    private void wavTomp3() {
        Log.i("test", " accTomp3 开始");
//        File flacFile = new File(Environment.getExternalStorageDirectory(), "my_audio.flac");
        File aacFile = new File("/storage/emulated/0/aaaa/aacTowav.wav");
        IConvertCallback callback = new IConvertCallback() {
            @Override
            public void onSuccess(File convertedFile) {
                // So fast? Love it!
                if (convertedFile != null && convertedFile.exists()) {
                    Log.i("test", "onSuccess  转换完成=》" + convertedFile.getAbsolutePath());
                }

            }

            @Override
            public void onFailure(Exception error) {
                // Oops! Something went wrong
                Log.i("test", "onFailure  转换失败=》");
            }
        };
        AndroidAudioConverter.with(this)
                // Your current audio file
                .setFile(aacFile)

                // Your desired audio format
                .setFormat(AudioFormat.MP3)

                // An callback to know when conversion is finished
                .setCallback(callback)

                // Start conversion
                .convert();
    }

    private void accTomp3() {
        Log.i("test", " accTomp3 开始");
//        File flacFile = new File(Environment.getExternalStorageDirectory(), "my_audio.flac");
        File aacFile = new File("/storage/emulated/0/aaaa/aac.aac");
        IConvertCallback callback = new IConvertCallback() {
            @Override
            public void onSuccess(File convertedFile) {
                // So fast? Love it!
                if (convertedFile != null && convertedFile.exists()) {
                    Log.i("test", "onSuccess  转换完成=》" + convertedFile.getAbsolutePath());
                }

            }

            @Override
            public void onFailure(Exception error) {
                // Oops! Something went wrong
                Log.i("test", "onFailure  转换失败=》");
            }
        };
        AndroidAudioConverter.with(this)
                // Your current audio file
                .setFile(aacFile)

                // Your desired audio format
                .setFormat(AudioFormat.MP3)

                // An callback to know when conversion is finished
                .setCallback(callback)

                // Start conversion
                .convert();
    }

    private void mp3Toacc() {

    }

    private void pcmToaac() {
//        String path=Environment.getExternalStorageDirectory().getAbsolutePath();
        final AudioCodec audioCodec = AudioCodec.newInstance();
//        audioCodec.setEncodeType(MediaFormat.MIMETYPE_AUDIO_MPEG);
        audioCodec.setEncodeType(MediaFormat.MIMETYPE_AUDIO_AAC);
//        audioCodec.setIOPath(path + "/codec.aac", path + "/encode.mp3");
//        audioCodec.setIOPath("/storage/emulated/0/aaaa/aac.aac", "/storage/emulated/0/aaaa/AudioCodec-aac-mp3.mp3");
//        audioCodec.setIOPath("/storage/emulated/0/aaaa/aacTomp3.mp3", "/storage/emulated/0/aaaa/AudioCodec-mp3-aac.aac");
        audioCodec.setIOPath("/storage/emulated/0/ffmpeg/wav2mp3.mp3", "/storage/emulated/0/ffmpeg/AudioCodec-mp3-aac.aac");
        audioCodec.prepare();
        audioCodec.startAsync();
        audioCodec.setOnCompleteListener(new AudioCodec.OnCompleteListener() {
            @Override
            public void completed() {
                audioCodec.release();
            }
        });

    }


}
