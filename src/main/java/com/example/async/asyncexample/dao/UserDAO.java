package com.example.async.asyncexample.dao;

import com.example.async.asyncexample.model.User;

public interface UserDAO {
    User getUserByID(int id);

    String getFirstNameByID(int id);

    String getLastNameByID(int id);

    int getSalaryByID(int id);
}
