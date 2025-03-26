package mk.finki.ukim.mk.emtlab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.emtlab.model.*;
import mk.finki.ukim.mk.emtlab.repository.AuthorRepository;
import mk.finki.ukim.mk.emtlab.repository.BookRepository;
import mk.finki.ukim.mk.emtlab.repository.CopyRepository;
import mk.finki.ukim.mk.emtlab.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final CopyRepository copyRepository;

    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository, CopyRepository copyRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.copyRepository = copyRepository;
    }

    @PostConstruct
    public void init() {
        List<Copy> copies1 = new ArrayList<>();
        List<Copy> copies2 = new ArrayList<>();
        List<Copy> copies3 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            copies1.add(copyRepository.save(new Copy(Condition.NEW, true)));
            copies2.add(copyRepository.save(new Copy(Condition.NEW, true)));
            copies3.add(copyRepository.save(new Copy(Condition.NEW, true)));
        }

        countryRepository.save(new Country("UK", "Europe"));
        countryRepository.save(new Country("USA", "North America"));
        countryRepository.save(new Country("Russia", "Asia"));

        authorRepository.save(new Author("J.K.", "Rowling", this.countryRepository.findByName("UK")));
        authorRepository.save(new Author("Dan", "Brown", this.countryRepository.findByName("USA")));
        authorRepository.save(new Author("Leo", "Tolstoy", this.countryRepository.findByName("Russia")));

        bookRepository.save(new Book("Harry Potter and the Sorcerer's Stone", Category.FANTASY, authorRepository.findByName("J.K."), copies1));
        bookRepository.save(new Book("The Da Vinci Code", Category.THRILLER, authorRepository.findByName("Dan"), copies2));
        bookRepository.save(new Book("War and Peace", Category.CLASSICS, authorRepository.findByName("Leo"), copies3));
    }
}
