package demos.android.stormdzh.com.androiddemos.test.entity;

/**
 * @Description: 数字红点
 * @Author: dzh
 * @CreateDate: 2020-08-25 17:40
 */
public class DigitRedPointMsgInfo {

    public int totalUnRead;   // 聚合节点的是子节点总数，非聚合节点和unRead相等
    public int unRead;   //未读消息数
    public boolean isShow; // 只有自己组装的聚合节点有，用户控制该该节点是否展示
    public String time;  //时间戳
}
