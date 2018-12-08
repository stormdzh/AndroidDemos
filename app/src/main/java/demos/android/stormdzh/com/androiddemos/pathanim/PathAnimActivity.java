package demos.android.stormdzh.com.androiddemos.pathanim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import demos.android.stormdzh.com.androiddemos.R;

public class PathAnimActivity extends AppCompatActivity implements View.OnClickListener {

    private YWStudyProgView mYWStudyProgView;

    private ImageView ivAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_path_anim);

        findViewById(R.id.tv_start_anim).setOnClickListener(this);
        mYWStudyProgView = findViewById(R.id.mYWStudyProgView);

        ivAnim = findViewById(R.id.iv_anim);
        ivAnim.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_anim:
                startAnim();
                break;
            default:
                mYWStudyProgView.startPathAnim();
                break;
        }
    }

    private void startAnim() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.1f, 1f, 0.1f, 1f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.RESTART);
        animationSet.addAnimation(scaleAnimation);
        ivAnim.startAnimation(animationSet);
    }
}
