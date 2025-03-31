package mk.finki.ukim.mk.emtlab.service.domain.impl;

import mk.finki.ukim.mk.emtlab.model.domain.Book;
import mk.finki.ukim.mk.emtlab.model.domain.Copy;
import mk.finki.ukim.mk.emtlab.model.domain.User;
import mk.finki.ukim.mk.emtlab.model.domain.Wishlist;
import mk.finki.ukim.mk.emtlab.model.exceptions.NoAvailableCopiesException;
import mk.finki.ukim.mk.emtlab.repository.BookRepository;
import mk.finki.ukim.mk.emtlab.repository.CopyRepository;
import mk.finki.ukim.mk.emtlab.repository.UserRepository;
import mk.finki.ukim.mk.emtlab.repository.WishlistRepository;
import mk.finki.ukim.mk.emtlab.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository,
                               UserRepository userRepository,
                               BookRepository bookRepository,
                               CopyRepository copyRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.copyRepository = copyRepository;
    }

    @Override
    public Wishlist addBookToWishlist(String username, Long bookId) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if the book has at least one available copy
        boolean hasAvailableCopy = book.getCopies().stream().anyMatch(Copy::getAvailable);
        if (!hasAvailableCopy) {
            throw new NoAvailableCopiesException("This book has no available copies.");
        }

        // Find existing wishlist for the user or create a new one
        Wishlist wishlist = wishlistRepository.findByUserUsername(username).orElse(new Wishlist(user));
        wishlist.getBooks().add(book);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist viewWishlist(String username) {
        return wishlistRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user"));
    }

    @Override
    public void rentWishlist(String username) {
        Wishlist wishlist = wishlistRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user"));
        for (Book book : wishlist.getBooks()) {
            Optional<Copy> availableCopy = book.getCopies().stream()
                    .filter(Copy::getAvailable)
                    .findFirst();
            if (availableCopy.isPresent()) {
                Copy copy = availableCopy.get();
                // Mark the copy as rented (i.e. not available)
                copy.setAvailable(false);
                copyRepository.save(copy);
            }
        }
        // Clear the wishlist after processing
        wishlist.getBooks().clear();
        wishlistRepository.save(wishlist);
    }
}
