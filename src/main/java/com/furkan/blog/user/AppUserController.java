package com.furkan.blog.user;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "User operations")
public class AppUserController extends BaseController {

    private final AppUserService service;

    @Operation(
            summary = "Add current user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success",
                            content = @Content(schema = @Schema(implementation = CurrentUserResponse.class))
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
    @PostMapping("/me")
    public ResponseEntity<?> createCurrentUser() {
        CurrentUserResponse response = service.createCurrentUser();
        return created(response);
    }

    @Operation(
            summary = "Get current user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success",
                            content = @Content(schema = @Schema(implementation = CurrentUserResponse.class))
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
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        CurrentUserResponse response = service.getCurrentUser();
        return created(response);
    }

}
