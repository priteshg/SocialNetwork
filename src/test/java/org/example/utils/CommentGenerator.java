package org.example.utils;

import lombok.experimental.UtilityClass;
import org.example.api.pojo.Comment;

@UtilityClass
public class CommentGenerator {
    public static Comment getSampleComment() {
        return Comment.builder()
                .name("Joe Blogs")
                .email("joeblogs1234@gardner.biz")
                .body("laudantium enim quasi est quidem magnam voluptate ipsam eosntempora quo necessitatibusndolor quam autem quasinreiciendis et nam sapiente accusantium")
                .build();
    }
}
