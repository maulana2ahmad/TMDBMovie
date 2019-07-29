package redudant.tmdbmovie.model.videos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosDBresponse implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<Videos> videos = null;
    public final static Parcelable.Creator<Videos> CREATOR = new Creator<Videos>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Videos createFromParcel(Parcel in) {
            return new Videos(in);
        }

        public Videos[] newArray(int size) {
            return (new Videos[size]);
        }
    };

    protected VideosDBresponse(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.videos, (Videos.class.getClassLoader()));
    }

    public VideosDBresponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Videos> getvideos() {
        return videos;
    }

    public void setvideos(List<Videos> videos) {
        this.videos = videos;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(videos);
    }

    public int describeContents() {
        return 0;
    }

}
