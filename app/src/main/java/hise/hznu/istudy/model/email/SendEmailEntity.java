package hise.hznu.istudy.model.email;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC on 2016/11/16.
 */
public class SendEmailEntity implements Serializable{
    /**
     * {"id":259,"code":"259","subject":"测试邮件功能","content":"测试邮件功能","date":"20161116145154","receives":
     */
    private String id;
    private String code;
    private String subject;
    private String content;
    private String date;
    private List<receives> receives;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SendEmailEntity.receives> getReceives() {
        return receives;
    }

    public void setReceives(List<SendEmailEntity.receives> receives) {
        this.receives = receives;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public static class receives implements Serializable{
        /**
         * "id":5814,"name":"李佳玘","isread":false
         */
        private String id;
        private String name;
        private boolean isread;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
