package mk.finki.ukim.mk.emtlab.service.application.impl;

import mk.finki.ukim.mk.emtlab.dto.CreateBookDto;
import mk.finki.ukim.mk.emtlab.dto.DisplayBookDto;
import mk.finki.ukim.mk.emtlab.model.domain.Author;
import mk.finki.ukim.mk.emtlab.model.enumerations.Category;
import mk.finki.ukim.mk.emtlab.repository.AuthorRepository;
import mk.finki.ukim.mk.emtlab.service.application.BookApplicationService;
import mk.finki.ukim.mk.emtlab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorRepository authorRepository;

    public BookApplicationServiceImpl(BookService bookService, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto createBookDto) {
        Author author = authorRepository.findById(createBookDto.author()).get();
        return bookService.save(createBookDto.toBook(author))
                .map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        Author author = authorRepository.findById(createBookDto.author()).get();
        return bookService.update(id, createBookDto.toBook(author)).map(DisplayBookDto::from);
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return bookService.findAll().stream().map(DisplayBookDto::from).toList();
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public List<DisplayBookDto> findByCategory(Category category) {
        return bookService.findByCategory(category).stream().map(DisplayBookDto::from).toList();
    }
}
