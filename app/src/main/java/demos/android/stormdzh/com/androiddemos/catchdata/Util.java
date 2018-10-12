package demos.android.stormdzh.com.androiddemos.catchdata;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Created by a111 on 2018/10/9.
 */

public class Util {

    private Util() {
        String path = Environment.getExternalStorageDirectory().toString() + "/words.xls";///storage/sdcard0
        File file = new File(path);
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            workbook = Workbook.createWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = workbook.createSheet("单词数据", 0);

        //4：设置titles
        String[] titles = {"单词", "信息"};
        //5:单元格
        Label label = null;
        //6:给第一行设置列名
        for (int i = 0; i < titles.length; i++) {
            //x,y,第一行的列名
            label = new Label(i, 0, titles[i]);
            //7：添加单元格
            try {
                sheet.addCell(label);
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }

    private static Util instence = new Util();

    public static Util getInstance() {

        return instence;
    }

    private String path;

    private WritableWorkbook workbook;
    private WritableSheet sheet;

    public void savaData2Excel(int index, String name, String result) {
        int i = index + 1;

        path = Environment.getExternalStorageDirectory().toString() + "/words.xls";///storage/sdcard0
        try {
            addExcel(i, name, result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    public void addExcel(int index, String name, String result) throws IOException, RowsExceededException, WriteException {

//        File file = new File(path);
//        if (!file.exists())
//            file.createNewFile();

        //2:创建工作簿
//        WritableWorkbook workbook = Workbook.createWorkbook(file);

//        WritableWorkbook workbook = null;
//        try {
//            workbook = Workbook.getWorkbook(file);
//        } catch (BiffException e) {
//            e.printStackTrace();
//        }
//
//        if(workbook==null){
//            workbook = Workbook.createWorkbook(file);
//        }

        if (workbook == null) return;

        //3:创建sheet,设置第二三四..个sheet，依次类推即可
//        WritableSheet sheet = workbook.createSheet("单词数据", 0);
        if (sheet == null) return;
        //4：设置titles
//        String[] titles = {"单词", "信息"};
//        //5:单元格
//        Label label = null;
//        //6:给第一行设置列名
//        for (int i = 0; i < titles.length; i++) {
//            //x,y,第一行的列名
//            label = new Label(i, 0, titles[i]);
//            //7：添加单元格
//            sheet.addCell(label);
//        }


        //8：模拟数据库导入数据
//        for (int i = 1; i < 10; i++) {
//            //添加编号，第二行第一列
//            label = new Label(0, i, i + "");
//            sheet.addCell(label);
//
//            //添加账号
//            label = new Label(1, i, "10010" + i);
//            sheet.addCell(label);
//
//            //添加密码
//            label = new Label(2, i, "123456");
//            sheet.addCell(label);
//        }
        Log.i("test", "index==>" + index);
        //添加单词，第二行第一列
        Label label = new Label(0, index, "name" + index);
        sheet.addCell(label);

        //添加信息
        Label label2 = new Label(1, index, "result" + index);
        sheet.addCell(label2);

        //写入数据，一定记得写入数据，不然你都开始怀疑世界了，excel里面啥都没有
        workbook.write();
        //最后一步，关闭工作簿
        if (index >= 5)
            workbook.close();
    }
}
