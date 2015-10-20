package tang.di.xiaozhu;

import tang.di.tools.XiaoZhuAdapter;
import tang.di.xiaozhu.data.XiaoZhuData;
import tang.di.xiaozhu.data.XiaoZhuDataList;
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
import android.widget.Button;
import android.widget.ListView;

import com.example.myfather.R;

public class XiaoZhuListFragment extends Fragment implements OnClickListener,
		OnItemClickListener, OnItemLongClickListener {

	private ListView listview;
	private XiaoZhuAdapter mAdapter;
	private XiaoZhuDataList mData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mData = XiaoZhuDataList.getInstance();
		mAdapter = new XiaoZhuAdapter(getActivity(), mData.getListForXiaoZhu());
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.detail_little_pig_zhu_jiemian,
				container, false);
		listview = (ListView) root.findViewById(R.id.xiao_zhu_listview);
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
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
