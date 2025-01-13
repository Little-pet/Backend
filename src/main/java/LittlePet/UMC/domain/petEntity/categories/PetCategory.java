package LittlePet.UMC.domain.petEntity.categories;

import LittlePet.UMC.domain.BaseEntity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@ToString
public class PetCategory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String species;

    @Column(nullable = false)
    private String features;

    @Column(nullable = false)
    private String foodInfo;

    @Column(nullable = false)
    private String environment;

    @Column(nullable = false)
    private String playMethods;

    @Column(nullable = false)
    private String mustHaveItemFood;

    @Column(nullable = false)
    private String mustHaveItemToys;

    @Column(nullable = false)
    private String mustHaveItemCage;

    @Column(nullable = false)
    private String featureImagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_big_category_id",nullable = false)
    private PetBigCategory petBigCategory;

    // Getters and Setters
}
