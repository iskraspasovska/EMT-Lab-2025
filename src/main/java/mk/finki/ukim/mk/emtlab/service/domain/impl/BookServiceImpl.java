package mk.finki.ukim.mk.emtlab.service.domain.impl;

import mk.finki.ukim.mk.emtlab.model.domain.Book;
import mk.finki.ukim.mk.emtlab.model.enumerations.Category;
import mk.finki.ukim.mk.emtlab.dto.BookDto;
import mk.finki.ukim.mk.emtlab.repository.AuthorRepository;
import mk.finki.ukim.mk.emtlab.repository.BookRepository;
import mk.finki.ukim.mk.emtlab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public Optional<Book> save(Book book) {
        return Optional.of(bookRepository.save(new Book(
                book.getName(),
                book.getCategory(),
                authorRepository.findById(book.getAuthor().getId())
                        .get())));
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    if (book.getName() != null) {
                        existingBook.setName(book.getName());
                    }
                    if (book.getCategory() != null) {
                        existingBook.setCategory(book.getCategory());
                    }
                    if (book.getAuthor() != null) {
                        existingBook.setAuthor(authorRepository.findById(book.getAuthor().getId()).get());
                    }
                    return bookRepository.save(existingBook);
                });
    }
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

//    @Override
//    public Optional<Book> markAsRented(Long id) {
//        return bookRepository.findById(id).map(book -> {
//            if (book.getAvailableCopies() > 0) {
//                book.setAvailableCopies(book.getAvailableCopies() - 1);
//            }
//            return bookRepository.save(book);
//        });
//    }
//
//    @Override
//    public Optional<Book> markAsReturned(Long id) {
//        return bookRepository.findById(id).map(book -> {
//            book.setAvailableCopies(book.getAvailableCopies() + 1);
//            return bookRepository.save(book);
//        });
//    }

    @Override
    public List<Book> findByCategory(Category category) {
        return bookRepository.findAllByCategory(category);
    }


}
