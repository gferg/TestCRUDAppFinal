package app.repository;

import app.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer>, PagingAndSortingRepository<User,Integer> {

    List<User> findByNameStartsWithIgnoreCase(String name);

    List<User> findAll();

}
