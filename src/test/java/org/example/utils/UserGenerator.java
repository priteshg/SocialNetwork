package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.example.api.pojo.user.User;

@UtilityClass
public class UserGenerator {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static User getSampleUser() {
        String json = "{\n" +
                "  \"name\": \"Joe Blogs\",\n" +
                "  \"username\": \"Bret\",\n" +
                "  \"email\": \"Sincere@april.biz\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Kulas Light\",\n" +
                "    \"suite\": \"Apt. 556\",\n" +
                "    \"city\": \"Gwenborough\",\n" +
                "    \"zipcode\": \"92998-3874\",\n" +
                "    \"geo\": {\n" +
                "      \"lat\": \"-37.3159\",\n" +
                "      \"lng\": \"81.1496\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"phone\": \"1-770-736-8031 x56442\",\n" +
                "  \"website\": \"hildegard.org\",\n" +
                "  \"company\": {\n" +
                "    \"name\": \"Romaguera-Crona\",\n" +
                "    \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "    \"bs\": \"harness real-time e-markets\"\n" +
                "  }\n" +
                "}";

        try {
            return mapper.readValue(json, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
