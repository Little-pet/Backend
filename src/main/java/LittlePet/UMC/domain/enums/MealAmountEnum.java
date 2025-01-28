package LittlePet.UMC.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MealAmountEnum {
    LIGHT("LIGHT", "적음"),
    NORMAL("NORMAL", "보통"),
    HEAVY("HEAVY", "많음");

    private final String code;        // 영문 코드
    private final String description; // 한글 설명

    public static MealAmountEnum fromCode(String code) {
        for (MealAmountEnum value : values()) {
            if (value.code.equalsIgnoreCase(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown MealAmountEnum code: " + code);
    }

    public static MealAmountEnum fromDescription(String description) {
        for (MealAmountEnum value : values()) {
            if (value.description.equals(description)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown MealAmountEnum description: " + description);
    }
}