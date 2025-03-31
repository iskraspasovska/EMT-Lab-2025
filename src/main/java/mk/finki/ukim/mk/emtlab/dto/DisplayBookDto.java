package mk.finki.ukim.mk.emtlab.dto;

import mk.finki.ukim.mk.emtlab.model.domain.Book;
import mk.finki.ukim.mk.emtlab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(
        Long id,
        String name,
        Category category,
        Long author,
        List<DisplayCopyDto> copies
) {

    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getCopies()
                        .stream()
                        .map(DisplayCopyDto::from)
                        .collect(Collectors.toList())
        );
    }

    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }

//    public Book toBook(Author author) {
//        return new Book(name, category, author, copies);
//    }

}
