package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.User;
import ch.wintihack.jobinator.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobService jobService;

    public User createNewUser() {
        User user = new User();
        user.getProgress().setMaxAmountJobs(jobService.getJobList(user,9999).size());
        user.getProgress().setCurrentLimit(user.getProgress().getMaxAmountJobs());
        user.getProgress().setCurrentAmountJobs(user.getProgress().getMaxAmountJobs());
        return userRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Integer id) throws Exception {
        return userRepository.findById(id).orElseThrow(Exception::new);
    }
}
