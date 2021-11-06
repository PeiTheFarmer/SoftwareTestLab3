package com.example.lab3;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.Data;

@Entity(tableName = "todoList")
public class NoteBean implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "state")
    private boolean state;

    public NoteBean(String content, Date date, Boolean state) {
        this.content = content;
        this.date = date;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getState() {
        return this.state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.content);
        dest.writeBoolean(this.state);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected NoteBean(Parcel in) {
        this.id = in.readInt();
        this.state = in.readBoolean();
        this.content = in.readString();
        Long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
    }

    public static final Parcelable.Creator<NoteBean> CREATOR = new Parcelable.Creator<NoteBean>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public NoteBean createFromParcel(Parcel source) {
            return new NoteBean(source);
        }

        @Override
        public NoteBean[] newArray(int size) {
            return new NoteBean[size];
        }
    };
}
