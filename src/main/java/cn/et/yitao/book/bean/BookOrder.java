package cn.et.yitao.book.bean;

import java.util.Date;
import java.util.Objects;

/**
 * Author:Administrator
 * Datetime:2018年10月29日 14:05
 */
public class BookOrder {
    private Integer id; //书籍id
    private Integer quantity; //书籍数量
    private Integer isdelete; //是否删除(1:是,0:否)
    private String name;  //书籍名
    private Integer price;   //书籍价格
    private String imgurl;   //图片地址
    private String author;   //书籍作者
    private String press;   //书籍出版社
    private String desc;  //书籍描述
    private Integer stock;  //书籍库存

    public BookOrder() {
        super();
    }

    public BookOrder(Integer id, Integer quantity, Integer isdelete, String name, Integer price, String imgurl, String author, String press, String desc, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgurl = imgurl;
        this.author = author;
        this.press = press;
        this.desc = desc;
        this.stock = stock;
        this.isdelete = isdelete;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BookOrder{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", isdelete=" + isdelete +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgurl='" + imgurl + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", desc='" + desc + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookOrder bookOrder = (BookOrder) o;
        return Objects.equals(id, bookOrder.id) &&
                Objects.equals(quantity, bookOrder.quantity) &&
                Objects.equals(isdelete, bookOrder.isdelete) &&
                Objects.equals(name, bookOrder.name) &&
                Objects.equals(price, bookOrder.price) &&
                Objects.equals(imgurl, bookOrder.imgurl) &&
                Objects.equals(author, bookOrder.author) &&
                Objects.equals(press, bookOrder.press) &&
                Objects.equals(desc, bookOrder.desc) &&
                Objects.equals(stock, bookOrder.stock);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, quantity, isdelete, name, price, imgurl, author, press, desc, stock);
    }
    public Integer getGid() {
        return id;
    }

    public void setGid(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}