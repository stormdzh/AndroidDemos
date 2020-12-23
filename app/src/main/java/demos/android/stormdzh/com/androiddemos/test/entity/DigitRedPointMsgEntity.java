package demos.android.stormdzh.com.androiddemos.test.entity;

import java.util.List;

/**
 * @Description: 数字消息红点实体类
 * @Author: dzh
 * @CreateDate: 2020-08-25 11:07
 */
public class DigitRedPointMsgEntity {
    public String pid; //数字红点消息的父类id
    public String functionId;  //功能ID 1:A 2:B 3:C
    public int showType;       //展示类型 3数字红点 4小圆点
    public DigitRedPointMsgInfo info;
    //自己封装的数据格式
    public List<DigitRedPointMsgEntity> list;
}
