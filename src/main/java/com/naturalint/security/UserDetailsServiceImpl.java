package com.naturalint.security;

import com.naturalint.repository.UserRepository;
import com.naturalint.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by skn on 10/11/2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    private RestTemplate restTemplate = new RestTemplate();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOG.info("Try to login user with name {}", email);
        User foundUser = userRepository.findByEmail(email);
        if (foundUser != null) {
            LOG.info("User with name {} have found", foundUser.getEmail());
            return new UserDetailsImpl(foundUser.getEmail(), foundUser.getPassword());
        }
        return null;
    }
}
