package tang.di.tools;

import java.util.ArrayList;

import tang.di.xiaozhu.data.XiaoZhuData;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myfather.R;

public class XiaoZhuAdapter extends BaseAdapter{

	private Context mContext;
	private ArrayList<XiaoZhuData> mData;

	public XiaoZhuAdapter(Context context, ArrayList<XiaoZhuData> data) {
		mContext = context;
		mData = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater mInflater = LayoutInflater.from(mContext);
		convertView = mInflater.inflate(R.layout.xiao_zhu_list_item, null);
		TextView date = (TextView) convertView
				.findViewById(R.id.xiao_zhu_list_item_date);
		TextView xiaozhuZongshu = (TextView) convertView
				.findViewById(R.id.xiao_zhu_zong_shu);
		TextView xiaozhuTitle = (TextView) convertView
				.findViewById(R.id.xiao_zhu_list_item_title);
		date.setText(mData.get(position).getXiao_zhu_chu_sheng_ri_qi());
		xiaozhuZongshu.setText(mData.get(position)
				.getXiao_zhu_xian_zai_zhi_shu());
		xiaozhuTitle.setText(mData.get(position).getXiao_zhu_suo_zai_wei_zhi());

		return convertView;
	}
}
