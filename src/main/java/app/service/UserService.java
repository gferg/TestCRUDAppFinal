package app.service;

import app.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> listAllByPage(Pageable pageable);
}
