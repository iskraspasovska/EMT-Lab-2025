package mk.finki.ukim.mk.emtlab.service.application;

import mk.finki.ukim.mk.emtlab.dto.DisplayCopyDto;
import mk.finki.ukim.mk.emtlab.model.domain.Copy;
import mk.finki.ukim.mk.emtlab.model.enumerations.Condition;

import java.util.Optional;

public interface CopyApplicationService {
    Optional<DisplayCopyDto> updateCondition (Long id, Condition condition);

    Optional<DisplayCopyDto> markAsRented (Long id);

    Optional<DisplayCopyDto> markAsReturned (Long id);

    void deleteCopy (Long id);

    Optional<DisplayCopyDto> findById(Long copyId);
}
