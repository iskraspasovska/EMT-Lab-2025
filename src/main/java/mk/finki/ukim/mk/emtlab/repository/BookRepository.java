package mk.finki.ukim.mk.emtlab.repository;

import mk.finki.ukim.mk.emtlab.model.Book;
import mk.finki.ukim.mk.emtlab.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> findAllByCategory(Category category);
}
