package com.mobile.mutia.sayuronline.notification;

public class Data {

    private String user;
    private Integer icon;
    private String body;
    private String title;
    private String sent;

    public Data() {
    }

    public Data(String user, Integer icon, String body, String title, String sent) {
        this.user = user;
        this.icon = icon;
        this.body = body;
        this.title = title;
        this.sent = sent;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }
}
