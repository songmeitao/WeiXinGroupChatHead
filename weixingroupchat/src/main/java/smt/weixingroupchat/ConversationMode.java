package smt.weixingroupchat;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Administrator on 2016/8/11.
 */
public class ConversationMode {

    private List<Bitmap> bitmaps;
    private String time;
    private String title;
    private String message;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Bitmap> getBitmaps() {
        return bitmaps;
    }

    public void setBitmaps(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }
}
