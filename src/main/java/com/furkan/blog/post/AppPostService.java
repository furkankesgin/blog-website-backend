package com.furkan.blog.post;

import java.util.List;

public interface AppPostService {

    PostResponse createPost(AppPost post);
    PostResponse updatePost(AppPost post);
    PostResponse deletePost(Long id);
    PostResponse getPost(Long id);
    List<PostResponse> getPosts();
}