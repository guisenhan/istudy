package hise.hznu.istudy.model.email;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC on 2016/11/16.
 */
public class ContacterEntity implements Serializable{
    /**
     * {"Label":"计算机原理（计141）",
     * "ContacterList":[
     * {
     * "Id":273,
     * "Name":"阿曼古丽·合孜尔别克",
     * "RoleName":null,
     * "Email":null,
     * "Phone":"18857118593",
     * "Face":"http://dodo.hznu.edu.cn/content/images/avatar_default.gif"}
     */
    private String Label;
    private List<ContacterList> ContacterList;

    public List<ContacterEntity.ContacterList> getContacterList() {
        return ContacterList;
    }

    public void setContacterList(List<ContacterEntity.ContacterList> contacterList) {
        ContacterList = contacterList;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public static class ContacterList{
        private String Id;
        private String Name;
        private String RoleName;
        private String Email;
        private String Phone;
        private String Face;

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getFace() {
            return Face;
        }

        public void setFace(String face) {
            Face = face;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getRoleName() {
            return RoleName;
        }

        public void setRoleName(String roleName) {
            RoleName = roleName;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }
    }
}
