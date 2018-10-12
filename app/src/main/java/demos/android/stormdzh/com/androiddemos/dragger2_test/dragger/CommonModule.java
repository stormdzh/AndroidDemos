package demos.android.stormdzh.com.androiddemos.dragger2_test.dragger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by a111 on 2018/10/12.
 */

@Module
public class CommonModule {
    private ICommonView iView;
    public CommonModule(ICommonView iView){
        this.iView = iView;
    }


    @Provides
    @ActivityScope
    public ICommonView provideIcommonView(){
        return this.iView;
    }
}
