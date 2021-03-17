package com.example.async.asyncexample.service.impl;

import com.example.async.asyncexample.dao.UserDAO;
import com.example.async.asyncexample.model.User;
import com.example.async.asyncexample.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final Long SLEEP_TIME_MILLIS = 5000L;

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<User> getUser(int id) throws InterruptedException {
        LOGGER.info("getUser() id: {} started", id);
        Thread.sleep(SLEEP_TIME_MILLIS);
        LOGGER.info("getUser() id: {} finished", id);
        return CompletableFuture.completedFuture(this.userDAO.getUserByID(id));
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<String> getFirstName(int id) throws InterruptedException {
        LOGGER.info("getFirstName() id: {} started", id);
        Thread.sleep(SLEEP_TIME_MILLIS);
        LOGGER.info("getFirstName() id: {} finished", id);
        return CompletableFuture.completedFuture(this.userDAO.getFirstNameByID(id));
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<String> getLastName(int id) throws InterruptedException {
        LOGGER.info("getLastName() id: {} started", id);
        Thread.sleep(1000);
        LOGGER.info("getLastName() id: {} finished", id);
        return CompletableFuture.completedFuture(this.userDAO.getLastNameByID(id));
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Integer> getSalary(int id) throws InterruptedException {
        LOGGER.info("getSalary() id: {} started", id);
        Thread.sleep(3000);
        LOGGER.info("getSalary() id: {} finished", id);
        return CompletableFuture.completedFuture(this.userDAO.getSalaryByID(id));
    }
}
