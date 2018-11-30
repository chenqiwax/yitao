package cn.et.yitao.home.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *Author:Administrator
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月09日 下午 7:03
 */
public class Advertising implements Serializable{

    private static final long serialVersionUID = 7204528439195892316L;
    private Integer id; //广告id
    private String headline;//广告标题
    private String subhead;//广告副标题
    private String url;//广告图片地址
    private String link;//图片链接地址
    private int isdelete;//是否删除(1:是,0:否)
    private Date createDate;//创建时间

    public Advertising() {
        super();
    }

    @Override
    public String toString() {
        return "Advertising{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", subhead='" + subhead + '\'' +
                ", url='" + url + '\'' +
                ", link='" + link + '\'' +
                ", isdelete=" + isdelete +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advertising that = (Advertising) o;

        if (isdelete != that.isdelete) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (headline != null ? !headline.equals(that.headline) : that.headline != null) return false;
        if (subhead != null ? !subhead.equals(that.subhead) : that.subhead != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        return createDate != null ? createDate.equals(that.createDate) : that.createDate == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (headline != null ? headline.hashCode() : 0);
        result = 31 * result + (subhead != null ? subhead.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + isdelete;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
