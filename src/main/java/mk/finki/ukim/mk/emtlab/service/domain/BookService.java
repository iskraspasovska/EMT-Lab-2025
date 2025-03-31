package mk.finki.ukim.mk.emtlab.service.domain;

import mk.finki.ukim.mk.emtlab.model.domain.Book;
import mk.finki.ukim.mk.emtlab.model.enumerations.Category;
import mk.finki.ukim.mk.emtlab.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> save(Book book);

    Optional<Book> update(Long id, Book book);

    void deleteById(Long id);

    List<Book> findAll();

    Optional<Book> findById(Long id);

//    Optional<Book> markAsRented(Long id);
//
//    Optional<Book> markAsReturned(Long id);

    List<Book> findByCategory(Category category);
}
