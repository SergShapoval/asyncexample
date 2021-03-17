package com.example.async.asyncexample.controller;

import com.example.async.asyncexample.dto.RESTResponse;
import com.example.async.asyncexample.model.User;
import com.example.async.asyncexample.service.UserService;
import com.example.async.asyncexample.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public RESTResponse<User> getUser(@PathVariable int id) throws InterruptedException, ExecutionException {
        return new RESTResponse<>(this.userService.getUser(id).get());
    }

    @GetMapping(path = "/{id}/firstName", produces = "application/json")
    public RESTResponse<String> getUserFirstName(@PathVariable int id) throws InterruptedException, ExecutionException {
        return new RESTResponse<>(this.userService.getFirstName(id).get());
    }

    @GetMapping(path = "/{id}/secondName", produces = "application/json")
    public RESTResponse<String> getUserLastName(@PathVariable int id) throws InterruptedException, ExecutionException {
        return new RESTResponse<>(this.userService.getLastName(id).get());
    }

    @GetMapping(path = "/{id}/salary", produces = "application/json")
    public RESTResponse<Integer> getUserSalary(@PathVariable int id) throws InterruptedException, ExecutionException {
        return new RESTResponse<>(this.userService.getSalary(id).get());
    }

    @GetMapping(path = "/{id}/noreply", produces = "application/json")
    public void getUserDataVoid(@PathVariable int id) throws InterruptedException, ExecutionException {
        LOGGER.info("getUserDataVoid()->getFirstName() STARTED id: {}", id);
        CompletableFuture<String> firstNameFuture = this.userService.getFirstName(id);
        LOGGER.info("getUserDataVoid()->getLastName() id: {}", id);
        CompletableFuture<String> lastNameFuture = this.userService.getLastName(id);
        LOGGER.info("getUserDataVoid()->getSalary() id: {}", id);
        CompletableFuture<Integer> salaryFuture = this.userService.getSalary(id);
        LOGGER.info("getUserDataVoid()->CompletableFuture.allOf() id: {}", id);
        //CompletableFuture.allOf(firstNameFuture, lastNameFuture, salaryFuture).join();
        LOGGER.info("getUserDataVoid(). id: {}, First Name: {}, Last Name: {}, Salary: {}", id,
                firstNameFuture.get(), lastNameFuture.get(), salaryFuture.get());
    }

    @GetMapping(path = "/{id}/reply", produces = "application/json")
    public RESTResponse<User> getUserDataReply(@PathVariable int id, HttpServletResponse httpServletResponse) throws InterruptedException, ExecutionException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        LOGGER.info("getUserDataReply()->getFirstName() STARTED id: {}", id);
        CompletableFuture<String> firstNameFuture = this.userService.getFirstName(id);
        LOGGER.info("getUserDataReply()->getLastName() id: {}", id);
        CompletableFuture<String> lastNameFuture = this.userService.getLastName(id);
        LOGGER.info("getUserDataReply()->getSalary() id: {}", id);
        CompletableFuture<Integer> salaryFuture = this.userService.getSalary(id);
        LOGGER.info("getUserDataReply()->CompletableFuture.allOf() id: {}", id);
        //CompletableFuture.allOf(firstNameFuture, lastNameFuture, salaryFuture).join();
        LOGGER.info("getUserDataReply(). id: {}, First Name: {}, Last Name: {}, Salary: {}", id,
                firstNameFuture.get(), lastNameFuture.get(), salaryFuture.get());
        return new RESTResponse<>(new User(id, firstNameFuture.get(), lastNameFuture.get(), salaryFuture.get()));
    }
}
