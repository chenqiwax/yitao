package cn.et.yitao.book.bean;

import java.util.Date;

/**
 * Author:chenqi
 * Email:chenqiwax@gmail.com
 * Datetime:2018年10月08日 下午 9:08
 */

/***
 * 书籍详情
 */
public class Book {
    private Integer id;   //书籍id
    private String name;  //书籍名
    private Integer price;   //书籍价格
    private String imgurl;   //图片地址
    private String author;   //书籍作者
    private String press;   //书籍出版社
    private Integer sales;   //商品销售量
    private Integer lid;    //分类id
    private Integer stock;   //书籍库存
    private Date publishDate;  //出版时间
    private Integer pageNum; //页数
    private Integer wordsNum; //字数
    private String paper;   //纸张类型
    private String details;  //书籍详情
    private String desc;  //书籍描述
    private Integer isdelete;//是否删除(1:是,0:否)
    private BookCategory bookCategory;//分类对象

    public Book() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (imgurl != null ? !imgurl.equals(book.imgurl) : book.imgurl != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (press != null ? !press.equals(book.press) : book.press != null) return false;
        if (sales != null ? !sales.equals(book.sales) : book.sales != null) return false;
        if (lid != null ? !lid.equals(book.lid) : book.lid != null) return false;
        if (stock != null ? !stock.equals(book.stock) : book.stock != null) return false;
        if (publishDate != null ? !publishDate.equals(book.publishDate) : book.publishDate != null) return false;
        if (pageNum != null ? !pageNum.equals(book.pageNum) : book.pageNum != null) return false;
        if (wordsNum != null ? !wordsNum.equals(book.wordsNum) : book.wordsNum != null) return false;
        if (paper != null ? !paper.equals(book.paper) : book.paper != null) return false;
        if (details != null ? !details.equals(book.details) : book.details != null) return false;
        if (desc != null ? !desc.equals(book.desc) : book.desc != null) return false;
        if (isdelete != null ? !isdelete.equals(book.isdelete) : book.isdelete != null) return false;
        return bookCategory != null ? bookCategory.equals(book.bookCategory) : book.bookCategory == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (imgurl != null ? imgurl.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (press != null ? press.hashCode() : 0);
        result = 31 * result + (sales != null ? sales.hashCode() : 0);
        result = 31 * result + (lid != null ? lid.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (pageNum != null ? pageNum.hashCode() : 0);
        result = 31 * result + (wordsNum != null ? wordsNum.hashCode() : 0);
        result = 31 * result + (paper != null ? paper.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (isdelete != null ? isdelete.hashCode() : 0);
        result = 31 * result + (bookCategory != null ? bookCategory.hashCode() : 0);
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

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getWordsNum() {
        return wordsNum;
    }

    public void setWordsNum(Integer wordsNum) {
        this.wordsNum = wordsNum;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {

        this.bookCategory = bookCategory;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgurl='" + imgurl + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", sales=" + sales +
                ", lid=" + lid +
                ", stock=" + stock +
                ", publishDate=" + publishDate +
                ", pageNum=" + pageNum +
                ", wordsNum=" + wordsNum +
                ", paper='" + paper + '\'' +
                ", details='" + details + '\'' +
                ", desc='" + desc + '\'' +
                ", isdelete=" + isdelete +
                ", bookCategory=" + bookCategory +
                '}';
    }
}
