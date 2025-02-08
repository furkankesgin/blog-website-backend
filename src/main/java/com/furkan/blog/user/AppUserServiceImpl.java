package com.furkan.blog.user;

import com.furkan.blog.shared.exception.ResourceNotFoundException;
import com.furkan.blog.shared.security.CurrentUserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final CurrentUserService currentUserService;
    private final AppUserRepository repository;
    private final ModelMapper mapper;

    @Override
    public AppUser createCurrentUser() {
        var user = AppUser.builder()
                .id(currentUserService.getCurrentUser().orElseThrow(ResourceNotFoundException::new).getId())
                .build();

        return repository.save(user);
    }

}
