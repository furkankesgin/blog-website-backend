package com.furkan.blog.post;

import com.furkan.blog.shared.controller.BaseController;
import com.furkan.blog.shared.result.ErrorResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Tag(name = "Posts", description = "Post operations")
public class AppPostController extends BaseController {

    private final AppPostService service;

    @Operation(
            summary = "post",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success",
                            content = @Content(schema = @Schema(implementation = PostResponse.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid value",
                            content = @Content(schema = @Schema(implementation = ErrorResult.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Not found",
                            content = @Content(schema = @Schema(implementation = ErrorResult.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(schema = @Schema(implementation = ErrorResult.class))
                    )
            }
    )
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody AppPost post) {
        PostResponse response = service.createPost(post);
        return created(response);
    }

    @GetMapping("/post")
    public ResponseEntity<?> getPosts() {
        List<PostResponse> response = service.getPosts();
        return created(response);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        PostResponse response = service.getPost(id);
        return created(response);
    }

    @PutMapping("/post")
    public ResponseEntity<?> updatePost(@RequestBody AppPost post) {
        PostResponse response = service.updatePost(post);
        return created(response);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        PostResponse response = service.deletePost(id);
        return created(response);
    }

}