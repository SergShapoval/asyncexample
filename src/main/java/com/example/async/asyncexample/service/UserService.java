package com.example.async.asyncexample.service;

import com.example.async.asyncexample.model.User;

import java.util.concurrent.CompletableFuture;

public interface UserService {
    CompletableFuture<User> getUser(int id) throws InterruptedException;

    CompletableFuture<String> getFirstName(int id) throws InterruptedException;

    CompletableFuture<String> getLastName(int id) throws InterruptedException;

    CompletableFuture<Integer> getSalary(int id) throws InterruptedException;
}
