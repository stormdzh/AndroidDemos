package demos.android.stormdzh.com.androiddemos.excel;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Created by a111 on 2018/9/29.
 */

public class ExcelUtil {


    public static void addExcel(String path) throws IOException, RowsExceededException, WriteException {

//        File file = new File("test.xls");
        File file = new File(path);
        if (!file.exists())
            file.createNewFile();

        //2:创建工作簿
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        //3:创建sheet,设置第二三四..个sheet，依次类推即可
        WritableSheet sheet = workbook.createSheet("测试excel表名称", 0);
        //4：设置titles
        String[] titles = {"编号", "账号", "密码"};
        //5:单元格
        Label label = null;
        //6:给第一行设置列名
        for (int i = 0; i < titles.length; i++) {
            //x,y,第一行的列名
            label = new Label(i, 0, titles[i]);
            //7：添加单元格
            sheet.addCell(label);
        }
        //8：模拟数据库导入数据
        for (int i = 1; i < 10; i++) {
            //添加编号，第二行第一列
            label = new Label(0, i, i + "");
            sheet.addCell(label);

            //添加账号
            label = new Label(1, i, "10010" + i);
            sheet.addCell(label);

            //添加密码
            label = new Label(2, i, "123456");
            sheet.addCell(label);
        }
        //写入数据，一定记得写入数据，不然你都开始怀疑世界了，excel里面啥都没有
        workbook.write();
        //最后一步，关闭工作簿
        workbook.close();
    }

    public static String readExcel(String path) throws Exception {
        //1:创建workbook
//        Workbook workbook = Workbook.getWorkbook(new File("test.xls"));
        Workbook workbook = Workbook.getWorkbook(new File(path));
        //2:获取第一个工作表sheet
        Sheet sheet = workbook.getSheet(0);
        //3:获取数据
        System.out.println("行：" + sheet.getRows());
        System.out.println("列：" + sheet.getColumns());
        String result = "";
        for (int i = 0; i < sheet.getRows(); i++) {
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                System.out.print(cell.getContents() + " ");
                result += (cell.getContents() + " ");
            }
            result += "\n";
            System.out.println();
        }

        //最后一步：关闭资源
        workbook.close();

        return result;
    }

    public static void printExcel(String path) throws Exception {
        //1:创建workbook
//        Workbook workbook = Workbook.getWorkbook(new File("test.xls"));
        Workbook workbook = Workbook.getWorkbook(new File(path));
        //2:获取第一个工作表sheet
        Sheet sheet = workbook.getSheet(0);
        //3:获取数据
        System.out.println("行：" + sheet.getRows());
        System.out.println("列：" + sheet.getColumns());
        for (int i = 0; i < sheet.getRows(); i++) {
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                System.out.print(cell.getContents() + " ");
            }
            System.out.println();
        }

        //最后一步：关闭资源
        workbook.close();
    }


}
