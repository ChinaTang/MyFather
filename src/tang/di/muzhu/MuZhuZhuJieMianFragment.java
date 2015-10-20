package tang.di.muzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.myfather.R;

public class MuZhuZhuJieMianFragment extends Fragment implements
		OnClickListener {
	Button mAdd;
	ListView listview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

		return v;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getActivity(), MuZhuDetailActivity.class);
		getActivity().startActivity(intent);

	}

}
