package cn.et.yitao.cart.bean;

/**
 * Author:libinhe
 * Datetime:2018年10月15日下午 3:15
 */
public class MyCart {

    private Integer id;  //购物车id
    private String uid; //用户id
    private Integer gid; //书籍id
    private Integer quantity; //书籍数量
    private Integer isdelete; //是否删除(1:是,0:否)
    private String name;  //书籍名
    private Integer price;   //书籍价格
    private String imgurl;   //图片地址
    private String author;   //书籍作者
    private String press;   //书籍出版社
    private String desc;  //书籍描述
    private String stock;  //书籍库存


    public MyCart() {
    }


    public MyCart(Integer id, String uid, Integer gid, Integer quantity, Integer isdelete, String name, Integer price, String imgurl, String author, String press, String desc, String stock) {
        this.id = id;
        this.uid = uid;
        this.gid = gid;
        this.quantity = quantity;
        this.isdelete = isdelete;
        this.name = name;
        this.price = price;
        this.imgurl = imgurl;
        this.author = author;
        this.press = press;
        this.desc = desc;
        this.stock = stock;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
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

    @Override
    public String toString() {
        return "MyCart{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", gid=" + gid +
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
}
