package demos.android.stormdzh.com.androiddemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import demos.android.stormdzh.com.androiddemos.adapter.main.MainAdapter;
import demos.android.stormdzh.com.androiddemos.entity.main.MainEntity;
import demos.android.stormdzh.com.androiddemos.listener.OnMainItemClickListener;
import demos.android.stormdzh.com.androiddemos.turntable.TurnTableActivity;

public class MainActivity extends AppCompatActivity implements OnMainItemClickListener {

    private List<MainEntity> mainList;
    private RecyclerView mRecyclerView;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mainList = new ArrayList<>();

        mainList.add(new MainEntity("抽奖", TurnTableActivity.class));
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
