package mk.finki.ukim.mk.emtlab.service.impl;

import mk.finki.ukim.mk.emtlab.model.Book;
import mk.finki.ukim.mk.emtlab.model.Category;
import mk.finki.ukim.mk.emtlab.model.dto.BookDto;
import mk.finki.ukim.mk.emtlab.repository.AuthorRepository;
import mk.finki.ukim.mk.emtlab.repository.BookRepository;
import mk.finki.ukim.mk.emtlab.service.BookService;
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
    public Optional<Book> save(BookDto bookDto) {
        return Optional.of(bookRepository.save(new Book(bookDto.getName(), bookDto.getCategory(), authorRepository.findById(bookDto.getAuthor()).get(), bookDto.getCopies())));
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    if (bookDto.getName() != null) {
                        existingBook.setName(bookDto.getName());
                    }
                    if (bookDto.getCategory() != null) {
                        existingBook.setCategory(bookDto.getCategory());
                    }
                    if (bookDto.getAuthor() != null) {
                        existingBook.setAuthor(authorRepository.findById(bookDto.getAuthor()).get());
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
