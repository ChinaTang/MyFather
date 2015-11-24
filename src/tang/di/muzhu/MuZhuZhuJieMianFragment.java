package tang.di.muzhu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myfather.R;

import java.util.ArrayList;

import tang.di.database.DataBaseTools;
import tang.di.database.MydatabaseHelp;
import tang.di.database.PublicWorld;
import tang.di.date.AllData;
import tang.di.date.MotherPigDate;

public class MuZhuZhuJieMianFragment extends Fragment implements
		OnClickListener{


	private Button mAdd;
	private ListView listview;
	private callbacks mCallbacks;
	private MyAdapter adapter;
	private ArrayList<MotherPigDate> mList;
	public static final int FRAGMENT_QUEST_DATE = 0;
	private DataBaseTools dataBaseTools;
	private FragmentManager fm;


	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		mCallbacks = (callbacks)activity;
	}
	@Override
	public void onDetach(){
		super.onDetach();
		mCallbacks = null;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mList = AllData.getInstance().getmMotherList();
		fm = getActivity().getSupportFragmentManager();
		dataBaseTools = DataBaseTools.getInstance(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.mu_zhu_zhu_jie_mian, container,
				false);
		mAdd = (Button) v.findViewById(R.id.mu_zhu_zhu_jie_mian_add);
		mAdd.setOnClickListener(this);
		listview = (ListView) v
				.findViewById(R.id.mu_zhu_zhu_jie_mian_list_veiw);
		adapter = new MyAdapter(getActivity());
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				MotherPigDate motherPigDate = mList.get(position);
				jumpFromMp(motherPigDate, true);
			}
		});

		return v;
	}

	@Override
	public void onClick(View v) {
		/**
		 * 插入一条数据
		 */
		MotherPigDate motherPigDate = new MotherPigDate();
			/**Intent intent = new Intent(getActivity(), MuZhuDetailActivity.class);
			 getActivity().startActivity(intent);**/
		jumpFromMp(motherPigDate, false);
	}

	/**
	 * 刷新界面的onActivityResult
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode != Activity.RESULT_OK) return;
		if(requestCode == FRAGMENT_QUEST_DATE){
			adapter.notifyDataSetChanged();
		}
	}

	interface callbacks{
		public void selfNotifity();
	}

	/**
	 * Adapter希望可以做成共通的
	 */

	private class MyAdapter extends ArrayAdapter<MotherPigDate>{

		private Context mContext;

		public MyAdapter(Context context){
			super(context, 0, mList);
			mContext = context.getApplicationContext();
		}

		/**
		 * getView方法
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			if(convertView == null){
				convertView = LayoutInflater.from(mContext).inflate(R.layout.list_view_item, null);
			}
			MotherPigDate data = getItem(position);
			TextView mKind = (TextView)convertView.findViewById(R.id.list_item_kind);
			TextView mAdress = (TextView)convertView.findViewById(R.id.list_item_adress);
			TextView mWant = (TextView)convertView.findViewById(R.id.list_item_think);
			mKind.setText(R.string.kind_big);
			mAdress.setText(data.getAdress());
			/**
			 * 加入显示guiz，显示当前需求数据
			 */
			mWant.setText(data.getmChanzaiDate().toString());
			return convertView;

		}

	}

	public MuZhuZhuJieMianFragment getFragmentInstance(Bundle bundle){
		MuZhuZhuJieMianFragment fragment = new MuZhuZhuJieMianFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	/**
	 * 界面跳转函数
	 */
	private void jumpFromMp(MotherPigDate motherPigDate, boolean flag){
		Intent intent = new Intent(getActivity(), MuZhuDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString(PublicWorld.TABLE2_KEY_GOLD_DATA_COLUM, motherPigDate.getQunID());
		intent.putExtra("FLAG", flag);
		intent.putExtras(bundle);
		getActivity().startActivityForResult(intent, MuZhuZhujieMianActivity.REQUEST_CODE);
		/**MuZhuDetailFragment fragment = MuZhuDetailFragment.newInstance(bundle);
		fragment.setTargetFragment(MuZhuZhuJieMianFragment.this, FRAGMENT_QUEST_DATE);
		fm.beginTransaction()
				.add(R.id.xiaozhu_fragment, fragment)
				.addToBackStack("transaction")
				.commit();**/
	}

	public void notifyChange(){
		adapter.notifyDataSetChanged();
	}

}
