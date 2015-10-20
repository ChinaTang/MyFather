package tang.di.tools;

import tang.di.muzhu.MuZhuZhuJieMianFragment;

import com.example.myfather.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class MyFragmentActivity extends FragmentActivity {

	public abstract Fragment getFragment();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		FragmentManager fm = getSupportFragmentManager();

		setContentView(R.layout.fragment_activity);
		Fragment mfragment = fm.findFragmentById(R.id.xiaozhu_fragment);
		if (mfragment == null) {
			mfragment = new MuZhuZhuJieMianFragment();
			fm.beginTransaction().add(R.id.xiaozhu_fragment, getFragment())
					.commit();
		}
	}

}
