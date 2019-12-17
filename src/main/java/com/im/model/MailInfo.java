package com.im.model;

public class MailInfo {

    private String mailAddress;
    private String toMailAccount;
    private String fromMailAccount;
    private String fromMailPassword;
    private String title;
    private String content;
    private String cc;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getToMailAccount() {
        return toMailAccount;
    }

    public void setToMailAccount(String toMailAccount) {
        this.toMailAccount = toMailAccount;
    }

    public String getFromMailAccount() {
        return fromMailAccount;
    }

    public void setFromMailAccount(String fromMailAccount) {
        this.fromMailAccount = fromMailAccount;
    }

    public String getFromMailPassword() {
        return fromMailPassword;
    }

    public void setFromMailPassword(String fromMailPassword) {
        this.fromMailPassword = fromMailPassword;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }
}
