package demos.android.stormdzh.com.androiddemos.customview.turntable;

/**
 * 抽奖转盘
 * Created by a111 on 2018/9/5.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.adapter.turntable.TurntAdapter;
import demos.android.stormdzh.com.androiddemos.customview.common.GridDividerItemDecoration;
import demos.android.stormdzh.com.androiddemos.entity.turntable.TurnEntity;
import demos.android.stormdzh.com.androiddemos.util.AppUtil;


public class TurntableView extends RelativeLayout implements TurntAdapter.OnItemClickListener {

    private final int CODE_TURN_LOCATION = 222;          //定位到结果的what值
    private final int CODE_TURN = 200;          //倒计时的what值
    private final int TIME_START_DElLAYED = 100;//开始倒计时延迟
    private final int TIME_TURN_DEFAULT = 80;   //默认旋转速度
    private final int TIME_TURN_MIN = 30;       //旋转最小速度
    private final int TIME_TURN_MAX = 200;      //旋转最大速度
    private final int TIME_TURN_TOTAL = 5000;   //旋转总时间

    private int speedType; //0：加速 1：减速  2：定位
    private int selectIndex = 1; //最后选中的条目
    private int lackStep; //最后缺少的步骤

    private int mTurnSpeed = TIME_TURN_DEFAULT;
    private List<TurnEntity> turnList;
    private RecyclerView recyclerView;
    private TurntAdapter mTurntAdapter;

    private TurnCountDownTimer mTurnCountDownTimer;

    public TurntableView(Context context) {
        this(context, null);
    }

    public TurntableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TurntableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_truntable, this);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addItemDecoration(new GridDividerItemDecoration(AppUtil.dip2px(context, 3), Color.TRANSPARENT));

//        turnList = new ArrayList<>();
//        for (int i = 1; i < 10; i++) {
//            turnList.add(new TurnEntity("title-" + i));
//        }
//
//        mTurntAdapter = new TurntAdapter(getContext(), turnList);
//        mTurntAdapter.setOnItemClickListener(this);
//        recyclerView.setAdapter(mTurntAdapter);


    }

    public void setData(List<TurnEntity> turnList) {
        this.turnList = turnList;
        mTurntAdapter = new TurntAdapter(getContext(), turnList);
        mTurntAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mTurntAdapter);
    }

    private TurntAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(TurntAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //抽奖旋转
                case CODE_TURN:
                    change2Next();
                    changeTurnTime();
                    Log.i("test", "speedType=>" + speedType + " mTurnSpeed=>" + mTurnSpeed);
                    mHandler.sendEmptyMessageDelayed(CODE_TURN, mTurnSpeed);
                    break;
                case CODE_TURN_LOCATION:
                    if (lackStep >= 0) {
                        change2Next();
                        mHandler.sendEmptyMessageDelayed(CODE_TURN_LOCATION, 300);
                        lackStep--;
                    } else {
                        mHandler.removeMessages(CODE_TURN_LOCATION);
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onTurnEnd();
                        }
                    }
                    break;
                default:
                    break;
            }

        }
    };

    private void changeTurnTime() {

        if (speedType == 0) { //加速
            int dspeed = (int) (10 + Math.random() * (20 - 10 + 1));
            mTurnSpeed = mTurnSpeed - dspeed;
            if (mTurnSpeed < TIME_TURN_MIN)
                mTurnSpeed = TIME_TURN_MIN;
            if (mTurnSpeed > TIME_TURN_MAX)
                mTurnSpeed = TIME_TURN_MAX;
        } else if (speedType == 1) { //减速
            int dspeed = (int) (10 + Math.random() * (20 - 10 + 1));
            mTurnSpeed = mTurnSpeed + dspeed;
            if (mTurnSpeed < TIME_TURN_MIN)
                mTurnSpeed = TIME_TURN_MIN;
            if (mTurnSpeed > TIME_TURN_MAX)
                mTurnSpeed = TIME_TURN_MAX;
        }

    }

    private int[] sortArray = new int[]{3, 0, 1, 2, 5, 8, 7, 6};
    private int curFocusIndex = 0;

    private void change2Next() {
        //旋转的顺序
        // 2 3 4             0 1 2
        // 1   5    =>       3 4 5
        // 8 7 6             6 7 8
        changeIndexFocus(curFocusIndex);
        curFocusIndex++;
        if (curFocusIndex >= 8)
            curFocusIndex = 0;
        mTurntAdapter.notifyDataSetChanged();

    }

    private void changeIndexFocus(int curFocusIndex) {
        for (int i = 0; i < 8; i++) {
            if (i == curFocusIndex) {
                turnList.get(sortArray[i]).state = 1;
            } else {
                turnList.get(sortArray[i]).state = 0;
            }
        }
    }

    public void startTurn(int index) {
        selectIndex = index;
        mTurnSpeed = TIME_TURN_DEFAULT;
        mHandler.removeMessages(CODE_TURN_LOCATION);
        mHandler.sendEmptyMessageDelayed(CODE_TURN, TIME_START_DElLAYED);
        mTurnCountDownTimer = new TurnCountDownTimer(TIME_TURN_TOTAL, TIME_TURN_MIN);
        mTurnCountDownTimer.start();
    }

    @Override
    public void onTurnStart() {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onTurnStart();
//        startTurn();
    }

    @Override
    public void onTurnEnd() {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onTurnEnd();
    }

    class TurnCountDownTimer extends CountDownTimer {

        public TurnCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //前 1/3提速   中 1/3 减速   后 1/3 定位
            if ((TIME_TURN_TOTAL - millisUntilFinished) < TIME_TURN_TOTAL / 3) {
                speedType = 0;
            } else {
                speedType = 1;
            }
        }

        @Override
        public void onFinish() {
            if (mHandler != null)
                mHandler.removeMessages(CODE_TURN);

            int mc = curFocusIndex;
//            if (curFocusIndex <= 0) {
//                mc = 7;
//            } else {
//                mc = curFocusIndex - 1;
//            }


            if (selectIndex > mc) {
                lackStep = selectIndex - mc;
            } else if (selectIndex < mc) {
                lackStep = selectIndex + 8 - mc;
            } else { //刚好相等就在在走一圈
                lackStep = 8;
            }

            Log.i("test", "mc-->" + mc + "   lackStep===>" + lackStep);

            // 在以300速度走lackStep步
            mHandler.sendEmptyMessageDelayed(CODE_TURN_LOCATION, 300);
        }
    }

}