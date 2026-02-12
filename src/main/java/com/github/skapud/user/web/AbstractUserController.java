package com.github.skapud.user.web;

import com.github.skapud.user.model.User;
import com.github.skapud.user.repository.UserRepository;
import com.github.skapud.user.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractUserController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected UserRepository repository;

    @Autowired
    protected UserService service;

    @Autowired
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    public User get(int id) {
        log.info("get {}", id);
        return repository.getExisted(id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.deleteExisted(id);
    }
}