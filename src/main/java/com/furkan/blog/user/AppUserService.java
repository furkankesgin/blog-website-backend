package com.furkan.blog.user;

public interface AppUserService {
    CurrentUserResponse createCurrentUser();

    CurrentUserResponse getCurrentUser();
}
