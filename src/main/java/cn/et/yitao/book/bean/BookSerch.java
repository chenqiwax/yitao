package cn.et.yitao.book.bean;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年11月06日 上午 10:36
 */
public class BookSerch {
    private Integer id;
    private String bookName;
    private String desc;
    private String bookAuthor;
    private Integer price;
    private String imgurl;
    private String catName;

    public BookSerch() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookSerch bookSerch = (BookSerch) o;

        if (id != null ? !id.equals(bookSerch.id) : bookSerch.id != null) return false;
        if (bookName != null ? !bookName.equals(bookSerch.bookName) : bookSerch.bookName != null) return false;
        if (desc != null ? !desc.equals(bookSerch.desc) : bookSerch.desc != null) return false;
        if (bookAuthor != null ? !bookAuthor.equals(bookSerch.bookAuthor) : bookSerch.bookAuthor != null) return false;
        if (price != null ? !price.equals(bookSerch.price) : bookSerch.price != null) return false;
        if (imgurl != null ? !imgurl.equals(bookSerch.imgurl) : bookSerch.imgurl != null) return false;
        return catName != null ? catName.equals(bookSerch.catName) : bookSerch.catName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (bookAuthor != null ? bookAuthor.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (imgurl != null ? imgurl.hashCode() : 0);
        result = 31 * result + (catName != null ? catName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookSerch{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", desc='" + desc + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", price=" + price +
                ", imgurl='" + imgurl + '\'' +
                ", catName='" + catName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
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

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
