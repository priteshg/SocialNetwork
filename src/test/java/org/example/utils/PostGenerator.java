package org.example.utils;

import lombok.experimental.UtilityClass;
import org.example.api.pojo.Post;

@UtilityClass
public class PostGenerator {
    public static Post getSamplePost(int userId) {
        return Post.builder()
                .body("Hello testing new post ")
                .title("test title")
                .userId(userId)
                .build();
    }
}
