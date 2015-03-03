package ua.com.joinit.service.impl;

import ua.com.joinit.service.UserService;
import ua.com.joinit.service.UserTestService;

/**
 * Created by krupet on 03.02.15.
 */
public class UserTestServiceImpl implements UserTestService {
    @Override
    public String getMessage() {
        return "Hi, %User%!";
    }
}
