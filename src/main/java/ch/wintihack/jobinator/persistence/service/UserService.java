package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.User;
import ch.wintihack.jobinator.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createNewUser() {
        return userRepository.save(new User());
    }
}
