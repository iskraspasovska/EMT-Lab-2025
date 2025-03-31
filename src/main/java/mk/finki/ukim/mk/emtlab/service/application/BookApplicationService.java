package mk.finki.ukim.mk.emtlab.service.application;

import mk.finki.ukim.mk.emtlab.dto.CreateBookDto;
import mk.finki.ukim.mk.emtlab.dto.DisplayBookDto;
import mk.finki.ukim.mk.emtlab.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    Optional<DisplayBookDto> save(CreateBookDto createBookDto);

    Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto);

    void deleteById(Long id);

    List<DisplayBookDto> findAll();

    Optional<DisplayBookDto> findById(Long id);

//    Optional<Book> markAsRented(Long id);
//
//    Optional<Book> markAsReturned(Long id);

    List<DisplayBookDto> findByCategory(Category category);
}
