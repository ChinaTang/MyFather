package tang.di.father.app;

import com.example.myfather.R;

import tang.di.database.DataBaseTools;
import tang.di.database.MydatabaseHelp;
import tang.di.database.PublicWorld;
import tang.di.date.AllData;
import tang.di.date.MotherPigDate;
import tang.di.muzhu.MuZhuZhujieMianActivity;
import tang.di.siliao.SiLiaoActivity;
import tang.di.xiaozhu.XiaoZhuListActivity;
import tang.di.zhangmuzonglan.ZhangMuActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button mXiaoZhu, mSiLiao, mMuZhu, mZhangMu;

	SQLiteDatabase db;

	private DataBaseTools dataBaseTools;

	private AllData allData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jia_xiang);
		init();
		/**
		 * 进入主界面初始化数据
		 */
		instanceDate();
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

	/**
	 * 初始化数据
	 */

	public void instanceDate(){
		dataBaseTools = DataBaseTools.getInstance(MainActivity.this);
		allData = AllData.getInstance();
		/**
		 * 查询lp整张表
		 */
		allData.setmLittlePigList(dataBaseTools.getLittlePigDataFormDB(null));
		allData.setmMotherList(dataBaseTools.getMotherPigDataFromDB(null));

		for(MotherPigDate item :allData.getmMotherList()){
			item.setmList(dataBaseTools.getLittlePigDataFormDB(item));
		}

	}
}
