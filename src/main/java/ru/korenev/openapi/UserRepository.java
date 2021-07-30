package ru.korenev.openapi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.korenev.openapi.model.User;

import javax.persistence.Id;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
}
