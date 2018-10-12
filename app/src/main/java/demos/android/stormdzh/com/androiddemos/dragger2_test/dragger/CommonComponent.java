package demos.android.stormdzh.com.androiddemos.dragger2_test.dragger;

import dagger.Component;
import demos.android.stormdzh.com.androiddemos.dragger2_test.Dragger2TestActivity;

/**
 * Created by a111 on 2018/10/12.
 */

@ActivityScope
@Component(modules = CommonModule.class)
public interface CommonComponent {
    void inject(Dragger2TestActivity activity);
}
