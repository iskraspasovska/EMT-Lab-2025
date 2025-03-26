package mk.finki.ukim.mk.emtlab.service.impl;

import mk.finki.ukim.mk.emtlab.model.Condition;
import mk.finki.ukim.mk.emtlab.model.Copy;
import mk.finki.ukim.mk.emtlab.repository.CopyRepository;
import mk.finki.ukim.mk.emtlab.service.CopyService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CopyServiceImpl implements CopyService {
    private final CopyRepository copyRepository;

    public CopyServiceImpl(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }


    @Override
    public Optional<Copy> updateCondition(Long id, Condition condition) {
        return copyRepository.findById(id)
                .map(existingCopy -> {
                    if (condition != null) {
                        existingCopy.setCondition(condition);
                    }
                    return copyRepository.save(existingCopy);
                });
    }

    @Override
    public Optional<Copy> markAsRented(Long id) {
        return copyRepository.findById(id)
                .map(existingCopy -> {
                    if (existingCopy.getAvailable()) {
                        existingCopy.setAvailable(false);
                    }
                    return copyRepository.save(existingCopy);
                });

    }

    @Override
    public Optional<Copy> markAsReturned(Long id) {
        return copyRepository.findById(id)
                .map(existingCopy -> {
                    if (!existingCopy.getAvailable()) {
                        existingCopy.setAvailable(true);
                    }
                    return copyRepository.save(existingCopy);
                });
    }

    @Override
    public void deleteCopy(Long id) {
        copyRepository.deleteById(id);
    }

    @Override
    public Optional<Copy> findById(Long copyId) {
        return copyRepository.findById(copyId);
    }
}
