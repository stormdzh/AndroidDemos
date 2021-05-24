package demos.android.stormdzh.com.androiddemos.jobservice;

import android.util.Log;

/**
 * @Description: 描述   加载 验证 准备 解析 初始化 使用 卸载
 *
 *   https://cloud.tencent.com/developer/article/1358290
 * @Author: dzh
 * @CreateDate: 2021/5/24 5:36 PM
 */
class TestLoadStep {

    static {
        Log.i("dzh", "static log");
    }

    {
        Log.i("dzh", "no-static log");
    }

    public TestLoadStep() {
        Log.i("dzh", "construct log");
    }
}
