package com.group.atelier.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_metrics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "neck_semi_circumference")
    private Double neckSemiCircumference;

    @Column(name = "chest_semi_circumference_1")
    private Double chestSemiCircumference1;

    @Column(name = "chest_semi_circumference_2")
    private Double chestSemiCircumference2;

    @Column(name = "chest_semi_circumference_3")
    private Double chestSemiCircumference3;

    @Column(name = "waist_semi_circumference")
    private Double waistSemiCircumference;

    @Column(name = "shoulder_width")
    private Double shoulderWidth;

    @Column(name = "chest_height")
    private Double chestHeight;

    @Column(name = "chest_height_1")
    private Double chestHeight1;

    @Column(name = "back_armhole_height")
    private Double backArmholeHeight;

    @Column(name = "back_length_till_waist")
    private Double backLengthTillWaist;

    @Column(name = "shoulder_height_sidelong")
    private Double shoulderHeightSidelong;

    @Column(name = "chest_width")
    private Double chestWidth;

    @Column(name = "chest_center")
    private Double chestCenter;

    @Column(name = "back_width")
    private Double backWidth;

    @Column(name = "waist_length_front")
    private Double waistLengthFront;

    @Column(name = "neck_base_to_front_waist_line_distance")
    private Double neckBaseToFrontWaistLineDistance;

    @Column(name = "increase_to_width_by_chest_line")
    private Double increaseToWidthByChestLine;

    @Column(name = "increase_to_armhole_depth")
    private Double increaseToArmholeDepth;

    @Column(name = "increase_to_neck_back")
    private Double increaseToNeckBack;
}
