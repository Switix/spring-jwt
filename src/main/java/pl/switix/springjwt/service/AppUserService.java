package pl.switix.springjwt.service;

import pl.switix.springjwt.entity.AppUser;

import java.util.Optional;

public interface AppUserService {

    Optional<AppUser> getAppUserByUsername(String username);
    void save(AppUser appUser);

}
