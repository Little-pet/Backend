package LittlePet.UMC.domain.petEntity.categories;

import LittlePet.UMC.domain.BaseEntity.BaseTimeEntity;
import LittlePet.UMC.domain.postEntity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class PetCategory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
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

    private String featureImagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_big_category_id",nullable = false)
    private PetBigCategory petBigCategory;

    // Getters and Setters
}
