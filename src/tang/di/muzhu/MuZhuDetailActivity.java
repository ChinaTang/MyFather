package tang.di.muzhu;

import tang.di.tools.MyFragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.myfather.R;

public class MuZhuDetailActivity extends MyFragmentActivity {

	@Override
	public Fragment getFragment() {
		Fragment mFragment = new MuZhuDetailFragment();
		return mFragment;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_activity);

	}

}
