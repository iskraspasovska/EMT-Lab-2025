package mk.finki.ukim.mk.emtlab.dto;

import mk.finki.ukim.mk.emtlab.model.domain.Author;
import mk.finki.ukim.mk.emtlab.model.domain.Book;
import mk.finki.ukim.mk.emtlab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDto(
        String name,
        Category category,
        Long author
) {
    public static CreateBookDto from(Book book) {
        return new CreateBookDto(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId()
        );
    }

    public static List<CreateBookDto> from(List<Book> books){
        return books.stream().map(CreateBookDto::from).collect(Collectors.toList());
    }

   public Book toBook(Author author) {
        return new Book(name, category, author);
   }
}
