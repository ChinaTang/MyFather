package tang.di.muzhu;

import tang.di.database.DataBaseTools;
import tang.di.database.PublicWorld;
import tang.di.date.LittlePigDate;
import tang.di.date.MotherPigDate;
import tang.di.tools.DatePickerFragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfather.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.zip.Inflater;

public class MuZhuDetailFragment extends Fragment implements OnClickListener {

	private static final String TAG = "MuZhuDetailFragment";

	private TextView Adress;

	private Button mAddXiaoZhuDetail;

	private Button mMotherPigFaQing, mMotherPigShouJing, mMotherPigHaveBaby, mSave;

	private ListView lpListView;

	private ArrayList<LittlePigDate> littlePigDates;

	private Myadapter adapter;

	public static final String UUID = "UUID";

	public static final String DIALOG_DATE = "DIALOG_DATE";

	public static final String BPToLpFlag = "bptolpflag";

	public static final String _ID = "_id";

	public static final String LPADRESS = "Adress";

	private static final int REQUEST_DATE_1 = 0;

	private static final int REQUEST_DATE_2 = 1;

	private static final int REQUEST_DATE_3 = 2;

	private MotherPigDate motherPigDate ;

	private DataBaseTools dataBaseTools;

	private Bundle bundle;


	public static MuZhuDetailFragment newInstance(Bundle bundle){
		MuZhuDetailFragment fragment = new MuZhuDetailFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dataBaseTools = DataBaseTools.getInstance(getActivity());
		lpData();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.mu_zhu_detail_fragment, container,
				false);
		init(v);
		mAddXiaoZhuDetail.setOnClickListener(this);
		mMotherPigFaQing.setOnClickListener(this);
		mMotherPigHaveBaby.setOnClickListener(this);
		mMotherPigShouJing.setOnClickListener(this);
		mSave.setOnClickListener(this);
		Adress.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.xiao_zhu_detail_add:
			JumpView(true, 0);

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
				saveButton();
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
		bundle = getArguments();
		mAddXiaoZhuDetail = (Button) v.findViewById(R.id.xiao_zhu_detail_add);
		mMotherPigFaQing = (Button)v.findViewById(R.id.mu_zhu_he_shi_fa_qing);
		mMotherPigHaveBaby = (Button)v.findViewById(R.id.mu_zhu_he_shi_chan_zai);
		mMotherPigShouJing = (Button)v.findViewById(R.id.mu_zhu_he_shi_shou_jing);
		lpListView = (ListView)v.findViewById(R.id.xiao_zhu_listview);
		mSave = (Button)v.findViewById(R.id.save_muhzu);
		Adress = (TextView)v.findViewById(R.id.mu_zhu_detail);

		if(bundle.getBoolean("FLAG")) {
			motherPigDate = dataBaseTools.getMotherPigDataFromDB(
					bundle.getString(PublicWorld.TABLE2_KEY_GOLD_DATA_COLUM)).get(0);
			mMotherPigFaQing.setText(DataBaseTools.DateToString(motherPigDate.getmFaQingdate()));
			mMotherPigHaveBaby.setText(DataBaseTools.DateToString(motherPigDate.getmChanzaiDate()));
			mMotherPigShouJing.setText(DataBaseTools.DateToString(motherPigDate.getmShoujingDate()));
			Adress.setText(motherPigDate.getAdress());
			mSave.setText("修改完成");
		}else{
			motherPigDate = new MotherPigDate();
		}
		lpListView.setAdapter(adapter);
		lpListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//JumpView(false, littlePigDates.get(position).get_id());
				Intent intent = new Intent(getActivity(),
						RelevanceLPActivity.class);
				Bundle bundle = new Bundle();

					bundle.putInt(_ID, littlePigDates.get(position).get_id());

				bundle.putString(UUID, littlePigDates.get(position).getQUUID());
				bundle.putBoolean(BPToLpFlag, false);
				Log.d(TAG, "=====" + String.valueOf(littlePigDates.get(position).get_id()) + "========");
				Log.d(TAG, "=====" + littlePigDates.get(position).getQUUID() + "=========");

				intent.putExtras(bundle);
				getActivity().startActivityForResult(intent, MuZhuDetailActivity.REQUEST_CODE);
			}
		});

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
			motherPigDate.setmFaQingdate(date);
			mMotherPigFaQing.setText(DataBaseTools.DateToString(date));
		}
		if(resquestCode == REQUEST_DATE_2){
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.DATE);
			motherPigDate.setmChanzaiDate(date);
			mMotherPigHaveBaby.setText(DataBaseTools.DateToString(date));
		}
		if(resquestCode == REQUEST_DATE_3){
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.DATE);
			motherPigDate.setmShoujingDate(date);
			mMotherPigShouJing.setText(DataBaseTools.DateToString(date));
		}
	}


	public void saveButton(){

		if(bundle.getBoolean("FLAG")){
			dataBaseTools.upContentValue(motherPigDate);
			Toast.makeText(getActivity(), "修改数据库完成", Toast.LENGTH_SHORT).show();
		}else{
			dataBaseTools.insertDateToSQL(motherPigDate);
			Log.d(TAG, "tangdi======>是否运行到这一步");
			Toast.makeText(getActivity(), "保存数据库完成", Toast.LENGTH_SHORT).show();
		}
		getActivity().finish();

	}

	private void saveFun(){
		/**
		 * 开启数据库写入数据
		 */
	}

	/**
	 * 界面跳转
	 */
	private void JumpView(Boolean flag, int postion){

		/**
		 * flag为true时添加新的明细
		 * 为false时修改
		 */

		Log.d(TAG, "==========postion " + String.valueOf(postion) + " =============");
		Intent intent = new Intent(getActivity(),
				RelevanceLPActivity.class);
		Bundle bundle = new Bundle();
		if(postion != 0){
			bundle.putInt(_ID, postion);
		}
		bundle.putString(UUID, motherPigDate.getQunID());
		bundle.putBoolean(BPToLpFlag, flag);
		bundle.putString(LPADRESS, motherPigDate.getAdress());

		intent.putExtras(bundle);
		getActivity().startActivityForResult(intent, MuZhuDetailActivity.REQUEST_CODE);

	}

	/**
	 * listview刷新
	 */
	public void notifity(){

	}

	/**
	 * 初始化当前lp的数据
	 */
	public void lpData(){
		littlePigDates = dataBaseTools.getLittlePigDataFormDB(motherPigDate);
		Log.e(TAG, littlePigDates.toString());
		adapter = new Myadapter(getActivity());
	}




	private class Myadapter extends ArrayAdapter<LittlePigDate>{

		private Context mContext;

		public Myadapter(Context context){
			super(context, 0, littlePigDates);
			mContext = context.getApplicationContext();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent){

			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_view_item, null);
			LittlePigDate data = getItem(position);
			TextView mKind = (TextView)convertView.findViewById(R.id.list_item_kind);
			TextView mAdress = (TextView)convertView.findViewById(R.id.list_item_adress);
			TextView mWant = (TextView)convertView.findViewById(R.id.list_item_think);
			mKind.setText(R.string.kind_little);
			mAdress.setText(String.valueOf(data.getmPigs()));
			/**
			 * 加入显示guiz，显示当前需求数据
			 */
			mWant.setText(DataBaseTools.DateToString(data.getmLittlePigBrithDay()));
			Log.d(TAG, "getView()" + littlePigDates.get(position).ismIsNoMilk());
			if(littlePigDates.get(position).ismIsNoMilk()){
				convertView.setBackgroundColor(getResources().getColor(R.color.red));
				Log.d(TAG, "====" + "红色的背景色" + "====");
				return convertView;
			}
			return convertView;

		}

		@Override
		public boolean isEnabled(int position){
			Log.d(TAG, "======" + littlePigDates.get(position).ismIsNoMilk());
			if(littlePigDates.get(position).ismIsNoMilk()){
				Log.d(TAG, "======" + "不允许点击的项" + "=====");
				return false;
			}
			return true;
		}

	}


}
