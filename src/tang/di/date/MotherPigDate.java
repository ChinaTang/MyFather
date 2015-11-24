package tang.di.date;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tangdi on 2015/10/15.
 */
public class MotherPigDate {

    private String Adress;

    private Date mFaQingdate, mShoujingDate, mChanzaiDate;

    /**
     * 查询标识符
     */
    private String QunID;

    private ArrayList<LittlePigDate> mList;

    private static final String ADRESS = "ADRESS";

    private static final String FAQINGDATE = "FAQINGDATE";

    private static final String SHOUJINGDATE = "SHOUJINGDATE";

    private static final String CHANZAIDATE = "CHANZAIDATE";

    private static final String LITTLEARRAY = "LITTLEARRAY";


    public MotherPigDate(){
        UUID uuid = UUID.randomUUID();
        QunID = uuid.toString();
        Adress = null;
        mList = null;
        mFaQingdate = new Date(System.currentTimeMillis());
        mShoujingDate = new Date(System.currentTimeMillis());
        mChanzaiDate = new Date(System.currentTimeMillis());
    }


    public MotherPigDate(String Adress, Date FaQingDate, Date mShoujingDate, Date mChanzaiDate){

        this.Adress = Adress;
        this.mFaQingdate = FaQingDate;
        this.mShoujingDate = mShoujingDate;
        this.mChanzaiDate = mChanzaiDate;
        mList = new ArrayList<LittlePigDate>();

    }

    public void addLittlePigList(LittlePigDate item){
        mList.add(item);
    }


    public String getAdress() {
        return Adress;
    }

    public Date getmFaQingdate() {
        return mFaQingdate;
    }

    public Date getmShoujingDate() {
        return mShoujingDate;
    }

    public Date getmChanzaiDate() {
        return mChanzaiDate;
    }

    public ArrayList<LittlePigDate> getmList() {
        return mList;
    }


    public void setAdress(String adress) {
        Adress = adress;
    }

    public void setmFaQingdate(Date mFaQingdate) {
        this.mFaQingdate = mFaQingdate;
    }

    public void setmShoujingDate(Date mShoujingDate) {
        this.mShoujingDate = mShoujingDate;
    }

    public void setmChanzaiDate(Date mChanzaiDate) {
        this.mChanzaiDate = mChanzaiDate;
    }

    public void setmList(ArrayList<LittlePigDate> mList) {
        this.mList = mList;
    }

    public String getQunID() {
        return QunID;
    }

    public void setQunID(String qunID) {
        QunID = qunID;
    }


}
