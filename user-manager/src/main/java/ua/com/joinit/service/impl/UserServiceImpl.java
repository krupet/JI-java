package ua.com.joinit.service.impl;

import ua.com.joinit.service.UserService;

/**
 * Created by krupet on 03.02.15.
 */
public class UserServiceImpl implements UserService {
    @Override
    public String getMessage() {
        return "Hi, %User%!";
    }
}
