package tang.di.muzhu;

import tang.di.tools.MyFragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.myfather.R;

public class MuZhuZhujieMianActivity extends MyFragmentActivity implements
		MuZhuZhuJieMianFragment.callbacks{

	private static final String TAG = "MuzhuZhujieMianActivity";

	private FragmentManager fm = getSupportFragmentManager();
	private MuZhuZhuJieMianFragment mFragment;
	private MuZhuDetailFragment Mufragment;
	public static final int REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		setContentView(R.layout.fragment_activity);
		Bundle bundle = new Bundle();
		Mufragment = MuZhuDetailFragment.newInstance(bundle);

	}

	@Override
	public Fragment getFragment() {
		mFragment = new MuZhuZhuJieMianFragment();
		return mFragment;
	}

	@Override
	public void selfNotifity(){
		/**
		 * 数据更新回调方法
		 */
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		Log.d(TAG, String.valueOf(requestCode) + "<============>" + String.valueOf(requestCode));
		if(resultCode != Activity.RESULT_OK) return;
		if(requestCode == REQUEST_CODE){
			mFragment.notifyChange();
		}
	}


}
