package cn.et.yitao.book.bean;

/**
 * Author:Administrator
 * Email:chenqiwax@gmail.com
 * Datetime:2018年10月09日 下午 7:06
 */

import java.util.List;
import java.util.Objects;

/**
 * 书籍分类
 */
public class BookCategory {
    private Integer id; //分类id
    private String name; //分类名
    private Integer pid; //分类父id
    private String desc; //分类描述
    private Integer isdelete; //是否删除(1:是,0:否)
    private List<BookCategory> categoryList;//所有子分类

    @Override
    public String toString() {
        return "BookCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", desc='" + desc + '\'' +
                ", isdelete=" + isdelete +
                ", categoryList=" + categoryList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCategory that = (BookCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(pid, that.pid) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(isdelete, that.isdelete) &&
                Objects.equals(categoryList, that.categoryList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, pid, desc, isdelete, categoryList);
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public List<BookCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<BookCategory> categoryList) {
        this.categoryList = categoryList;
    }
}
