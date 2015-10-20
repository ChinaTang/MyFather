package tang.di.xiaozhu;

import tang.di.muzhu.MuZhuDetailFragment;
import tang.di.tools.MyFragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.myfather.R;

public class XiaoZhuDetialActivity extends MyFragmentActivity {

	@Override
	public Fragment getFragment() {
		Intent intent = getIntent();

		Fragment fragment =
				XiaoZhuDetialFragment.getInstance(intent.getStringExtra(MuZhuDetailFragment.ADRESS));
		return fragment;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_activity);

	}

}
