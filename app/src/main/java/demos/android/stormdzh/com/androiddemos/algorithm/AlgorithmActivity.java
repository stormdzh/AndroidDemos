package demos.android.stormdzh.com.androiddemos.algorithm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.adapter.algorithm.AlgorithmAdapter;
import demos.android.stormdzh.com.androiddemos.algorithm.bubblesort.BubbleSortActivity;
import demos.android.stormdzh.com.androiddemos.algorithm.bubblesort.SelectionSortActivity;
import demos.android.stormdzh.com.androiddemos.entity.main.MainEntity;
import demos.android.stormdzh.com.androiddemos.listener.OnMainItemClickListener;

public class AlgorithmActivity extends AppCompatActivity implements OnMainItemClickListener {

    private List<MainEntity> mainList;
    private RecyclerView mRecyclerView;
    private AlgorithmAdapter mainAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);


        initData();
        initView();
    }

    private void initData() {
        mainList = new ArrayList<>();

        mainList.add(new MainEntity("冒泡排序", BubbleSortActivity.class));
        mainList.add(new MainEntity("选择排序", SelectionSortActivity.class));
        mainList.add(new MainEntity("ViewGroup遍历", ViewTreeActivity.class));

        mainAdapter = new AlgorithmAdapter(this, mainList);
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
