package demos.android.stormdzh.com.androiddemos.algorithm.bubblesort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import demos.android.stormdzh.com.androiddemos.R;

/**
 * 冒泡排序
 *
 *
 * 原理：比较两个相邻的元素，将值大的元素交换至右端。
 *
 * 思路：依次比较相邻的两个数，将小数放在前面，大数放在后面。即在第一趟：首先比较第1个和第2个数，将小数放前，大数放后。然后比较第2个数和第3个数，将小数放前，大数放后，如此继续，直至比较最后两个数，将小数放前，大数放后。重复第一趟步骤，直至全部排序完成。
 *
 * 第一趟比较完成后，最后一个数一定是数组中最大的一个数，所以第二趟比较的时候最后一个数不参与比较；
 *
 * 第二趟比较完成后，倒数第二个数也一定是数组中第二大的数，所以第三趟比较的时候最后两个数不参与比较；
 *
 * 依次类推，每一趟比较次数-1；
 *
 * ……
 *
 * 举例说明：要排序数组：int[] arr={6,3,8,2,9,1};
 *
 * 第一趟排序：
 *
 * 　　　　第一次排序：6和3比较，6大于3，交换位置：  3  6  8  2  9  1
 *
 * 　　　　第二次排序：6和8比较，6小于8，不交换位置：3  6  8  2  9  1
 *
 * 　　　　第三次排序：8和2比较，8大于2，交换位置：  3  6  2  8  9  1
 *
 * 　　　　第四次排序：8和9比较，8小于9，不交换位置：3  6  2  8  9  1
 *
 * 　　　　第五次排序：9和1比较：9大于1，交换位置：  3  6  2  8  1  9
 *
 * 　　　　第一趟总共进行了5次比较， 排序结果：      3  6  2  8  1  9
 *
 * ---------------------------------------------------------------------
 *
 * 第二趟排序：
 *
 * 　　　　第一次排序：3和6比较，3小于6，不交换位置：3  6  2  8  1  9
 *
 * 　　　　第二次排序：6和2比较，6大于2，交换位置：  3  2  6  8  1  9
 *
 * 　　　　第三次排序：6和8比较，6大于8，不交换位置：3  2  6  8  1  9
 *
 * 　　　　第四次排序：8和1比较，8大于1，交换位置：  3  2  6  1  8  9
 *
 * 　　　　第二趟总共进行了4次比较， 排序结果：      3  2  6  1  8  9
 *
 * ---------------------------------------------------------------------
 *
 * 第三趟排序：
 *
 * 　　　　第一次排序：3和2比较，3大于2，交换位置：  2  3  6  1  8  9
 *
 * 　　　　第二次排序：3和6比较，3小于6，不交换位置：2  3  6  1  8  9
 *
 * 　　　　第三次排序：6和1比较，6大于1，交换位置：  2  3  1  6  8  9
 *
 * 　　　　第二趟总共进行了3次比较， 排序结果：         2  3  1  6  8  9
 *
 * ---------------------------------------------------------------------
 *
 * 第四趟排序：
 *
 * 　　　　第一次排序：2和3比较，2小于3，不交换位置：2  3  1  6  8  9
 *
 * 　　　　第二次排序：3和1比较，3大于1，交换位置：  2  1  3  6  8  9
 *
 * 　　　　第二趟总共进行了2次比较， 排序结果：        2  1  3  6  8  9
 *
 * ---------------------------------------------------------------------
 *
 * 第五趟排序：
 *
 * 　　　　第一次排序：2和1比较，2大于1，交换位置：  1  2  3  6  8  9
 *
 * 　　　　第二趟总共进行了1次比较， 排序结果：  1  2  3  6  8  9
 *
 * ---------------------------------------------------------------------
 *
 * 最终结果：1  2  3  6  8  9
 *
 * ---------------------------------------------------------------------
 */
public class BubbleSortActivity extends AppCompatActivity {

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

    long time =0;

    private void startSort() {

        result += "排序前数组为：\n";
        for (int num : arr) {
            result = result + num + " ";
        }
        result+="\n\n";

        time=System.currentTimeMillis();
        for(int i=0;i<arr.length;i++){

            for(int j=0;j<arr.length-i-1;j++){

                if(arr[j]>arr[j+1]){
                    //交换值
                    int temp=arr[j];

                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }

                result += "第"+(i+1)+"趟"+(j+1)+"次排序：";
                for (int num : arr) {
                    result = result + num + " ";
                }
                result+="\n";
            }
        }


        result=result+"共耗时："+(System.currentTimeMillis()-time)+"毫秒";


        tvResult.setText(result);
    }

}
