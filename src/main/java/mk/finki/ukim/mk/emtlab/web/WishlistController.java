package mk.finki.ukim.mk.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.mk.emtlab.dto.CreateWishlistDto;
import mk.finki.ukim.mk.emtlab.dto.DisplayWishlistDto;
import mk.finki.ukim.mk.emtlab.service.application.WishlistApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
@Tag(
        name = "Wishlist API",
        description = "Endpoints for managing wishlist functionality"
)

public class WishlistController {

    private final WishlistApplicationService wishlistApplicationService;

    public WishlistController(WishlistApplicationService wishlistApplicationService) {
        this.wishlistApplicationService = wishlistApplicationService;
    }

    @PostMapping("/add")
    @Operation(
            summary = "Add a book to wishlist",
            description = "Adds a specified book to the user's wishlist if at least one available copy exists."
    )

    public ResponseEntity<String> addBookToWishlist(@RequestBody CreateWishlistDto dto) {
        try {
            DisplayWishlistDto wishlistDto = wishlistApplicationService.addBookToWishlist(dto.username(), dto.bookId());
            return ResponseEntity.ok("Book added to wishlist. Wishlist ID: " + wishlistDto.id());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "View wishlist",
            description = "Retrieves the wishlist for a given user based on username."
    )
    @GetMapping("/user/{username}")
    public ResponseEntity<DisplayWishlistDto> viewWishlist(@PathVariable String username) {
        try {
            DisplayWishlistDto wishlistDto = wishlistApplicationService.viewWishlist(username);
            return ResponseEntity.ok(wishlistDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Rent wishlist",
            description = "Processes the wishlist for the specified user by renting available copies for each book and then clearing the wishlist."
    )
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
