package org.example.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder
@Jacksonized
public class Post {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
