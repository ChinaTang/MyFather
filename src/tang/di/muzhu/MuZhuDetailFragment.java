package tang.di.muzhu;

import tang.di.date.MotherPigDate;
import tang.di.tools.DatePickerFragment;
import tang.di.xiaozhu.XiaoZhuDetialActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfather.R;

import java.util.Date;

public class MuZhuDetailFragment extends Fragment implements OnClickListener {

	private TextView Adress;

	private Button mAddXiaoZhuDetail;

	private Button mMotherPigFaQing, mMotherPigShouJing, mMotherPigHaveBaby, mSave;

	public static final String ADRESS = "Adress";

	public static final String DIALOG_DATE = "DIALOG_DATE";

	private static final int REQUEST_DATE_1 = 0;

	private static final int REQUEST_DATE_2 = 1;

	private static final int REQUEST_DATE_3 = 2;

	private MotherPigDate mMotherPigDate;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMotherPigDate = new MotherPigDate();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.mu_zhu_detail_fragment, container,
				false);
		init(v);

		mAddXiaoZhuDetail.setOnClickListener(this);

		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.xiao_zhu_detail_add:
			Intent intent = new Intent(getActivity(),
					XiaoZhuDetialActivity.class);
			intent.putExtra(ADRESS, Adress.getText());
			getActivity().startActivity(intent);

			break;
			case R.id.mu_zhu_he_shi_fa_qing:

				showDialog(REQUEST_DATE_1);
				break;
			case R.id.mu_zhu_he_shi_chan_zai:
				showDialog(REQUEST_DATE_2);
				break;
			case R.id.mu_zhu_he_shi_shou_jing:
				showDialog(REQUEST_DATE_3);
				break;
			case R.id.save_muhzu:
				break;
			case R.id.mu_zhu_detail :
				/**
				 * 开启选择位置Dialog
				 */
				break;

		default:
			break;
		}
	}

	private void init(View v){
		mAddXiaoZhuDetail = (Button) v.findViewById(R.id.xiao_zhu_detail_add);
		mMotherPigFaQing = (Button)v.findViewById(R.id.mu_zhu_he_shi_fa_qing);
		mMotherPigHaveBaby = (Button)v.findViewById(R.id.mu_zhu_he_shi_chan_zai);
		mMotherPigShouJing = (Button)v.findViewById(R.id.mu_zhu_he_shi_shou_jing);
		mSave = (Button)v.findViewById(R.id.save_muhzu);
		Adress = (TextView)v.findViewById(R.id.mu_zhu_detail);
	}

	/**
	 * 弹出日期选择对话框
	 */
	public void showDialog(int REQUEST_DATE){
		Date date = new Date();
		FragmentManager fm = getActivity().getSupportFragmentManager();
		DatePickerFragment dialog = DatePickerFragment.getInstance(date);
		dialog.setTargetFragment(MuZhuDetailFragment.this, REQUEST_DATE);
		dialog.show(fm, DIALOG_DATE);


	}

	@Override
	public void onActivityResult(int resquestCode, int resultCode, Intent data){
		if(resultCode != Activity.RESULT_OK){
			return;
		}
		if(resquestCode == REQUEST_DATE_1){
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.DATE);
			mMotherPigDate.setmFaQingdate(date);
			mMotherPigFaQing.setText(date.toString());
		}
		if(resquestCode == REQUEST_DATE_2){
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.DATE);
			mMotherPigDate.setmChanzaiDate(date);
			mMotherPigHaveBaby.setText(date.toString());
		}
		if(resquestCode == REQUEST_DATE_3){
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.DATE);
			mMotherPigDate.setmShoujingDate(date);
			mMotherPigShouJing.setText(date.toString());
		}
	}

	private void saveFun(){
		/**
		 * 开启数据库写入数据
		 */
	}

}
