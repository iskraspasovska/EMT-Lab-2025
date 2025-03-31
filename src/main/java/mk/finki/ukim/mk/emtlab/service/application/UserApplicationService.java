package mk.finki.ukim.mk.emtlab.service.application;

import mk.finki.ukim.mk.emtlab.dto.CreateUserDto;
import mk.finki.ukim.mk.emtlab.dto.DisplayUserDto;
import mk.finki.ukim.mk.emtlab.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
