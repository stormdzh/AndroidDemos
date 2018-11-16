package demos.android.stormdzh.com.androiddemos.pinyin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;

public class PingyinActivity extends AppCompatActivity {

    private TextView tvShowText;
    private TextView tvPingyinText;
    private TextView tvChinesext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pinyin);

        tvShowText = findViewById(R.id.tvShowText);
        tvPingyinText = findViewById(R.id.tvPingyinText);
        tvChinesext = findViewById(R.id.tvChinesext);

        test1();

        test2();

    }

    private void test2() {
//        tvPingyinText.setText(converterToFirstSpell("你好世界"));
        tvPingyinText.setText(converterToSpell("你好世界"));
        tvChinesext.setText("你好世界");
    }

    private void test1() {
        String gstr = "[\"d\\u00e0i\",\"zhe\",\"x\\u012b\",\"w\\u00e0ng\",\"q\\u00f9\",\"l\\u01da\",\"x\\u00edng\",\"b\\u01d0\",\"d\\u00e0o\",\"d\\u00e1\",\"zh\\u014dng\",\"di\\u01cen\",\"g\\u00e8ng\",\"m\\u011bi\",\"h\\u01ceo\"]";
        Gson gson = new GsonBuilder().create();
        List<String> pList = gson.fromJson(gstr, new TypeToken<List<String>>() {
        }.getType());

        String pin="";
        for(int i=0;i<pList.size();i++){
            pin+=pList.get(i);
        }

        tvShowText.setText(pin);
    }

    public static String converterToFirstSpell(String chines){
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    Log.i("test","nameChar[i]:"+nameChar[i]);
                    Log.i("test","defaultFormat:"+defaultFormat);
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }

    /**
     * 汉字转换位汉语拼音，英文字符不变
     * @param chines 汉字
     * @return 拼音
     */
    public static String converterToSpell(String chines){
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }
}
