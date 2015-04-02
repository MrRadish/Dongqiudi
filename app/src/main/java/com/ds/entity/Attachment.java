package com.ds.entity;

/**
 * Created by aaa on 15-3-26.
 */
public class Attachment {
    private int height;      //附件的高
    private int width;       //附件的宽
    private String thumb;    //缩略图地址
    private String url;     //原图地址


    public Attachment(){

    }
    public Attachment(int height, int width, String thumb, String url) {
        this.height = height;
        this.width = width;
        this.thumb = thumb;
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getwidth() {
        return width;
    }

    public void setwidth(int width) {
        this.width = width;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "height=" + height +
                ", width=" + width +
                ", thumb='" + thumb + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
