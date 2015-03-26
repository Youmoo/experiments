package cache;

public interface BookRepository {

    Book getByIsbn(String isbn);

}
