package com.furkan.blog.post;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppPostServiceImpl implements AppPostService {

    private final AppPostRepository repository;
    private final ModelMapper mapper;

    @Override
    public PostResponse createPost(AppPost post) {
        var postEntity = mapper.map(post, AppPost.class);
        var savedPost = repository.save(postEntity);
        return PostResponse.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .build();
    }

    @Override
    public PostResponse updatePost(AppPost post) {
        var updatedPost = repository.save(post);
        return PostResponse.builder()
                .id(updatedPost.getId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .build();
    }

    @Override
    public PostResponse deletePost(Long id) {
        repository.deleteById(id);
        return PostResponse.builder()
                .id(id)
                .build();
    }

    @Override
    public PostResponse getPost(Long id) {
        var postEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostResponse.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .build();
    }

    @Override
    public List<PostResponse> getPosts() {
        var posts = repository.findAll();
        return posts.stream()
                .map(post -> PostResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toList());
    }
}