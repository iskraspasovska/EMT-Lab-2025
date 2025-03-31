package mk.finki.ukim.mk.emtlab.repository;

import mk.finki.ukim.mk.emtlab.model.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    Optional<Wishlist> findByUserUsername(String username);

}
