package mk.finki.ukim.mk.emtlab.service.domain;

import mk.finki.ukim.mk.emtlab.model.domain.Wishlist;

public interface WishlistService {
    Wishlist addBookToWishlist(String username, Long bookId);
    Wishlist viewWishlist(String username);
    void rentWishlist(String username);
}
