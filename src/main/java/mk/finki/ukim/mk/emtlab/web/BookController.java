package mk.finki.ukim.mk.emtlab.web;

import mk.finki.ukim.mk.emtlab.model.Book;
import mk.finki.ukim.mk.emtlab.model.Category;
import mk.finki.ukim.mk.emtlab.model.Condition;
import mk.finki.ukim.mk.emtlab.model.Copy;
import mk.finki.ukim.mk.emtlab.model.dto.BookDto;
import mk.finki.ukim.mk.emtlab.service.BookService;
import mk.finki.ukim.mk.emtlab.service.CopyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final CopyService copyService;

    public BookController(BookService bookService, CopyService copyService) {
        this.bookService = bookService;
        this.copyService = copyService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll(@RequestParam(required = false) Category category) {
        List<Book> books = (category != null) ? bookService.findByCategory(category) : bookService.findAll();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return bookService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save (@RequestBody BookDto book){
        return bookService.save(book).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> update (@PathVariable Long id, @RequestBody BookDto book){
        return bookService.update(id, book)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            bookService.deleteById(id);
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
    @PutMapping("/copy/rent/{copyId}")
    public ResponseEntity<Copy> markAsRented( @PathVariable Long copyId) {
        return copyService.markAsRented(copyId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/copy/return/{copyId}")
    public ResponseEntity<Copy> markAsReturned( @PathVariable Long copyId) {
        return copyService.markAsReturned(copyId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/copy/delete/{copyId}")
    public ResponseEntity<Void> deleteCopy(@PathVariable Long copyId) {
        if (copyService.findById(copyId).isPresent()) {
            copyService.deleteCopy(copyId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/copy/updateCondition/{copyId}")
    public ResponseEntity<Copy> updateCondition(@PathVariable Long copyId, @RequestParam Condition condition){
        return copyService.updateCondition(copyId, condition)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}

