package mk.finki.ukim.mk.emtlab.service.application;

import mk.finki.ukim.mk.emtlab.dto.DisplayWishlistDto;

public interface WishlistApplicationService {
    DisplayWishlistDto addBookToWishlist(String username, Long bookId);
    DisplayWishlistDto viewWishlist(String username);
    void rentWishlist(String username);
}
