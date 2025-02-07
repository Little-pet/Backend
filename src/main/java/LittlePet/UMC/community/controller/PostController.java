package LittlePet.UMC.community.controller;

import LittlePet.UMC.S3Service;
import LittlePet.UMC.SmallPet.service.PetCategoryService;
import LittlePet.UMC.apiPayload.ApiResponse;
import LittlePet.UMC.community.dto.CreatePostResponseDTO;
import LittlePet.UMC.community.dto.GetPostResponseDTO;
import LittlePet.UMC.community.dto.PostForm;
import LittlePet.UMC.community.service.PostService;
import LittlePet.UMC.domain.petEntity.categories.PetCategory;
import LittlePet.UMC.domain.postEntity.Post;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PetCategoryService petCategoryService;
    private final S3Service s3Service;

//    @Operation(summary = "커뮤니티 게시물 생성", description = "커뮤니티에 새로운 게시물을 생성할 수 있는 카테고리입니다")
//    @PostMapping("/api/post/{user-id}")
//    public ApiResponse<CreatePostResponseDTO> createPost(
//            @RequestBody @Valid PostForm postForm,
//            @PathVariable("user-id") Long userId)  {
//
//        String url = null;
//        Post post = postService.createPost(postForm, userId, url);
//
//        CreatePostResponseDTO dto = new CreatePostResponseDTO(post);
//
//        return ApiResponse.onSuccess(dto);
//    }

    @Operation(summary = "커뮤니티 게시물 생성", description = "커뮤니티에 새로운 게시물을 생성할 수 있는 카테고리입니다")
    @PostMapping(value = "/api/post/{user-id}", consumes = {"multipart/form-data"})
    public ApiResponse<CreatePostResponseDTO> createPost(
            @RequestPart @Valid PostForm postForm,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @PathVariable("user-id") Long userId) throws IOException {

        String url = s3Service.upload(image);
        Post post = postService.createPost(postForm, userId, url);

        CreatePostResponseDTO dto = new CreatePostResponseDTO(post);

        return ApiResponse.onSuccess(dto);
    }

    @Operation(summary = "커뮤니티 게시물 수정", description = "커뮤니티에 기존 게시물을 수정할 수 있는 카테고리입니다")
    @PatchMapping(value = "/api/post/{post-id}", consumes = {"multipart/form-data"})
    public ApiResponse<CreatePostResponseDTO> updatePost(
            @PathVariable("post-id") Long postId,
            @RequestPart @Valid PostForm postForm,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        String url = s3Service.upload(image);
        Post updatePost = postService.updatePost(postId, postForm,url);

        CreatePostResponseDTO dto = new CreatePostResponseDTO(updatePost);

        return ApiResponse.onSuccess(dto);
    }

    @Operation(summary = "커뮤니티 게시물 삭제", description = "커뮤니티를 삭제할 수 있는 카테고리입니다")
    @DeleteMapping("/api/post/{post-id}")
    public ApiResponse<String> deletePost(@PathVariable("post-id") Long postId) {
        postService.deletePost(postId);
        return ApiResponse.onSuccess("게시물이 성공적으로 삭제되었습니다");
    }

    @Operation(summary = "커뮤니티 특정 글 조회", description = "특정 게시물을 조회하는 카테고리입니다")
    @GetMapping("/api/post/{post-id}")
    public ApiResponse<GetPostResponseDTO> GetPost(@PathVariable("post-id") Long postId) {
        Post post = postService.FindOnePost(postId);

        GetPostResponseDTO dto = new GetPostResponseDTO(post);

        return ApiResponse.onSuccess(dto);
    }
}

