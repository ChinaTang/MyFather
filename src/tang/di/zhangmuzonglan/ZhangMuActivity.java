package tang.di.zhangmuzonglan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import tang.di.tools.MyFragmentActivity;

public class ZhangMuActivity extends MyFragmentActivity {

    @Override
    public Fragment getFragment(){
        ZhangMuFragment fragmentActivity = new ZhangMuFragment();
        return fragmentActivity;
    }

}
