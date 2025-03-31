package mk.finki.ukim.mk.emtlab.service.domain;

import mk.finki.ukim.mk.emtlab.model.enumerations.Condition;
import mk.finki.ukim.mk.emtlab.model.domain.Copy;

import java.util.Optional;

public interface CopyService {
    Optional<Copy> updateCondition (Long id, Condition condition);

    Optional<Copy> markAsRented (Long id);

    Optional<Copy> markAsReturned (Long id);

    void deleteCopy (Long id);

    Optional<Copy> findById(Long copyId);
}
