public class Book {

    public String title;

    public String getTitle() {
	return this.title;
    }

    public Book(String title) {
	this.title = title;
    }

    public String toString() {
	return title;
    }
    
    private static java.util.Comparator<Book> byTitle =
	java.util.Comparator.comparing(Book::getTitle);
    //java.util.Comparator.comparing(b -> b.getTitle());
    
    public static void main(String [] args) {
	java.util.ArrayList<Book> books =
	    new java.util.ArrayList<Book>();
	books.add(new Book("Moby Dick"));
	books.add(new Book("War and Peace"));
	books.add(new Book("The Hunger Games"));
	books.add(new Book("Twilight"));

	java.util.Collections.sort(books,byTitle);
	System.out.println(books);
    }
    

}
