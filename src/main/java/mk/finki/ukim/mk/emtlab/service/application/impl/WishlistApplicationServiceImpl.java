package mk.finki.ukim.mk.emtlab.service.application.impl;


import mk.finki.ukim.mk.emtlab.dto.DisplayWishlistDto;
import mk.finki.ukim.mk.emtlab.model.domain.Wishlist;
import mk.finki.ukim.mk.emtlab.service.application.WishlistApplicationService;
import mk.finki.ukim.mk.emtlab.service.domain.WishlistService;
import org.springframework.stereotype.Service;

@Service
public class WishlistApplicationServiceImpl implements WishlistApplicationService {

    private final WishlistService wishlistService;

    public WishlistApplicationServiceImpl(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public DisplayWishlistDto addBookToWishlist(String username, Long bookId) {
        Wishlist wishlist = wishlistService.addBookToWishlist(username, bookId);
        return DisplayWishlistDto.from(wishlist);
    }

    @Override
    public DisplayWishlistDto viewWishlist(String username) {
        Wishlist wishlist = wishlistService.viewWishlist(username);
        return DisplayWishlistDto.from(wishlist);
    }

    @Override
    public void rentWishlist(String username) {
        wishlistService.rentWishlist(username);
    }
}
