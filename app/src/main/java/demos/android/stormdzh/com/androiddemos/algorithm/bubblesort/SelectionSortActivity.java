package demos.android.stormdzh.com.androiddemos.algorithm.bubblesort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * 选择排序
 * <p>
 * <p>
 * a) 原理：每一趟从待排序的记录中选出最小的元素，顺序放在已排好序的序列最后，直到全部记录排序完毕。也就是：每一趟在n-i+1(i=1，2，…n-1)个记录中选取关键字最小的记录作为有序序列中第i个记录。基于此思想的算法主要有简单选择排序、树型选择排序和堆排序。（这里只介绍常用的简单选择排序）
 * <p>
 * b) 简单选择排序的基本思想：给定数组：int[] arr={里面n个数据}；第1趟排序，在待排序数据arr[1]~arr[n]中选出最小的数据，将它与arrr[1]交换；第2趟，在待排序数据arr[2]~arr[n]中选出最小的数据，将它与r[2]交换；以此类推，第i趟在待排序数据arr[i]~arr[n]中选出最小的数据，将它与r[i]交换，直到全部排序完成。
 * <p>
 * c) 举例：数组 int[] arr={5,2,8,4,9,1};
 * <p>
 * -------------------------------------------------------
 * <p>
 * 第一趟排序： 原始数据：5  2  8  4  9  1
 * <p>
 * 最小数据1，把1放在首位，也就是1和5互换位置，
 * <p>
 * 排序结果：1  2  8  4  9  5
 * <p>
 * -------------------------------------------------------
 * <p>
 * 第二趟排序：
 * <p>
 * 第1以外的数据{2  8  4  9  5}进行比较，2最小，
 * <p>
 * 排序结果：1  2  8  4  9  5
 * <p>
 * -------------------------------------------------------
 * <p>
 * 第三趟排序：
 * <p>
 * 除1、2以外的数据{8  4  9  5}进行比较，4最小，8和4交换
 * <p>
 * 排序结果：1  2  4  8  9  5
 * <p>
 * -------------------------------------------------------
 * <p>
 * 第四趟排序：
 * <p>
 * 除第1、2、4以外的其他数据{8  9  5}进行比较，5最小，8和5交换
 * <p>
 * 排序结果：1  2  4  5  9  8
 * <p>
 * -------------------------------------------------------
 * <p>
 * 第五趟排序：
 * <p>
 * 除第1、2、4、5以外的其他数据{9  8}进行比较，8最小，8和9交换
 * <p>
 * 排序结果：1  2  4  5  8  9
 * <p>
 * -------------------------------------------------------
 * <p>
 * 注：每一趟排序获得最小数的方法：for循环进行比较，定义一个第三个变量temp，首先前两个数比较，把较小的数放在temp中，然后用temp再去跟剩下的数据比较，如果出现比temp小的数据，就用它代替temp中原有的数据。具体参照后面的代码示例，相信你在学排序之前已经学过for循环语句了，这样的话，这里理解起来就特别容易了。
 */
public class SelectionSortActivity extends AppCompatActivity {

    private TextView tvResult;
    int[] arr = {6, 3, 8, 2, 9, 1};
    String result = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);


        tvResult = findViewById(R.id.tvResult);
        ((TextView) (findViewById(R.id.tvTitle))).setText("冒泡排序");
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSort();
            }
        });
    }

    long time = 0;

    private void startSort() {

        result += "排序前数组为：\n";
        for (int num : arr) {
            result = result + num + " ";
        }
        result += "\n\n";

        time = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {

            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {

                if (arr[j] < arr[minIndex]) {
                    //交换值

                    minIndex = j;
                }
            }

            if (i != minIndex) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }

            result += "第" + (i + 1) + "趟排序：";
            for (int num : arr) {
                result = result + num + " ";
            }
            result += "\n";
        }


        result = result + "共耗时：" + (System.currentTimeMillis() - time) + "毫秒";


        tvResult.setText(result);
    }

}
