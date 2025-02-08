package LittlePet.UMC.community.dto;

import LittlePet.UMC.apiPayload.code.status.ErrorStatus;
import LittlePet.UMC.apiPayload.exception.handler.BadgeHandler;
import LittlePet.UMC.apiPayload.exception.handler.PetCategoryHandler;
import LittlePet.UMC.domain.petEntity.categories.PetCategory;
import LittlePet.UMC.domain.petEntity.mapping.UserPet;
import LittlePet.UMC.domain.postEntity.Post;
import LittlePet.UMC.domain.postEntity.mapping.PostContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPostResponseDTO {
    private String userName;
    private String userBadge;
    private String PetCategory;
    private String postTitle;
    private LocalDateTime createTime;
    private Long views; //조회
    private Long likes; //좋아요
    private Long commentNum; //댓글수
    private List<PostContentForm> contents;
    private List <CommentResponseDTO> comments; //댓글 단 사람의 닉네임, 펫 종류, 성별, 그, 댓글내용, 댓글 생성 시간

    public GetPostResponseDTO(Post post) {
        this.userName = post.getUser().getName();

        this.userBadge = post.getUser().userHaveBadge();

        this.PetCategory =post.getPetCategory().getSpecies();

        this.postTitle = post.getTitle();
        this.createTime = post.getCreatedAt();
        this.views = post.getViews();
        this.likes = (long) post.getPostLikeList().size();
        this.commentNum = post.getTotalCommentCount();

        // ✅ PostContent 리스트 변환
        this.contents = Optional.ofNullable(post.getPostcontentList())
                .orElse(Collections.emptyList())  // null이면 빈 리스트 반환
                .stream()
                .map(postContent -> new PostContentForm(postContent.getContent()))
                .collect(Collectors.toList());

        // ✅ 최상위 댓글 필터링 및 변환
        this.comments = Optional.ofNullable(post.getCommentList())
                .orElse(Collections.emptyList()) // null이면 빈 리스트 반환
                .stream()
                .filter(comment -> comment.getParent() == null) // 부모가 없는 최상위 댓글만 필터링
                .map(comment -> new CommentResponseDTO(comment)) // 개별 댓글을 DTO로 변환
                .collect(Collectors.toList());
    }


}
