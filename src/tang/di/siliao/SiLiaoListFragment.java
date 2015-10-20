package tang.di.siliao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.myfather.R;

public class SiLiaoListFragment extends Fragment implements OnClickListener,
		OnItemClickListener {

	private Button mAddSiLiaoZhangMu;
	private ListView mList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.detai_si_liao, container, false);
		mAddSiLiaoZhangMu = (Button) v.findViewById(R.id.add_detail_siliao);
		mList = (ListView) v.findViewById(R.id.si_liao);
		mAddSiLiaoZhangMu.setOnClickListener(this);
		mList.setOnItemClickListener(this);
		
		return v;

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
