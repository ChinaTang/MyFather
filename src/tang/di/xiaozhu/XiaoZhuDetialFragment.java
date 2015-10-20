package tang.di.xiaozhu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfather.R;

public class XiaoZhuDetialFragment extends Fragment implements OnClickListener {

	private EditText mXiaoZhuWeiZhi, mXiaoZhuZhiShu, mMaiChuXiaoZhuZhiShu,
			mMaichuXiaozhuZongjia;
	private Button mXiaoZhuChuShengRiQi, mXiaoZhuMaiChuRiQi, mSave;

	private static final String ADRESS = "Adress";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.detal_little_pig, container,
				false);
		mXiaoZhuZhiShu = (EditText) root.findViewById(R.id.xiao_zhu_zhi_shu);
		mMaiChuXiaoZhuZhiShu = (EditText) root
				.findViewById(R.id.xiao_zhu_mai_chu_zhi_shu);
		mMaichuXiaozhuZongjia = (EditText) root
				.findViewById(R.id.xiao_zhu_mai_chu_jia_ge);
		mXiaoZhuChuShengRiQi = (Button) root
				.findViewById(R.id.xiao_zhu_chu_sheng_ri_qi);
		mXiaoZhuMaiChuRiQi = (Button) root
				.findViewById(R.id.xiao_zhu_mai_chu_ri_qi);
		mSave = (Button) root.findViewById(R.id.save);

		return root;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	public static XiaoZhuDetialFragment getInstance(String Adress){
		Bundle mAdress = new Bundle();
		mAdress.putSerializable(ADRESS, Adress);
		XiaoZhuDetialFragment fragment = new XiaoZhuDetialFragment();
		fragment.setArguments(mAdress);
		return fragment;
	}

}
