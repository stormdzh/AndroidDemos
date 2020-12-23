package demos.android.stormdzh.com.androiddemos.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.test.bubble.MsgBubbleView;

/**
 * @Description: 描述
 * @Author: dzh
 * @CreateDate: 2020-10-19 17:37
 */
public class BubbleAnimActivity extends Activity implements View.OnClickListener {

    private MsgBubbleView mineMsgBubbleView;
    private View tvView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bubble_anim);

        findViewById(R.id.tvShow).setOnClickListener(this);
        findViewById(R.id.tvhint).setOnClickListener(this);

        mineMsgBubbleView = findViewById(R.id.mineMsgBubbleView);
        tvView = findViewById(R.id.tvView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvShow:
                show();
                break;
            case R.id.tvhint:
                hint();
                break;
        }
    }

    private void show() {
        mineMsgBubbleView.setData(tvView);
    }

    private void hint() {
        mineMsgBubbleView.cancelBubble(tvView);
    }
}
