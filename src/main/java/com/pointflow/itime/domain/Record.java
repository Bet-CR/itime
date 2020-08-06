package com.pointflow.itime.domain;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-08-03
 * Time: 22:28
 **/
public class Record {

    private Long saveTimestamp;
    private List recordData;

    public Long getSaveTimestamp() {
        return saveTimestamp;
    }

    public void setSaveTimestamp(Long saveTimestamp) {
        this.saveTimestamp = saveTimestamp;
    }

    public List getRecordData() {
        return recordData;
    }

    public void setRecordData(List recordData) {
        this.recordData = recordData;
    }

    @Override
    public String toString() {
        return "Record{" +
                "saveTimestamp=" + saveTimestamp +
                ", recordData=" + recordData +
                '}';
    }
}
