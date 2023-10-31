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
}
