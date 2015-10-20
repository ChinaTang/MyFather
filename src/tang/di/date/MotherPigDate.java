package tang.di.date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tangdi on 2015/10/15.
 */
public class MotherPigDate {

    private static String Adress;

    private Date mFaQingdate, mShoujingDate, mChanzaiDate;

    private static ArrayList<LittlePigDate> mList;

    private static final String ADRESS = "ADRESS";

    private static final String FAQINGDATE = "FAQINGDATE";

    private static final String SHOUJINGDATE = "SHOUJINGDATE";

    private static final String CHANZAIDATE = "CHANZAIDATE";

    private static final String LITTLEARRAY = "LITTLEARRAY";

    public MotherPigDate(){}


    public MotherPigDate(String Adress, Date FaQingDate, Date mShoujingDate, Date mChanzaiDate){

        this.Adress = Adress;
        this.mFaQingdate = FaQingDate;
        this.mShoujingDate = mShoujingDate;
        this.mChanzaiDate = mChanzaiDate;
        mList = new ArrayList<LittlePigDate>();

    }

    public static void addLittlePigList(LittlePigDate item){
        mList.add(item);
    }

    /**
     * JSON数据初始化
     * @param json
     * @throws JSONException
     */
    public MotherPigDate(JSONObject json) throws JSONException {
        Adress = json.getString(ADRESS);
        mFaQingdate = new Date(json.getLong(FAQINGDATE));
        mShoujingDate = new Date(json.getLong(SHOUJINGDATE));
        mChanzaiDate = new Date(json.getLong(CHANZAIDATE));
        JSONArray mJSONLittle = json.getJSONArray(LITTLEARRAY);
        for(int i = 0; i < mJSONLittle.length(); i++){
            mList.add(new LittlePigDate(mJSONLittle.getJSONObject(i)));
        }
    }

    /**
     * JSON数据JSON化
     * @return
     * @throws JSONException
     */
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(ADRESS, Adress);
        json.put(FAQINGDATE, mFaQingdate.getTime());
        json.put(SHOUJINGDATE, mShoujingDate.getTime());
        json.put(CHANZAIDATE, mChanzaiDate.getTime());
        JSONArray array = new JSONArray();
        for(int i = 0; i < mList.size(); i++){
            array.put(mList.get(i).toJSON());
        }
        json.put(LITTLEARRAY, array);
        return json;
    }



    public static String getAdress() {
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

    public static ArrayList<LittlePigDate> getmList() {
        return mList;
    }


    public static void setAdress(String adress) {
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

    public static void setmList(ArrayList<LittlePigDate> mList) {
        MotherPigDate.mList = mList;
    }

}
