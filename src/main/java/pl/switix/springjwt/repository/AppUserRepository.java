package pl.switix.springjwt.repository;

import org.springframework.data.repository.CrudRepository;
import pl.switix.springjwt.entity.AppUser;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser,Long> {

    Optional<AppUser> findAppUserByUsername(String username);
}
