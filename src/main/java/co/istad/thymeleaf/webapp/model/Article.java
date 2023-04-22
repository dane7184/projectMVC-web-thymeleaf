package co.istad.thymeleaf.webapp.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private UUID uuid;

    @NotBlank(message = "Title is required..!")
    private String title;

    private String thumbnail;

    private Author author;

    @NotBlank(message = "please add Description")
    private String text;

    private Integer index;

    private String category;
}
