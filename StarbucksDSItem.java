
package io.buildup.pkg20171016225815.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import buildup.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class StarbucksDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("text1") public String text1;
    @SerializedName("picture") public String picture;
    @SerializedName("desc") public String desc;
    @SerializedName("id") public String id;
    @SerializedName("pictureUri") public transient Uri pictureUri;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text1);
        dest.writeString(picture);
        dest.writeString(desc);
        dest.writeString(id);
    }

    public static final Creator<StarbucksDSItem> CREATOR = new Creator<StarbucksDSItem>() {
        @Override
        public StarbucksDSItem createFromParcel(Parcel in) {
            StarbucksDSItem item = new StarbucksDSItem();

            item.text1 = in.readString();
            item.picture = in.readString();
            item.desc = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public StarbucksDSItem[] newArray(int size) {
            return new StarbucksDSItem[size];
        }
    };

}

