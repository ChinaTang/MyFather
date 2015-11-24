package tang.di.muzhu;

import tang.di.tools.MyFragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.myfather.R;

public class MuZhuDetailActivity extends MyFragmentActivity {

	public static final int REQUEST_CODE = 5;
	Fragment mFragment;
	@Override
	public Fragment getFragment() {
		Intent intent = getIntent();
		mFragment = MuZhuDetailFragment.newInstance(intent.getExtras());
		return mFragment;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_activity);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode != Activity.RESULT_OK) return;

	}

}
