package mk.finki.ukim.mk.emtlab.dto;

import mk.finki.ukim.mk.emtlab.model.domain.Wishlist;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayWishlistDto(
        Long id,
        String username,
        List<DisplayBookDto> books
) {
    public static DisplayWishlistDto from(Wishlist wishlist) {
        return new DisplayWishlistDto(
                wishlist.getId(),
                wishlist.getUser().getUsername(),
                wishlist.getBooks().stream()
                        .map(DisplayBookDto::from)
                        .collect(Collectors.toList())
        );
    }
}
