package com.huiy.ssm.demo.javamail;

public class EmailVO {

    /** 发件人 **/
    private String fromAddress;

    /** 收件人 **/
    private String toAddress;

    /** 邮件主题 **/
    private String subject;

    /** 邮件内容 **/
    private String content;

    /**抄送**/
    private String[] ccList;

    public String getFromAddress(){
        return fromAddress;
    }

    public void setFromAddress(String fromAddress){
        this.fromAddress = fromAddress;
    }

    public String getToAddress(){
        return toAddress;
    }

    public void setToAddress(String toAddress){
        this.toAddress = toAddress;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String[] getCcList(){
        return ccList;
    }

    public void setCcList(String[] ccList){
        this.ccList = ccList;
    }
}
