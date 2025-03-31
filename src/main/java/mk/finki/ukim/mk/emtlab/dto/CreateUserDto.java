package mk.finki.ukim.mk.emtlab.dto;

import mk.finki.ukim.mk.emtlab.model.domain.User;
import mk.finki.ukim.mk.emtlab.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    public User toUser() {
//        if (!password.equals(repeatPassword)) {
//            throw new PasswordsDoNotMatchException();
//        }
        return new User(username, password, name, surname, role);
    }
}

