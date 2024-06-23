package com.supur.service;

import com.supur.entity.User;
import com.supur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Kullanıcı işlemlerini yöneten servis sınıfı.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Yeni bir kullanıcı kaydeder.
     * @param user Kaydedilecek kullanıcı.
     */
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Kullanıcıyı kullanıcı adı ve şifreye göre bulur.
     * @param username Kullanıcı adı.
     * @param password Şifre.
     * @return Bulunan kullanıcı veya null.
     */
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    /**
     * Kullanıcıyı kullanıcı adına göre bulur.
     * @param username Kullanıcı adı.
     * @return Bulunan kullanıcı veya null.
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
