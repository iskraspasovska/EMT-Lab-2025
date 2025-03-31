package mk.finki.ukim.mk.emtlab.dto;

import mk.finki.ukim.mk.emtlab.model.domain.Author;
import mk.finki.ukim.mk.emtlab.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorDto(
        Long id,
        String name,
        String surname,
        Long country
) {
    public static DisplayAuthorDto from(Author author) {
        return new DisplayAuthorDto(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }

    public static List<DisplayAuthorDto> from(List<Author> authors) {
        return authors.stream().map(DisplayAuthorDto::from).collect(Collectors.toList());
    }

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}
