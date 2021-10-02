package pl.switix.springjwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.switix.springjwt.service.AppUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    AppUserService appUserService;

    @Autowired
    public UserDetailsServiceImpl(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserService.getAppUserByUsername(s).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
