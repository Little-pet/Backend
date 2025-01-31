package LittlePet.UMC.SmallPet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class PetCategoryReqeustDto {

    @Getter
    @Setter
    public static class PetCategoryWriteDTO {
        @NotBlank(message = "종을 입력해주세요.")
        @Size(max = 20, message = "종은 20자를 초과할 수 없습니다.")
        private String species;

        private String features;
        private String featuresHeadLine;

        //먹이정보
        private String foodInfo;
        private String foodInfoHeadLine;

        //환경
        private String environment;
        private String environmentHeadLine;

        //놀이방법
        private String playMethods;
        private String playMethodsHeadLine;

        @NotNull(message = "상위 카테고리 ID(petBigCategoryId)는 필수로 입력해야 합니다.")
        private Long petBigCategoryId;
    }
}
