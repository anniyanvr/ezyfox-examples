package org.youngmonkeys.example.ezyhttp.login.service.impl;

import com.google.api.services.oauth2.model.Userinfo;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.sercurity.EzySHA256;
import org.youngmonkeys.example.ezyhttp.login.entity.AccountType;
import org.youngmonkeys.example.ezyhttp.login.entity.User;
import org.youngmonkeys.example.ezyhttp.login.entity.UserStatus;
import org.youngmonkeys.example.ezyhttp.login.exception.UserNotFoundException;
import org.youngmonkeys.example.ezyhttp.login.repository.UserRepository;
import org.youngmonkeys.example.ezyhttp.login.request.UpdateUserRequest;
import org.youngmonkeys.example.ezyhttp.login.service.IUserService;

import java.time.LocalDateTime;

@EzySingleton
public class UserService implements IUserService {

    @EzyAutoBind
    private UserRepository userRepository;

    @Override
    public User getUserInfoByEmail(String email) {
        return userRepository.findByField("email", email);
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User saveGoogleUserInfo(Userinfo googleUserInfo) {
        // create new user information
        User user = new User();
        user.setEmail(googleUserInfo.getEmail());
        user.setFullName(googleUserInfo.getName());
        user.setLastName(googleUserInfo.getFamilyName());
        user.setFirstName(googleUserInfo.getGivenName());
        user.setAvatarURL(googleUserInfo.getPicture());
        user.setAccountType(AccountType.GOOGLE);
        user.setDeleted(false);
        user.setStatus(UserStatus.REGISTER);
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userRepository.save(user);
    }
}