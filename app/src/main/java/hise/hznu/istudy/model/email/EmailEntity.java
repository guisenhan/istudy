package hise.hznu.istudy.model.email;

import java.io.Serializable;

/**
 * Created by PC on 2016/9/26.
 */
public class EmailEntity implements Serializable{
    /**
     * {
     id			:		短信id
     code		：		短信编码
     subject	：		主题
     content	:		短信内容 html
     date		：		发送时间 yyyyMMddHHmmss
     isread		:		是否已经读，1=已经读，0=未读
     senderid	：		发送人id
     sendername： 		发送人姓名
     */
    private String id;
    private String code;
    private String subject;
    private String content;
    private String date;
    private boolean isread;
    private String senderid;
    private String sendername;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
