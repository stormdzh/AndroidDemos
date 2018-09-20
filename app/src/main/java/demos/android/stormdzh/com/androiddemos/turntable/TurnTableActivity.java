package demos.android.stormdzh.com.androiddemos.turntable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.adapter.turntable.TurntAdapter;
import demos.android.stormdzh.com.androiddemos.customview.turntable.TurntableView;
import demos.android.stormdzh.com.androiddemos.entity.turntable.TurnEntity;

/**
 * 抽奖
 * Created by a111 on 2018/9/20.
 */

public class TurnTableActivity extends AppCompatActivity implements TurntAdapter.OnItemClickListener {

    private TurntableView mTurntableView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_table);
        mTurntableView = findViewById(R.id.mTurntableView);

        setData();
    }

    private void setData() {

        List<TurnEntity> mList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mList.add(new TurnEntity(String.valueOf(i), "name->" + i, ""));
        }
        mList.add(4, new TurnEntity("", "", ""));
        mTurntableView.setData(mList);
        mTurntableView.setOnItemClickListener(this);
    }

    @Override
    public void onTurnStart() {
        mTurntableView.startTurn(4);
    }

    @Override
    public void onTurnEnd() {
        Toast.makeText(this, "恭喜中奖！", Toast.LENGTH_SHORT).show();
    }
}
