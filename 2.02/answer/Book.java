public class Book {
    // 1. Fields
    String title;
    String author;
    int year;

    // 2. Constructor for all three parameters
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // 3. Overloaded constructor for missing year
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.year = -1; // Fallback value
    }

    public static void main(String[] args) {
        // 4. Test both constructors
        Book completeBook = new Book("The Martian", "Andy Weir", 2011);
        Book olderBook = new Book("Epic of Gilgamesh", "Unknown");

        System.out.println(completeBook.title + " published in: " + completeBook.year);
        System.out.println(olderBook.title + " published in: " + olderBook.year);
    }
}