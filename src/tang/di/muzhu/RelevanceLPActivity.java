package tang.di.muzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.myfather.R;

import tang.di.tools.MyFragmentActivity;

/**
 * Created by tangdi on 2015/11/18.
 */
public class RelevanceLPActivity extends MyFragmentActivity{

    public Fragment getFragment(){

        Intent intent = getIntent();
        Bundle bundle  = intent.getExtras();
        return RelevanceLPFragment.getInstance(bundle);
    }

    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.fragment_activity);
    }

}
