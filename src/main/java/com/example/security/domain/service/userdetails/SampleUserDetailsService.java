package com.example.security.domain.service.userdetails;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.example.security.domain.model.Account;
import com.example.security.domain.service.account.AccountSharedService;

@Service
public class SampleUserDetailsService implements UserDetailsService {

    // (2)
    @Inject
    AccountSharedService accountSharedService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            // (3)
            Account account = accountSharedService.findOne(username);
            // (4)
            return new SampleUserDetails(account);

        } catch (ResourceNotFoundException e) {
            // (5)
            throw new UsernameNotFoundException("user not found", e);
        }

    }
}
