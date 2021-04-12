package demos.android.stormdzh.com.androiddemos.segmentedbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import demos.android.stormdzh.com.androiddemos.R;
import demos.android.stormdzh.com.androiddemos.segmentedbar.view.Segment;
import demos.android.stormdzh.com.androiddemos.segmentedbar.view.SegmentedBarView;

/**
 * @Description: 参考：https://github.com/gspd-mobi/SegmentedBarView-Android
 * @Author: dzh
 * @CreateDate: 2021-04-12 10:32
 */
public class SegmentedBarActivity extends Activity {

    private SegmentedBarView mSegmentedBarView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_segmentedbar);


        mSegmentedBarView =findViewById(R.id.bar_view);

        List<Segment> segments = new ArrayList<>();
        Segment segment = new Segment(0, 4.5f, "", Color.parseColor("#EF3D2F"));
        segments.add(segment);
        Segment segment2 = new Segment(4.5f, 6.5f, "", Color.parseColor("#8CC63E"));
        segments.add(segment2);
        Segment segment3 = new Segment(6.5f, 20f, "", Color.parseColor("#EF3D2F"));
        segments.add(segment3);

        mSegmentedBarView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mSegmentedBarView.setPadding(0, getResources().getDimensionPixelSize(R.dimen.vertical_padding), 0, 0);
        mSegmentedBarView.setSegments(segments);
    }
}
