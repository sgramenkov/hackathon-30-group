package bonch.hack.backend.entities;


import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title_news", nullable = false)
    private String titleNews;

    @Column(name = "text_content", nullable = false)
    private String textContent;

    @Column(name = "img", nullable = false)
    private String img;

    public String getTextContent() {
        return textContent;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
