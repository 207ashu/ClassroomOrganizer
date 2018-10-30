package com.neurobreach.classroomorganizer;

public class SubjectDetails {
    String subName,subCode,total,intMarks,extMarks;

    public SubjectDetails(String subName, String subCode, String total, String intMarks, String extMarks) {
        this.subName = subName;
        this.subCode = subCode;
        this.total = total;
        this.intMarks = intMarks;
        this.extMarks = extMarks;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getIntMarks() {
        return intMarks;
    }

    public void setIntMarks(String intMarks) {
        this.intMarks = intMarks;
    }

    public String getExtMarks() {
        return extMarks;
    }

    public void setExtMarks(String extMarks) {
        this.extMarks = extMarks;
    }
}
