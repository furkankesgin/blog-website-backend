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
    public CurrentUserResponse createCurrentUser() {
        var currentUser = AppUser.builder()
                .id(currentUserService.getCurrentUser().orElseThrow(ResourceNotFoundException::new).getId())
                .build();

        var user = repository.save(currentUser);

        return CurrentUserResponse.builder()
                .id(user.getId())
                .build();
    }

    @Override
    public CurrentUserResponse getCurrentUser() {
        var userId = currentUserService.getCurrentUser().orElseThrow(ResourceNotFoundException::new).getId();
        var user = repository.findById(userId).orElseThrow(ResourceNotFoundException::new);

        return CurrentUserResponse.builder()
                .id(user.getId())
                .build();
    }

}
