package com.techcourse.service;

import com.techcourse.domain.User;
import org.springframework.transaction.support.TransactionTemplate;

public class TxUserService implements UserService {

    private final UserService userService;
    private final TransactionTemplate transactionTemplate;

    public TxUserService(UserService userService, TransactionTemplate transactionTemplate) {
        this.userService = userService;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public User findById(long id) {
        return userService.findById(id);
    }

    @Override
    public void insert(User user) {
        transactionTemplate.executeWithTransaction(connection -> {
            userService.insert(user);
            return null;
        });
    }

    @Override
    public void changePassword(long id, String newPassword, String createBy) {
        transactionTemplate.executeWithTransaction(connection -> {
            userService.changePassword(id, newPassword, createBy);
            return null;
        });
    }
}
