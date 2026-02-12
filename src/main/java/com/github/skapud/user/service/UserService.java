package com.github.skapud.user.service;

import com.github.skapud.user.model.User;
import com.github.skapud.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.github.skapud.app.config.SecurityConfig.PASSWORD_ENCODER;

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
