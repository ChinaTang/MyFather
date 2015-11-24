package tang.di.xiaozhu;

import tang.di.tools.MyFragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.myfather.R;

public class XiaoZhuListActivity extends MyFragmentActivity {

	public static final int requestCode = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_activity);
	}

	@Override
	public Fragment getFragment() {
		Fragment fragment = new XiaoZhuListFragment();
		return fragment;
	}

	@Override
	public void onActivityResult(int requestCode, int respondCode, Intent date){

	}

}
