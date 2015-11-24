package tang.di.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myfather.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by tangdi on 2015/11/16.
 */
public class MyAdapter<T> extends ArrayAdapter<T>{

    private Context mContext;


    private ArrayList<T> mList;



    public MyAdapter(Context context, ArrayList<T> list){
        super(context, 0, list);
        mContext = context.getApplicationContext();
    }

    /**
     * getView方法
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_view_item, null);
        }
        T data = getItem(position);
        TextView mKind = (TextView)convertView.findViewById(R.id.list_item_kind);
        TextView mAdress = (TextView)convertView.findViewById(R.id.list_item_adress);
        TextView mWant = (TextView)convertView.findViewById(R.id.list_item_think);
        return null;
    }

}
