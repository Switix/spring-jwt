package pl.switix.springjwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.switix.springjwt.entity.AppUser;
import pl.switix.springjwt.repository.AppUserRepository;
import pl.switix.springjwt.service.AppUserService;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Optional<AppUser> getAppUserByUsername(String username) {
        return appUserRepository.findAppUserByUsername(username);
    }

    @Override
    public void save(AppUser appUser) {
        appUserRepository.save(appUser);
    }
}
