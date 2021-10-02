package pl.switix.springjwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.switix.springjwt.entity.AppUser;
import pl.switix.springjwt.service.AppUserService;

@Configuration
public class Start {

    public Start(AppUserService appUserService, PasswordEncoder passwordEncoder){
        AppUser appUser1 = new AppUser();
        appUser1.setUsername("Maciej");
        appUser1.setPassword(passwordEncoder.encode("Maciej123"));
        appUser1.setRole("ROLE_ADMIN");


        AppUser appUser2 = new AppUser();
        appUser2.setUsername("Adam");
        appUser2.setPassword(passwordEncoder.encode("Adam123"));
        appUser2.setRole("ROLE_USER");

        appUserService.save(appUser1);
        appUserService.save(appUser2);
    }
}
