package cn.et.yitao.user.bean;

import java.util.List;

/**
 *Author:Administrator
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月09日 下午 7:05
 */
public class Role {
    private Integer id;//角色id
    private String name;//角色名
    private Integer bun;//是否禁用 (1:否,0:是)
    private String desc;//描述
    private Integer isDelete;//是否删除(1:是,0:否)

    public Role() {
        super();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bun=" + bun +
                ", desc='" + desc + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        if (bun != null ? !bun.equals(role.bun) : role.bun != null) return false;
        if (desc != null ? !desc.equals(role.desc) : role.desc != null) return false;
        return isDelete != null ? isDelete.equals(role.isDelete) : role.isDelete == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (bun != null ? bun.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBun() {
        return bun;
    }

    public void setBun(Integer bun) {
        this.bun = bun;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
