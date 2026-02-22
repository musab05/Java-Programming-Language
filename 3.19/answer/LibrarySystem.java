import java.util.Optional;

class Website {
    private String url;
    public Website(String url) { this.url = url; }
    public String getUrl() { return url; }
}

class Author {
    private Website website;
    public Author(Website website) { this.website = website; }
    // Returns Optional because an author might not have a site
    public Optional<Website> getWebsite() { return Optional.ofNullable(website); }
}

class Book {
    private Author author;
    public Book(Author author) { this.author = author; }
    // Returns Optional because a book might be anonymous
    public Optional<Author> getAuthor() { return Optional.ofNullable(author); }
}

public class LibrarySystem {
    public static String getAuthorUrl(Book book) {
        return Optional.ofNullable(book)
            .flatMap(Book::getAuthor)    // Flattens Optional<Author>
            .flatMap(Author::getWebsite) // Flattens Optional<Website>
            .map(Website::getUrl)        // Returns the plain String inside the box
            .orElse("URL not available");
    }

    public static void main(String[] args) {
        // Test Case 1: Full data
        Book book1 = new Book(new Author(new Website("https://java.com")));
        System.out.println("Book 1: " + getAuthorUrl(book1));

        // Test Case 2: Missing Website
        Book book2 = new Book(new Author(null));
        System.out.println("Book 2: " + getAuthorUrl(book2));

        // Test Case 3: Missing Author
        Book book3 = new Book(null);
        System.out.println("Book 3: " + getAuthorUrl(book3));
    }
}