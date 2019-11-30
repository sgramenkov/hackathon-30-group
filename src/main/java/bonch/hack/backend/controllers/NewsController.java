package bonch.hack.backend.controllers;

import bonch.hack.backend.entities.News;
import bonch.hack.backend.entities.NewsComment;
import bonch.hack.backend.repositories.NewsCommentRepository;
import bonch.hack.backend.repositories.NewsRepository;
import bonch.hack.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static bonch.hack.backend.JsonSingleton.getJSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public class NewsController {

    private static final String SERVER_URL = "http://localhost:8080/";
    private static final String PATH_IMAGES_NEWS = "images_news/";

    private final NewsRepository newsRepository;
    private final NewsCommentRepository newsCommentRepository;
    private final UserRepository userRepository;


    @Autowired
    public NewsController(NewsRepository newsRepository, NewsCommentRepository newsCommentRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.newsCommentRepository = newsCommentRepository;
        this.userRepository = userRepository;
    }

    //POST set news
    @RequestMapping(value = "/news", method = POST)
    @ResponseBody
    public HttpStatus setNews(
            @RequestParam("title_news") String titleNews,
            @RequestParam("text_content") String textContent,
            @RequestParam("img") MultipartFile img) {

        News news;
        HttpStatus httpStatus;
        BufferedOutputStream stream;
        try {
            news = new News();
            news.setTitleNews(titleNews);
            news.setTextContent(textContent);
            // TODO: 11/30/2019  
            news.setImg(SERVER_URL + PATH_IMAGES_NEWS + titleNews.hashCode() + ".png");

            stream = new BufferedOutputStream(new FileOutputStream(new File(PATH_IMAGES_NEWS + textContent.hashCode() + ".png")));
            stream.write(img.getBytes());
            stream.close();
            newsRepository.save(news);

            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return httpStatus;
    }

    //POST set comment
    @RequestMapping(value = "/news/comment", method = POST)
    @ResponseBody
    public HttpStatus setComment(
            @RequestParam("news_id") long newsId,
            @RequestParam("user_id") long userId,
            @RequestParam("comment") String comment) {

        NewsComment newsComment;
        HttpStatus httpStatus;
        try {
            newsComment = new NewsComment();
            newsComment.setNews(newsRepository.findById(newsId).get());
            newsComment.setUser(userRepository.findById(userId).get());
            newsComment.setComment(comment);
            newsComment.setTimeStamp(System.currentTimeMillis() / 1000L);
            newsCommentRepository.save(newsComment);

            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return httpStatus;
    }

    //GET get news
    @RequestMapping(
            value = "/news",
            method = GET)
    @ResponseBody
    public String getNews() {
        return getJSON(newsRepository.findAll());
    }
    // TODO: 11/30/2019  

}
