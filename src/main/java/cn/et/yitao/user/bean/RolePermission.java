package cn.et.yitao.user.bean;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月10日 上午 8:44
 */
public class RolePermission {
    private Integer eid;//角色id
    private Integer sid;//权限id

    public RolePermission() {
        super();
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "eid=" + eid +
                ", sid=" + sid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePermission that = (RolePermission) o;

        if (eid != null ? !eid.equals(that.eid) : that.eid != null) return false;
        return sid != null ? sid.equals(that.sid) : that.sid == null;
    }

    @Override
    public int hashCode() {
        int result = eid != null ? eid.hashCode() : 0;
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        return result;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
