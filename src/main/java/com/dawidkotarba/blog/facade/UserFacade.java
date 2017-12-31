package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.auth.service.impl.RegistrationServiceImpl;
import com.dawidkotarba.blog.converters.impl.UserInConverter;
import com.dawidkotarba.blog.converters.impl.UserOutConverter;
import com.dawidkotarba.blog.model.dto.impl.UserInDto;
import com.dawidkotarba.blog.model.dto.impl.UserOutDto;
import com.dawidkotarba.blog.model.entities.impl.UserEntity;
import com.dawidkotarba.blog.repository.CacheableUserRepository;
import com.google.common.base.Preconditions;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.stream.Collectors;

@Named
public class UserFacade {

    private final CacheableUserRepository cacheableUserRepository;
    private final UserInConverter userInConverter;
    private final UserOutConverter userOutConverter;
    private final RegistrationServiceImpl registrationService;

    @Inject
    UserFacade(final CacheableUserRepository cacheableUserRepository, final UserInConverter userInConverter, final UserOutConverter
            userOutConverter, final RegistrationServiceImpl registrationService) {
        this.cacheableUserRepository = cacheableUserRepository;
        this.userInConverter = userInConverter;
        this.userOutConverter = userOutConverter;
        this.registrationService = registrationService;
    }

    public Optional<UserOutDto> findByUsername(final String username) {
        Preconditions.checkNotNull(username);
        final UserEntity byUsername = cacheableUserRepository.findByUsername(username);

        if (Objects.isNull(byUsername)) {
            return Optional.empty();
        }

        final UserOutDto userDto = userOutConverter.convert(byUsername);
        return Optional.of(userDto);
    }

    public Set<UserOutDto> findAll() {
        final List<UserEntity> all = cacheableUserRepository.findAll();

        final LinkedHashSet<UserOutDto> result = all.stream()
                .map(userOutConverter::convert)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return Collections.unmodifiableSet(result);

    }

    @PreAuthorize("hasAuthority('administrate')")
    public void add(final UserInDto userInDto) {
        Preconditions.checkNotNull(userInDto);
        registrationService.registerUser(userInConverter.convert(userInDto));
    }
}
