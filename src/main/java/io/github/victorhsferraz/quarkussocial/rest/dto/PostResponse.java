package io.github.victorhsferraz.quarkussocial.rest.dto;

import java.time.LocalDateTime;

import io.github.victorhsferraz.quarkussocial.domain.model.Post;
import lombok.Data;

@Data
public class PostResponse {
    private String text;
    private LocalDateTime dateTime;

    public static PostResponse fromEntity(Post post) {
        var response = new PostResponse();
        response.setText(post.getText());
        response.setDateTime(post.getDateTime());
        return response;
    }
}
