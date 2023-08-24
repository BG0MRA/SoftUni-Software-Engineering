package softuni.exam.models.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class BookImportDto {
    //    "author": "F. Scott Fitzgerald",
    //    "available": true,
    //    "description": "A classic novel set in the roaring 20s",
    //    "genre": "CLASSIC_LITERATURE",
    //    "title": "The Great Gatsby",
    //    "rating": 9.1
    //•	title – accepts char sequence (between 3 to 40 inclusive). The values are unique in the database.
    //•	author - accepts char sequence (between 3 to 40 inclusive).
    //•	description - a long and detailed description about the book with a character length value higher than or equal to 5.
    //•	available – accepts a true or false, representing the availability status of the book.
    //•	genre – String enumeration, one of the following – CLASSIC_LITERATURE, SCIENCE_FICTION, FANTASY
    //•	rating – accepts number values that are positive.

    @NotNull
    @Size(min = 3, max =  40)
    private String author;
    @NotNull
    private Boolean available;
    @NotNull
    @Size(min = 5)
    private String description;
    @NotNull
    private String genre;
    @NotNull
    @Size(min = 3, max =  40)
    private String title;

    @NotNull
    @Positive
    private Double rating;

    public BookImportDto() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
