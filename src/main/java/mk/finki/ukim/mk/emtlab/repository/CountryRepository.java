package mk.finki.ukim.mk.emtlab.repository;

import mk.finki.ukim.mk.emtlab.model.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    public Country findByName(String name);
}
