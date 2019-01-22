package demos.android.stormdzh.com.androiddemos.texthtml;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * textview 显示html https://www.cnblogs.com/xqxacm/p/5092557.html
 */
public class TextViewHtmlActivity extends AppCompatActivity {

    private TextView tvTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texthtml);

        tvTextView = findViewById(R.id.tvTextView);

//        String html = "<span style='color: #828D93;'><b style='font-size:18px;'>当前学币不足，分享可解锁</b><br/><span>每天都可邀请好友助力获<b style='font-size: 20px; color: #FF0000;'>20</b>学币解锁哦</span></span>";
        String html = "<font color='#828D93' size='24'> <b>当前学币不足，分享可解锁</b> </font>  <br/>  <font color='#828D93' size='18'> 每天都可邀请好友助力获 </font>  <font color='#ff0000' size='30'> <b>20</b> </font>  <font color='#828D93' size='18'> 学币解锁哦 </font>";

        //String html="<div style='color: #828D93;'><b style='font-size:18px;'>当前学币不足，分享可解锁</b><br/>每天都可邀请好友助力获<b style='font-size: 20px; color: #FFBB00;'>20</b>学币解锁哦</div>";

//        String html="<div  color='#828D93' size='24'><b style='font-size:18px;'>当前学币不足，分享可解锁</b><br/>每天都可邀请好友助力获<b style='font-size: 20px; color: #FFBB00;'>20</b>学币解锁哦</div>";

        //设置介绍
        if (Build.VERSION.SDK_INT >= 24) {
            tvTextView.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvTextView.setText(Html.fromHtml(html));
        }
    }
}
