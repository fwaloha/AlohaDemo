package com.wf.aloha;

import java.io.Serializable;

/**
 * Created by wangfei on 2017/10/17.
 */

public class CheckBean implements Serializable {
    private String order;
    private String name;
    private String content;
    private String time;
    private boolean isChecked;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}