package mk.finki.ukim.mk.emtlab.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.emtlab.model.domain.*;
import mk.finki.ukim.mk.emtlab.model.enumerations.Category;
import mk.finki.ukim.mk.emtlab.model.enumerations.Condition;
import mk.finki.ukim.mk.emtlab.model.enumerations.Role;
import mk.finki.ukim.mk.emtlab.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final CopyRepository copyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository, CopyRepository copyRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.copyRepository = copyRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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


        userRepository.save(new User(
                "is",
                passwordEncoder.encode("at"),
                "Iskra",
                "Spasovska",
                Role.ROLE_LIBRARIAN
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));


    }
}
