package mk.finki.ukim.mk.emtlab.service.application.impl;

import mk.finki.ukim.mk.emtlab.dto.DisplayCopyDto;
import mk.finki.ukim.mk.emtlab.model.enumerations.Condition;
import mk.finki.ukim.mk.emtlab.service.application.CopyApplicationService;
import mk.finki.ukim.mk.emtlab.service.domain.CopyService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CopyApplicationServiceImpl implements CopyApplicationService {

    private final CopyService copyService;

    public CopyApplicationServiceImpl(CopyService copyService) {
        this.copyService = copyService;
    }

    @Override
    public Optional<DisplayCopyDto> updateCondition(Long id, Condition condition) {
        return copyService.updateCondition(id, condition).map(DisplayCopyDto::from);
    }

    @Override
    public Optional<DisplayCopyDto> markAsRented(Long id) {
        return copyService.markAsRented(id).map(DisplayCopyDto::from);
    }

    @Override
    public Optional<DisplayCopyDto> markAsReturned(Long id) {
        return copyService.markAsReturned(id).map(DisplayCopyDto::from);
    }

    @Override
    public void deleteCopy(Long id) {
        copyService.deleteCopy(id);
    }

    @Override
    public Optional<DisplayCopyDto> findById(Long copyId) {
        return copyService.findById(copyId).map(DisplayCopyDto::from);
    }
}
