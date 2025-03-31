package mk.finki.ukim.mk.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.mk.emtlab.dto.DisplayCopyDto;
import mk.finki.ukim.mk.emtlab.model.enumerations.Condition;
import mk.finki.ukim.mk.emtlab.service.application.CopyApplicationService;
import mk.finki.ukim.mk.emtlab.service.domain.CopyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/copies")
@Tag(
        name= "Copy API",
        description = "Endpoints for managing book copies"
)
public class CopyController {
    private final CopyApplicationService copyApplicationService;

    public CopyController(CopyService copyService, CopyApplicationService copyApplicationService) {
        this.copyApplicationService = copyApplicationService;
    }

    @Operation(
            summary = "Mark copy as rented",
            description = "Marks the specified copy as rented using its ID."
    )
    @PutMapping("/rent/{copyId}")
    public ResponseEntity<DisplayCopyDto> markAsRented(@PathVariable Long copyId) {
        return copyApplicationService.markAsRented(copyId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Mark copy as returned",
            description = "Marks the specified copy as returned using its ID."
    )
    @PutMapping("/return/{copyId}")
    public ResponseEntity<DisplayCopyDto> markAsReturned( @PathVariable Long copyId) {
        return copyApplicationService.markAsReturned(copyId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a copy",
            description = "Deletes the specified copy by its ID."
    )
    @DeleteMapping("/delete/{copyId}")
    public ResponseEntity<Void> deleteCopy(@PathVariable Long copyId) {
        if (copyApplicationService.findById(copyId).isPresent()) {
            copyApplicationService.deleteCopy(copyId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Update copy condition",
            description = "Updates the condition of the specified copy using its ID and a provided condition parameter."
    )
    @PutMapping("/updateCondition/{copyId}")
    public ResponseEntity<DisplayCopyDto> updateCondition(@PathVariable Long copyId, @RequestParam Condition condition){
        return copyApplicationService.updateCondition(copyId, condition)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
