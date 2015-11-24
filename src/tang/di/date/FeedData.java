package tang.di.date;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import tang.di.database.DataBaseTools;

/**
 * Created by tangdi on 2015/11/24.
 */
public class FeedData implements Parcelable{

    private String date;

    private float kg;

    private String remark;

    private int _id;

    public Date getDate() {
        return DataBaseTools.StringToDate(date);
    }

    public float getKg() {
        return kg;
    }

    public String getRemark() {
        return remark;
    }

    public int get_id() {
        return _id;
    }

    public void setDate(Date date) {
        this.date = DataBaseTools.DateToString(date);
    }

    public void setKg(float kg) {
        this.kg = kg;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public FeedData(Parcel parcel){
        date = parcel.readString();
        kg = parcel.readFloat();
        remark = parcel.readString();
        _id = parcel.readInt();
    }

    public FeedData(){
        date = DataBaseTools.DateToString(new Date(System.currentTimeMillis()));
        kg = 0;
        remark = "";
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(date);
        dest.writeFloat(kg);
        dest.writeString(remark);
        dest.writeInt(_id);
    }


    public static final Parcelable.Creator<FeedData> CREATOR = new Creator<FeedData>() {

        @Override
        public FeedData[] newArray(int size) {
            return new FeedData[size];
        }

        @Override
        public FeedData createFromParcel(Parcel source) {
            return new FeedData(source);
        }

    };

}
