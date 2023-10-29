package com.group.atelier.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client_metrics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column(name = "neck_semi_circumference")
    private float neckSemiCircumference;

    @Column(name = "chest_semi_circumference_1")
    private float chestSemiCircumference1;

    @Column(name = "chest_semi_circumference_2")
    private float chestSemiCircumference2;

    @Column(name = "chest_semi_circumference_3")
    private float chestSemiCircumference3;

    @Column(name = "waist_semi_circumference")
    private float waistSemiCircumference;

    @Column(name = "shoulder_width")
    private float shoulderWidth;

    @Column(name = "chest_height")
    private float chestHeight;

    @Column(name = "chest_height_1")
    private float chestHeight1;

    @Column(name = "back_armhole_height")
    private float backArmholeHeight;

    @Column(name = "back_length_till_waist")
    private float backLengthTillWaist;

    @Column(name = "shoulder_height_sidelong")
    private float shoulderHeightSidelong;

    @Column(name = "chest_width")
    private float chestWidth;

    @Column(name = "chest_center")
    private float chestCenter;

    @Column(name = "back_width")
    private float backWidth;

    @Column(name = "waist_length_front")
    private float waistLengthFront;

    @Column(name = "neck_base_to_front_waist_line_distance")
    private float neckBaseToFrontWaistLineDistance;
}
