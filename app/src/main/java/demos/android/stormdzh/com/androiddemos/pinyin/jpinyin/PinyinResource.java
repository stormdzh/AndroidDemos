package demos.android.stormdzh.com.androiddemos.pinyin.jpinyin;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import demos.android.stormdzh.com.androiddemos.MyApplication;

/**
 * 资源文件加载类
 *
 * @author stuxuhai (dczxxuhai@gmail.com)
 */
public final class PinyinResource {

    private PinyinResource() {
    }

    protected static Reader newClassPathReader(String classpath) {
        InputStream is = PinyinResource.class.getResourceAsStream(classpath);
        try {
            return new InputStreamReader(is, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    protected static Reader newFileReader(String path) {
        try {
            return new InputStreamReader(new FileInputStream(path), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static Reader newAssetReader(String path) {
        try {
//            InputStream abpath = context.getResourceAsStream("/assets/文件名");
            return new InputStreamReader(new FileInputStream(path), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static Map<String, String> getResource(Reader reader) {
        Map<String, String> map = new ConcurrentHashMap<String, String>();
        try {
            BufferedReader br = new BufferedReader(reader);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("=");
                map.put(tokens[0], tokens[1]);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    //    protected static Map<String, String> getPinyinResource() {
//        return getResource(newClassPathReader("/data/pinyin.dict"));
//    }
//
//    protected static Map<String, String> getMutilPinyinResource() {
//        return getResource(newClassPathReader("/data/mutil_pinyin.dict"));
//    }
//
//    protected static Map<String, String> getChineseResource() {
//        return getResource(newClassPathReader("/data/chinese.dict"));
//    }
    protected static Map<String, String> getPinyinResource() {
        return getResource(newFileReader("file:///android_asset/chinese/pinyin.dict"));
    }

    protected static Map<String, String> getMutilPinyinResource() {
        return getResource(newFileReader("file:///android_asset/chinese/mutil_pinyin.dict"));
    }

    protected static Map<String, String> getChineseResource() {
        return getResource(newFileReader("file:///android_asset/chinese/chinese.dict"));
    }


//    asset
//    InputStream is = getAssets().open(fileName);
//    int lenght = is.available();
//    byte[]  buffer = new byte[lenght];
//            is.read(buffer);
//    String result = = new String(buffer, "utf8");

//    file:///android_asset/chinese/html/index.htmll
}
