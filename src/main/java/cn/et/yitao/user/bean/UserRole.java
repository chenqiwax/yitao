package cn.et.yitao.user.bean;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月10日 上午 8:45
 */
public class UserRole {
    private String uid;//用户id
    private Integer eid;//角色id

    public UserRole() {
        super();
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "uid='" + uid + '\'' +
                ", eid=" + eid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (uid != null ? !uid.equals(userRole.uid) : userRole.uid != null) return false;
        return eid != null ? eid.equals(userRole.eid) : userRole.eid == null;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (eid != null ? eid.hashCode() : 0);
        return result;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}
