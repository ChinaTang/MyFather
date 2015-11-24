package tang.di.siliao;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import tang.di.tools.MyFragmentActivity;

/**
 * Created by tangdi on 2015/11/23.
 */
public class FeedDetailActivity extends MyFragmentActivity{

    @Override
    public Fragment getFragment(){
        Bundle bundle = getIntent().getExtras();
        return FeedDetailFragment.getInstance(bundle);
    }



}
