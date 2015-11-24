package tang.di.muzhu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfather.R;

import java.util.Date;
import java.util.zip.Inflater;

import tang.di.database.DataBaseTools;
import tang.di.database.PublicWorld;
import tang.di.date.LittlePigDate;
import tang.di.tools.DatePickerFragment;

/**
 * Created by tangdi on 2015/11/18.
 */
public class RelevanceLPFragment extends Fragment implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    private EditText LpNumber;

    private CheckBox isNomilk;

    private Button LpBirth;

    private Button LpNoMilk;

    private Button save;

    private Bundle Information;

    private LittlePigDate littlePigDate;

    private DataBaseTools dataBaseTools;

    private static final String DIALOG_DATE = "RevevanceDialog";

    private static final int BRITH_CODE = 6;

    private static final int MILK_CODE = 7;

    private static final String TAG = "RelevanceLPFragment";

    private boolean NO_Milk_flag = false;

    public static Fragment getInstance(Bundle bundle){
        RelevanceLPFragment Fragment = new RelevanceLPFragment();
        Fragment.setArguments(bundle);
        return Fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dataBaseTools = DataBaseTools.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container,  @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.brith_lp, container, false);
        init(v);
        return v;
    }

    private void init(View v){
        LpNumber = (EditText)v.findViewById(R.id.lp_number);
        LpBirth = (Button)v.findViewById(R.id.bn_lp_birth);
        LpNoMilk = (Button)v.findViewById(R.id.lp_no_milk);
        isNomilk = (CheckBox)v.findViewById(R.id.is_no_milk);
        save = (Button)v.findViewById(R.id.bn_save);
        Information = getArguments();
        if(Information.getBoolean(MuZhuDetailFragment.BPToLpFlag)){
            littlePigDate = new LittlePigDate();
            littlePigDate.setQUUID(Information.getString("UUID"));
            littlePigDate.setmAdress(Information.getString(MuZhuDetailFragment.LPADRESS));
            Log.d(TAG, "=================new LittlePigDate" + Information.getString("UUID") + "===================");
        }else{
            littlePigDate = dataBaseTools.getLittlePigDataFormDB(
                    Information.getString(MuZhuDetailFragment.UUID), Information.getInt(MuZhuDetailFragment._ID));


            Log.d(TAG, "===init===" + Information.getString(MuZhuDetailFragment.UUID) + Information.getInt(MuZhuDetailFragment._ID));
            Log.d(TAG, "===init====" + littlePigDate.getmPigs());
            Log.d(TAG, "===init===" + littlePigDate.getmLittlePigBrithDay());
            Log.d(TAG, "===init===" + littlePigDate.getmLittlePigMilk());
            LpNumber.setText(String.valueOf(littlePigDate.getmPigs()));
            LpBirth.setText(DataBaseTools.DateToString(littlePigDate.getmLittlePigBrithDay()));
            LpNoMilk.setText(DataBaseTools.DateToString(littlePigDate.getmLittlePigMilk()));
        }
        LpBirth.setOnClickListener(this);
        LpNoMilk.setOnClickListener(this);
        isNomilk.setOnCheckedChangeListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.bn_lp_birth:
                showDialog(BRITH_CODE);
                break;
            case R.id.lp_no_milk:
                showDialog(MILK_CODE);
                break;
            case R.id.bn_save:
                saveButton();
                break;
            default:
                break;
        }
    }

    /**
     * show dialog日期选择
     */

    public void showDialog(int REQUEST_DATE){
        Date date = new Date();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        DatePickerFragment dialog = DatePickerFragment.getInstance(date);
        dialog.setTargetFragment(RelevanceLPFragment.this, REQUEST_DATE);
        dialog.show(fm, DIALOG_DATE);
    }

    @Override
    public void onActivityResult(int QuestionCode, int ResultCode, Intent data){
        if(ResultCode != Activity.RESULT_OK) return;
        if(QuestionCode == BRITH_CODE){
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.DATE);
            littlePigDate.setmLittlePigBrithDay(date);
            LpBirth.setText(DataBaseTools.DateToString(date));
        }else if(QuestionCode == MILK_CODE){
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.DATE);
            littlePigDate.setmLittlePigMilk(date);
            LpNoMilk.setText(DataBaseTools.DateToString(date));
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

        if(isChecked){
            Log.d(TAG, "onCheckedChanged====>" + String.valueOf(isChecked));
            littlePigDate.setmIsNoMilk(true);
            NO_Milk_flag = true;
        }else{
            Log.d(TAG, "onCheckedChanged====>" + String.valueOf(isChecked));
            littlePigDate.setmIsNoMilk(false);
            NO_Milk_flag = false;
        }

    }

    private void saveButton(){
        littlePigDate.setmPigs(Integer.valueOf(LpNumber.getText().toString()));
        if(Information.getBoolean(MuZhuDetailFragment.BPToLpFlag)){
            dataBaseTools.insertDateToSQL(littlePigDate);
            Toast.makeText(getActivity(), "数据库保存成功", Toast.LENGTH_SHORT).show();
        }else{
            /**
             * 数据库更新有问题
             */
            dataBaseTools.upContentValue(littlePigDate);
            Log.d(TAG, "===saveButton===" + littlePigDate.getQUUID() + String.valueOf(littlePigDate.get_id()));
            Toast.makeText(getActivity(), "数据库修改成功", Toast.LENGTH_SHORT).show();
        }
        if(littlePigDate.ismIsNoMilk()){
            dataBaseTools.insertT4(littlePigDate);
            Log.d(TAG, "====" + "进入另一个数据库Table4" + "========");
        }


        getActivity().finish();
    }

}
