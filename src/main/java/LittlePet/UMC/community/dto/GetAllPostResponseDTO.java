package LittlePet.UMC.community.dto;

import LittlePet.UMC.domain.postEntity.Post;
import LittlePet.UMC.domain.postEntity.mapping.PostContent;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPostResponseDTO {
    private long postId;
    private long userId;
    private String title;
    private String petCategory;
    private List<PostContentResponseDTO> contents;
    private String userName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalDateTime createdTime;

    private long views;
    private long likes;
    private long comments;

    public GetAllPostResponseDTO(Post post) {
        this.postId = post.getId();
        this.userId = post.getUser().getId();
        this.title = post.getTitle();
        this.petCategory = post.getPetCategory().getSpecies(); // PetCategory에서 카테고리 가져오기
        this.userName = post.getUser().getName(); // 작성자 이름

        this.createdTime = post.getCreatedAt();
        this.views = post.getViews();
        this.likes = (long) post.getPostLikeList().size(); // 좋아요 개수
        this.comments = post.getTotalCommentCount(); // 전체 댓글 개수

        // ✅ 게시물 내용(사진, 텍스트 등) 리스트 변환
        this.contents = post.getPostcontentList()
                .stream()
                .sorted(Comparator.comparingInt(PostContent::getSequence))
                .map(content -> new PostContentResponseDTO(content.getContent(),content.getSequence())) // PostContent 엔티티의 content 필드
                .collect(Collectors.toList());
    }

    public static List<GetAllPostResponseDTO> fromEntityList(List<Post> posts) {
        return posts.stream()
                .map(GetAllPostResponseDTO::new)
                .collect(Collectors.toList());
    }

//    @Data
//    static class ContentDTO {
//        private String content;
//        private int sequence;
//
//        public ContentDTO(PostContent postContent) {
//            this.content = postContent.getContent();
//            this.sequence = postContent.getSequence();
//        }
//    }
}
