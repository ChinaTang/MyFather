package tang.di.muzhu;

import tang.di.tools.MyFragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.myfather.R;

public class MuZhuZhujieMianActivity extends MyFragmentActivity {

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		setContentView(R.layout.fragment_activity);

	}

	@Override
	public Fragment getFragment() {
		Fragment mFragment = new MuZhuZhuJieMianFragment();
		return mFragment;
	}

}
