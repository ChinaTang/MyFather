package tang.di.zhangmuzonglan;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myfather.R;

import java.util.ArrayList;

import tang.di.database.DataBaseTools;
import tang.di.date.FeedData;
import tang.di.date.ZhangMuDate;

/**
 * Created by tangdi on 2015/11/24.
 */
public class ZhangMuFragment extends Fragment{

    private ListView listView;

    private ArrayList<ZhangMuDate> arrayList;

    private DataBaseTools dataBaseTools;

    private MyAdapter adapter;

    @Override
    public void onCreate(Bundle SaveBundleInstance){
        super.onCreate(SaveBundleInstance);
        dataBaseTools = DataBaseTools.getInstance(getActivity());
        arrayList = dataBaseTools.select6();
        adapter = new MyAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.zhang_mu_list, container, false);
        listView = (ListView)v.findViewById(R.id.zhang_mulistview);
        listView.setAdapter(adapter);
        return v;
    }

    private class MyAdapter extends ArrayAdapter<ZhangMuDate>{

        private Context context;
        public MyAdapter(Context context){
            super(context, 0, arrayList);
            this.context = context;
        }

        @Override
        public View getView(int postion, View convertView, ViewGroup parent){

            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_item, null);
            TextView mKind = (TextView)convertView.findViewById(R.id.list_item_kind);
            TextView mAdress = (TextView)convertView.findViewById(R.id.list_item_adress);
            TextView mWant = (TextView)convertView.findViewById(R.id.list_item_think);
            mKind.setText("zhangmu");
            mAdress.setText(DataBaseTools.DateToString(arrayList.get(postion).getDate()));
            mWant.setText(String.valueOf(arrayList.get(postion).getMoney()));
            return convertView;

        }

    }

}
