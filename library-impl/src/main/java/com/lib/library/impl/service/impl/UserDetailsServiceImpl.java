package com.lib.library.impl.service.impl;

import com.lib.library.db.entity.Reader;
import com.lib.library.db.entity.Staff;
import com.lib.library.impl.repository.ReaderRepository;
import com.lib.library.impl.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StaffRepository staffRepository;
    private final ReaderRepository readerRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return staffRepository.findByEmail(login)
                .<UserDetails>map(staff -> User.builder()
                        .username(staff.getEmail())
                        .password(staff.getPassword())
                        .roles(staff.getRole()) // ADMIN или USER
                        .build())
                .orElseGet(() -> readerRepository.findByName(login)
                        .map(reader -> User.builder()
                                .username(reader.getName())
                                .password(reader.getPassword())
                                .roles("READER")
                                .build())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + login)));

    }
    public Staff getStaffByEmail(String email) {
        return staffRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Staff not found: " + email));
    }

    public Reader getReaderByName(String name) {
        return readerRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Reader not found: " + name));
    }
}
