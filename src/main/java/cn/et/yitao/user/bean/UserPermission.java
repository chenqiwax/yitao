package cn.et.yitao.user.bean;

/**
 *Author:Administrator
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月09日 下午 7:05
 */
public class UserPermission {
    private Integer id;//权限id
    private String name;//权限编号
    private Integer bun;//是否禁用
    private String logo;//权限标识
    private String desc;//权限描述
    private String url;//权限url
    private String type;//权限类型
    private Integer menu;//是否有菜单(1:有,0:无)

    public UserPermission() {
        super();
    }

    @Override
    public String toString() {
        return "UserPermissionDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bun=" + bun +
                ", logo='" + logo + '\'' +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", menu=" + menu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPermission that = (UserPermission) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (bun != null ? !bun.equals(that.bun) : that.bun != null) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return menu != null ? menu.equals(that.menu) : that.menu == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (bun != null ? bun.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (menu != null ? menu.hashCode() : 0);
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }
}
