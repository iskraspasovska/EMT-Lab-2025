package mk.finki.ukim.mk.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.mk.emtlab.dto.CreateBookDto;
import mk.finki.ukim.mk.emtlab.dto.DisplayBookDto;
import mk.finki.ukim.mk.emtlab.model.enumerations.Category;
import mk.finki.ukim.mk.emtlab.service.application.BookApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(
        name= "Book API",
        description = "Endpoints for managing books"
)
public class BookController {
    private final BookApplicationService bookApplicationService;

    public BookController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @Operation(
            summary = "Get all books",
            description = "Retrieves a list of all available books, optionally filtered by category."
    )
    @GetMapping
    public ResponseEntity<List<DisplayBookDto>> findAll(@RequestParam(required = false) Category category) {
        List<DisplayBookDto> books = (category != null) ? bookApplicationService.findByCategory(category) : bookApplicationService.findAll();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);

    }

    @Operation(
            summary = "Get book by id",
            description = "Retrieves a single book by its id."
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create a new book",
            description = "Creates a new book from the provided data."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> save (@RequestBody CreateBookDto createBookDto){
        return bookApplicationService.save(createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update an existing book",
            description = "Updates an existing book with new data."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayBookDto> update (@PathVariable Long id, @RequestBody CreateBookDto createBookDto){
        return bookApplicationService.update(id, createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(
            summary = "Delete a book",
            description = "Deletes the book with the specified id."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        if (bookApplicationService.findById(id).isPresent()) {
            bookApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

//    @PutMapping("/rent/{id}")
//    public ResponseEntity<Book> rentBook (@PathVariable Long id){
//        return bookService.markAsRented(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/return/{id}")
//    public ResponseEntity<Book> returnBook (@PathVariable Long id){
//        return bookService.markAsReturned(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

}

