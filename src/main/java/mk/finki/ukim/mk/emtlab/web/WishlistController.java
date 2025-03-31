package mk.finki.ukim.mk.emtlab.web;

import mk.finki.ukim.mk.emtlab.dto.CreateWishlistDto;
import mk.finki.ukim.mk.emtlab.dto.DisplayWishlistDto;
import mk.finki.ukim.mk.emtlab.service.application.WishlistApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistApplicationService wishlistApplicationService;

    public WishlistController(WishlistApplicationService wishlistApplicationService) {
        this.wishlistApplicationService = wishlistApplicationService;
    }

    // Endpoint to add a book to the wishlist.
    @PostMapping("/add")
    public ResponseEntity<String> addBookToWishlist(@RequestBody CreateWishlistDto dto) {
        try {
            DisplayWishlistDto wishlistDto = wishlistApplicationService.addBookToWishlist(dto.username(), dto.bookId());
            return ResponseEntity.ok("Book added to wishlist. Wishlist ID: " + wishlistDto.id());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to view a user's wishlist by username.
    @GetMapping("/user/{username}")
    public ResponseEntity<DisplayWishlistDto> viewWishlist(@PathVariable String username) {
        try {
            DisplayWishlistDto wishlistDto = wishlistApplicationService.viewWishlist(username);
            return ResponseEntity.ok(wishlistDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to rent all books in the user's wishlist.
    @PostMapping("/rent/{username}")
    public ResponseEntity<String> rentWishlist(@PathVariable String username) {
        try {
            wishlistApplicationService.rentWishlist(username);
            return ResponseEntity.ok("Wishlist processed for renting");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
