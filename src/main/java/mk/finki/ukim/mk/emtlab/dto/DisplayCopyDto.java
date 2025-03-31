package mk.finki.ukim.mk.emtlab.dto;

import mk.finki.ukim.mk.emtlab.model.domain.Copy;
import mk.finki.ukim.mk.emtlab.model.enumerations.Condition;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCopyDto(
        Long id,
        Condition condition,
        Boolean available
) {
    public static DisplayCopyDto from(Copy copy) {
        return new DisplayCopyDto(
                copy.getId(),
                copy.getCondition(),
                copy.getAvailable()
        );
    }

    public static List<DisplayCopyDto> from(List<Copy> copies) {
        return copies.stream().map(DisplayCopyDto::from).collect(Collectors.toList());
    }

    public Copy toCopy(){
        return new Copy(condition, available);
    }
}
