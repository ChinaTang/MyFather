package tang.di.siliao;

import com.example.myfather.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import tang.di.tools.MyFragmentActivity;

public class SiLiaoActivity extends MyFragmentActivity {

	FragmentManager fm;

	public static final int QUESTIONCODE = 1;

	@Override
	public Fragment getFragment(){
		SiLiaoListFragment fragment;
		return fragment = new SiLiaoListFragment();
	}


}
