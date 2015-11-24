package tang.di.siliao;

import android.app.Activity;
import android.content.ContentValues;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myfather.R;

import java.util.ArrayList;

import tang.di.database.DataBaseTools;
import tang.di.date.FeedData;
import tang.di.tools.MyAdapter;

public class SiLiaoListFragment extends Fragment implements OnClickListener,
		OnItemClickListener {

	private Button mAddSiLiaoZhangMu;
	private ListView mList;
	private ArrayList<FeedData> feedDatas;
	private DataBaseTools dataBaseTools;
	private MyAdapter adapter;
	public static final String DATE_FEED = "DATE_FEED";
	public static final String DATE_FLAG = "FLAG";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dataBaseTools = DataBaseTools.getInstance(getActivity());
		feedDatas = dataBaseTools.select();
		adapter = new MyAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.detai_si_liao, container, false);
		mAddSiLiaoZhangMu = (Button) v.findViewById(R.id.add_detail_siliao);
		mList = (ListView) v.findViewById(R.id.si_liao);
		mAddSiLiaoZhangMu.setOnClickListener(this);
		mList.setAdapter(adapter);
		mList.setOnItemClickListener(this);
		
		return v;

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getActivity(), FeedDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable(DATE_FEED, feedDatas.get(position));
		bundle.putBoolean(DATE_FLAG, true);
		intent.putExtras(bundle);
		getActivity().startActivityForResult(intent, SiLiaoActivity.QUESTIONCODE);

	}

	@Override
	public void onClick(View v) {
		/**
		 * 界面跳转
		 */
		Intent intent = new Intent(getActivity(), FeedDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putBoolean(DATE_FLAG, false);
		intent.putExtras(bundle);
		getActivity().startActivityForResult(intent, SiLiaoActivity.QUESTIONCODE);


	}

	private class MyAdapter extends ArrayAdapter<FeedData>{

		private Context context;
		public MyAdapter(Context context){
			super(context, 0, feedDatas);
			this.context = context;
		}

		@Override
		public View getView(int postion, View convertView, ViewGroup parent){

			convertView = LayoutInflater.from(context).inflate(R.layout.list_view_item, null);
			TextView mKind = (TextView)convertView.findViewById(R.id.list_item_kind);
			TextView mAdress = (TextView)convertView.findViewById(R.id.list_item_adress);
			TextView mWant = (TextView)convertView.findViewById(R.id.list_item_think);
			mKind.setText("siliao");
			mAdress.setText(DataBaseTools.DateToString(feedDatas.get(postion).getDate()));
			mWant.setText(String.valueOf(feedDatas.get(postion).getKg()));
			return convertView;

		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode != Activity.RESULT_OK) return;

		adapter.notifyDataSetChanged();

	}

}
