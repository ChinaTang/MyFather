package tang.di.siliao;

import com.example.myfather.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class SiLiaoActivity extends FragmentActivity {

	FragmentManager fm;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_activity);

		fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.xiaozhu_fragment);
		if (fragment == null) {
			fragment = new SiLiaoListFragment();
			fm.beginTransaction().add(R.id.xiaozhu_fragment, fragment).commit();
		}

	}

}
