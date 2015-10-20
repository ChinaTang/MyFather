package tang.di.father.app;

import com.example.myfather.R;

import tang.di.muzhu.MuZhuZhujieMianActivity;
import tang.di.siliao.SiLiaoActivity;
import tang.di.xiaozhu.XiaoZhuListActivity;
import tang.di.zhangmuzonglan.ZhangMuActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button mXiaoZhu, mSiLiao, mMuZhu, mZhangMu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		mXiaoZhu.setOnClickListener(this);
		mSiLiao.setOnClickListener(this);
		mMuZhu.setOnClickListener(this);
		mZhangMu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.xiao_zhu:
			jumpActivity(XiaoZhuListActivity.class);
			break;

		case R.id.si_liao:
			jumpActivity(SiLiaoActivity.class);
			break;

		case R.id.mu_zhu:
			jumpActivity(MuZhuZhujieMianActivity.class);
			break;

		case R.id.zhuang_wu:
			jumpActivity(ZhangMuActivity.class);
			break;

		default:
			break;
		}

	}

	private void init() {
		mXiaoZhu = (Button) findViewById(R.id.xiao_zhu);
		mSiLiao = (Button) findViewById(R.id.si_liao);
		mMuZhu = (Button) findViewById(R.id.mu_zhu);
		mZhangMu = (Button) findViewById(R.id.zhuang_wu);
	}

	private void jumpActivity(Class<?> className) {
		Intent intent = new Intent(MainActivity.this, className);
		startActivity(intent);
	}
}
