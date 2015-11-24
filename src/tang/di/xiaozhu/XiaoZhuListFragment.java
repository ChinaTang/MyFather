package tang.di.xiaozhu;

import tang.di.database.DataBaseTools;
import tang.di.date.LittlePigDate;
import tang.di.tools.MyAdapter;
import tang.di.tools.XiaoZhuAdapter;
import tang.di.xiaozhu.data.XiaoZhuData;
import tang.di.xiaozhu.data.XiaoZhuDataList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myfather.R;

import java.util.ArrayList;

public class XiaoZhuListFragment extends Fragment implements OnClickListener,
		OnItemClickListener, OnItemLongClickListener {

	private ListView listview;
	private MyAdapter mAdapter;
	private LittlePigDate mData;
	private ArrayList<LittlePigDate> littlePigDates;

	public static final String date = "DATE";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		littlePigDates = DataBaseTools.getInstance(getActivity()).selsetT4();
		mAdapter = new MyAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.detail_little_pig_zhu_jiemian,
				container, false);
		listview = (ListView) root.findViewById(R.id.xiao_zhu_listview);
		listview.setAdapter(mAdapter);
		listview.setOnItemClickListener(this);
		listview.setOnItemLongClickListener(this);
		return root;

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		/**
		 * 画面跳转
		 */
		Intent intent = new Intent(getActivity(), XiaoZhuDetialActivity.class);
		LittlePigDate littlePigDate = littlePigDates.get(position);
		intent.putExtra(date, littlePigDate);
		getActivity().startActivityForResult(intent, XiaoZhuListActivity.requestCode);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		return false;
	}

	private class MyAdapter extends ArrayAdapter<LittlePigDate>{
		private Context mcontext;
		MyAdapter(Context context){
			super(context, 0, littlePigDates);
			mcontext = context.getApplicationContext();
		}

		@Override
		public View getView (int position, View convertView, ViewGroup parent){
			convertView = LayoutInflater.from(mcontext).inflate(R.layout.no_milk_lp, null);
			TextView number = (TextView)convertView.findViewById(R.id.no_milk_lp_number);
			TextView adress = (TextView)convertView.findViewById(R.id.no_milk_lp_adress);
			TextView NoMilkday = (TextView)convertView.findViewById(R.id.no_milk_lp_nomilk_day);
			number.setText(String.valueOf(littlePigDates.get(position).getmPigs()));
			adress.setText(littlePigDates.get(position).getmAdress());
			NoMilkday.setText(DataBaseTools.DateToString(littlePigDates.get(position).getmLittlePigMilk()));
			if(littlePigDates.get(position).getIsSell()) {
				convertView.setMinimumWidth(0);
				return convertView;
			}
			return convertView;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int respondCode, Intent date){

	}

}
