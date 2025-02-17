package LittlePet.UMC.User.converter;

import LittlePet.UMC.User.dto.UserResponse.UserProfileResponseDTO;
import LittlePet.UMC.User.dto.UserResponse.UserUpdateProfileResponseDTO;
import LittlePet.UMC.domain.userEntity.User;

import java.util.stream.Collectors;

public class UserProfileConverter {

    public static UserProfileResponseDTO toUserResponseDto(User user) {
        return UserProfileResponseDTO.builder()
                .name(user.getName())
                .profilePhoto(user.getProfilePhoto())
                .postCount(user.getPostList().size())
                .commentCount(user.getCommentList().size())
                .likeCount(user.getPostlikeList().size())
                .reviewCount(user.getHospitalStarRatingList().size())
                .scrapCount(user.getPostClippingList().size())
                .petProfile(user.getUserPetList().stream()
                        .map(pet -> UserProfileResponseDTO.PetProfileDTO.builder()
                                .petSpices(pet.getPetCategory().getSpecies()) // PetCategory에서 species 가져오기
                                .imageUrl(pet.getPetCategory().getFeatureImagePath()) // 프로필 이미지 추가
                                .build())
                        .collect(Collectors.toList()))
                .userPet(user.getUserPetList().stream()
                        .map(pet -> UserProfileResponseDTO.PetInfoDTO.builder()
                                .petId(pet.getId())
                                .name(pet.getName())
                                .profilePhoto(pet.getProfilePhoto())
                                .petCategory(pet.getPetCategory().getSpecies())
                                .build())
                        .collect(Collectors.toList()))
                .userBadge(user.getUserBadgeList().stream()
                        .filter(badge -> badge.getBadge() != null) // null 체크 추가
                        .map(badge -> UserProfileResponseDTO.BadgeInfoDTO.builder()
                                .name(badge.getBadge().getName())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }


        public static UserUpdateProfileResponseDTO toUpdateResponse(User user) {
            return UserUpdateProfileResponseDTO.builder()
                    .userId(user.getId())
                    .nickname(user.getName())
                    .phone(user.getPhone())
                    .profilePhoto(user.getProfilePhoto())
                    .introduction(user.getIntroduction())
                    .build();
        }

}
