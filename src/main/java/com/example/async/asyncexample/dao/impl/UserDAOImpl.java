package com.example.async.asyncexample.dao.impl;

import com.example.async.asyncexample.dao.UserDAO;
import com.example.async.asyncexample.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDAOImpl implements UserDAO {

    private Map<Integer, User> storageMap = getStorageMap(1_000_000);

    private Map<Integer, User> getStorageMap(int quantityOfUsersToGenerate) {
        Map<Integer, User> resultMap = new HashMap<>(quantityOfUsersToGenerate);
        for (int i = 0; i < quantityOfUsersToGenerate; i++) {
            resultMap.put(i, new User(i, "FirstName" + i, "LastName" + i, new Random().nextInt()));
        }
        return Collections.unmodifiableMap(resultMap);
    }

    @Override
    public User getUserByID(int id) {
        return storageMap.get(id);
    }

    @Override
    public String getFirstNameByID(int id) {
        return getUserByID(id).getFirstName();
    }

    @Override
    public String getLastNameByID(int id) {
        return getUserByID(id).getLastName();
    }

    @Override
    public int getSalaryByID(int id) {
        return getUserByID(id).getSalary();
    }
}
