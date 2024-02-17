package org.example.seminar5task2.service;

import lombok.AllArgsConstructor;
import org.example.seminar5task2.model.Book;
import org.example.seminar5task2.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    /**
     * Получить все Книги
     *
     * @return
     */
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Optional<Book> getBookByID(Long id) {
        return repository.findById(id);
    }

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPublicationYear(bookDetails.getPublicationYear());
            return repository.save(book);
        } else {
            throw new IllegalArgumentException("Book not found with id: " + id);
        }
    }

    public void deleteBook(long id) {
        repository.deleteById(id);
    }

}
