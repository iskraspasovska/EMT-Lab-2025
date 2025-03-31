package mk.finki.ukim.mk.emtlab.dto;

import mk.finki.ukim.mk.emtlab.model.domain.Copy;
import mk.finki.ukim.mk.emtlab.model.enumerations.Condition;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCopyDto(
    Condition condition,
    Boolean available
) {
    public static CreateCopyDto from(Copy copy) {
        return new CreateCopyDto(
                copy.getCondition(),
                copy.getAvailable()
        );
    }

    public static List<CreateCopyDto> from(List<Copy> copies) {
        return copies.stream().map(CreateCopyDto::from).collect(Collectors.toList());
    }

    public Copy toCopy(){
        return new Copy(condition, available);
    }
}
