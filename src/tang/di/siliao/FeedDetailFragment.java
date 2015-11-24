package tang.di.siliao;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfather.R;

import java.util.Date;

import tang.di.database.DataBaseTools;
import tang.di.date.FeedData;
import tang.di.tools.DatePickerFragment;

/**
 * Created by tangdi on 2015/11/23.
 */
public class FeedDetailFragment extends Fragment implements View.OnClickListener {

    private FeedData feedData;

    private static final int QUESTIONCODE = 2;

    private static final String DIALOG_FLAG = "dialog";

    private Button bn;

    private EditText kg;

    private Button save;

    private EditText beiwang;

    private DataBaseTools dataBaseTools;

    private Bundle bundle;

    public static FeedDetailFragment getInstance(Bundle bundle){
        FeedDetailFragment fragment = new FeedDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle SaveBundleInstance){
        super.onCreate(SaveBundleInstance);
        dataBaseTools = DataBaseTools.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.detail_feed, container, false);
        init(v);
        return v;
    }

    private void init(View v){
        bn = (Button)v.findViewById(R.id.bt_date);
        kg = (EditText)v.findViewById(R.id.kg);
        beiwang = (EditText)v.findViewById(R.id.bei_zhu);
        save = (Button)v.findViewById(R.id.save_bt);
        bundle = getArguments();
        if(bundle.getBoolean(SiLiaoListFragment.DATE_FLAG)){
            feedData = bundle.getParcelable(SiLiaoListFragment.DATE_FEED);
            bn.setText(DataBaseTools.DateToString(feedData.getDate()));
            beiwang.setText(feedData.getRemark());
            kg.setText(String.valueOf(feedData.getKg()));
        }else{
            feedData = new FeedData();
        }

        bn.setOnClickListener(this);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bt_date :
                showDialog();
                break;
            case R.id.save_bt :
                btSave();
                break;
            default:
                break;
        }
    }

    private void showDialog(){
        Date date = new Date();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        DatePickerFragment dialog = DatePickerFragment.getInstance(date);
        dialog.setTargetFragment(this, QUESTIONCODE);
        dialog.show(fm, DIALOG_FLAG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode != Activity.RESULT_OK) return;
        if(requestCode == QUESTIONCODE){
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.DATE);
            bn.setText(DataBaseTools.DateToString(date));
            feedData.setDate(date);
        }

    }

    private void btSave(){
        feedData.setRemark(beiwang.getText().toString());
        feedData.setKg(Float.valueOf(kg.getText().toString()));
        if(bundle.getBoolean(SiLiaoListFragment.DATE_FLAG)){
            dataBaseTools.upDate5(feedData);
        }else{
            dataBaseTools.insert(feedData);
        }
    }



}
