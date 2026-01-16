package ru.javaops.topjava.user.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.user.model.User;
import ru.javaops.topjava.user.repository.UserRepository;

import java.util.Optional;

import static ru.javaops.topjava.app.config.SecurityConfig.PASSWORD_ENCODER;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Cacheable("users")
    public Optional<User> findByEmailIgnoreCase(String email) {
        return repository.findByEmailIgnoreCase(email);
    }

    @Transactional
    @CacheEvict(value = {"users"}, allEntries = true)
    public User prepareAndSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return repository.save(user);
    }

    @CacheEvict(value = {"users"}, allEntries = true)
    public void deleteExisted(int id) {
        repository.deleteExisted(id);
    }

    @Transactional
    @CacheEvict(value = {"users"}, allEntries = true)
    public void enable(int id, boolean enabled) {
        User user = repository.getExisted(id);
        user.setEnabled(enabled);
    }
}
