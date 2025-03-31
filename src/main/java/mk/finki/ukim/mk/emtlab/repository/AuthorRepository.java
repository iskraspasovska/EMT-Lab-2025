package mk.finki.ukim.mk.emtlab.repository;

import mk.finki.ukim.mk.emtlab.model.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Author findByName(String name);
}
