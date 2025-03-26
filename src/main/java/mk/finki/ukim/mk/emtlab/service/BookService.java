package mk.finki.ukim.mk.emtlab.service;

import mk.finki.ukim.mk.emtlab.model.Book;
import mk.finki.ukim.mk.emtlab.model.Category;
import mk.finki.ukim.mk.emtlab.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> save(BookDto bookDto);

    Optional<Book> update(Long id, BookDto bookDto);

    void deleteById(Long id);

    List<Book> findAll();

    Optional<Book> findById(Long id);

//    Optional<Book> markAsRented(Long id);
//
//    Optional<Book> markAsReturned(Long id);

    List<Book> findByCategory(Category category);
}
