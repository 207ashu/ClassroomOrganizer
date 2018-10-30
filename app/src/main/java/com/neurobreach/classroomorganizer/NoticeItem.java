package com.neurobreach.classroomorganizer;

public class NoticeItem {
    String t,desc;

    public NoticeItem(String t, String desc) {
        this.t = t;
        this.desc = desc;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
