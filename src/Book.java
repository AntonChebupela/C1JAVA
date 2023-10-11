
public class Book {
    private String title;
    private String author;
    private String publicationDate;
    private String genre;

    public Book(String title, String author, String publicationDate, String genre) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.genre = genre;
    }

    // Геттеры для доступа к полям книги
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Название: " + title +
                ", Автор: " + author +
                ", Дата написания: " + publicationDate +
                ", Жанр: " + genre;
    }
}