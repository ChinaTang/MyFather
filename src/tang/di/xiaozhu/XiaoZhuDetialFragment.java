package tang.di.xiaozhu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfather.R;

import java.util.Date;

import tang.di.database.DataBaseTools;
import tang.di.date.LittlePigDate;
import tang.di.date.ZhangMuDate;
import tang.di.tools.DatePickerFragment;

public class XiaoZhuDetialFragment extends Fragment implements OnClickListener {

	private TextView Adress, IsHeBing;

	private EditText PigNumber, SellNumber, SellPrices;

	private Button btNoMilkDate, btCastratDate, btSellDate, Save;

	private LittlePigDate littlePigDate;

	public static final String DATA = "data";

	private static final String DIALOG = "DATE_DIALOG";

	private static final int QUESTION1 = 1;

	private static final int QUESTION2 = 2;

	private static final int QUESTION3 = 3;

	private DataBaseTools dataBaseTools;

	private ZhangMuDate zhangMuDate;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dataBaseTools = DataBaseTools.getInstance(getActivity());
		littlePigDate = (LittlePigDate)getArguments().getParcelable(DATA);
		if(littlePigDate == null){
			littlePigDate = new LittlePigDate();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.detal_little_pig, container,
				false);
		Adress = (TextView)root.findViewById(R.id.choose_mother_pig);
		IsHeBing = (TextView)root.findViewById(R.id.he_bing);
		PigNumber = (EditText)root.findViewById(R.id.xiao_zhu_zhi_shu);
		SellNumber = (EditText)root.findViewById(R.id.xiao_zhu_mai_chu_zhi_shu);
		SellPrices = (EditText)root.findViewById(R.id.xiao_zhu_mai_chu_jia_ge);
		btNoMilkDate = (Button)root.findViewById(R.id.detail_xiao_zhu_duan_nai_ri_qi);
		btCastratDate = (Button)root.findViewById(R.id.xiao_zhu_yan_ge_ri_qi);
		btSellDate = (Button)root.findViewById(R.id.xiao_zhu_mai_chu_ri_qi);
		Save = (Button)root.findViewById(R.id.save);
		initView();
		Adress.setOnClickListener(this);
		btNoMilkDate.setOnClickListener(this);
		btCastratDate.setOnClickListener(this);
		btSellDate.setOnClickListener(this);
		Save.setOnClickListener(this);
		return root;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.choose_mother_pig:
				break;
			case R.id.detail_xiao_zhu_duan_nai_ri_qi:
				showDialog(QUESTION1, littlePigDate.getmLittlePigMilk());
				break;
			case R.id.xiao_zhu_yan_ge_ri_qi:
				showDialog(QUESTION2, littlePigDate.getmLittleCutDay());
				break;
			case R.id.xiao_zhu_mai_chu_ri_qi:
				showDialog(QUESTION3, littlePigDate.getmSellLittlePigDay());
				break;
			case R.id.save:
				btSave();
				break;
			default:
				break;
		}

	}

	public static XiaoZhuDetialFragment getInstance(LittlePigDate littlePigDate){
		XiaoZhuDetialFragment fragment = new XiaoZhuDetialFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable(DATA, littlePigDate);
		fragment.setArguments(bundle);
		return fragment;
	}

	private void initView(){
		Adress.setText(littlePigDate.getmAdress());
		if(littlePigDate.getISADD()){
			IsHeBing.setText(R.string.he_bing);
		}else{
			IsHeBing.setText(R.string.no_he_bing);
		}
		PigNumber.setText(String.valueOf(littlePigDate.getmPigs()));
		SellNumber.setText(String.valueOf(littlePigDate.getmSellLittlePig()));
		btNoMilkDate.setText(DataBaseTools.DateToString(littlePigDate.getmLittlePigMilk()));
		btCastratDate.setText(DataBaseTools.DateToString(littlePigDate.getmLittleCutDay()));
		btSellDate.setText(DataBaseTools.DateToString(littlePigDate.getmSellLittlePigDay()));
		SellPrices.setText(String.valueOf(littlePigDate.getmSellLittlePigPrice()));
	}

	private void showDialog(int QuestionCode, Date date){

		FragmentManager fm = getActivity().getSupportFragmentManager();
		DatePickerFragment dialog = DatePickerFragment.getInstance(date);
		dialog.setTargetFragment(XiaoZhuDetialFragment.this, QuestionCode);
		dialog.show(fm, DIALOG);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode != Activity.RESULT_OK) return;
		if(requestCode == QUESTION1){
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.DATE);
			littlePigDate.setmLittlePigMilk(date);
			btNoMilkDate.setText(DataBaseTools.DateToString(date));
		}
		if(requestCode == QUESTION2){
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.DATE);
			littlePigDate.setmLittleCutDay(date);
			btCastratDate.setText(DataBaseTools.DateToString(date));
		}
		if(requestCode == QUESTION3){
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.DATE);
			littlePigDate.setmSellLittlePigDay(date);
			btSellDate.setText(DataBaseTools.DateToString(date));
		}
	}

	private void btSave(){

		littlePigDate.setmPigs(Integer.valueOf(PigNumber.getText().toString()));
		littlePigDate.setmSellLittlePig(Integer.valueOf(SellNumber.getText().toString()));
		littlePigDate.setmSellLittlePigPrice(Float.valueOf(SellPrices.getText().toString()));
		dataBaseTools.UpdataT4(littlePigDate);

		if(littlePigDate.getmSellLittlePig() != 0 && littlePigDate.getmSellLittlePigPrice() != 0){
			/**
			 * 插入table6
			 */
			zhangMuDate = new ZhangMuDate();
			zhangMuDate.setDetail(littlePigDate.getmAdress() + "/" + littlePigDate.getmSellLittlePig());
			zhangMuDate.setDate(littlePigDate.getmSellLittlePigDay());
			zhangMuDate.setMoney(littlePigDate.getmSellLittlePigPrice());
			dataBaseTools.insert(zhangMuDate);
			Log.e("tangdi" , "插入table6");

			/**
			 * 全部卖出不允许显示
			 */
			if(littlePigDate.getmSellLittlePig() == littlePigDate.getmPigs()){
				littlePigDate.setIsSell(true);
			}
		}

		getActivity().finish();
	}

}
