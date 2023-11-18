package com.group.atelier.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pattern_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PatternData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "basis_grid_width")
    private Double basisGridWidth;

    @Column(name = "basis_grid_length")
    private Double basisGridLength;

    @Column(name = "armhole_depth")
    private Double armholeDepth;

    @Column(name = "back_width")
    private Double backWidth;

    @Column(name = "file_width")
    private Double fileWidth;

    @Column(name = "armhole_width")
    private Double armholeWidth;

    @Column(name = "back_neck_width")
    private Double backNeckWidth;

    @Column(name = "back_neck_height")
    private Double backNeckHeight;

    @Column(name = "shoulder_cut_slope")
    private Double shoulderCutSlope;

    @Column(name = "shoulder_cut_end")
    private Double shoulderCutEnd;

    @Column(name = "side_slope_top")
    private Double sideSlopeTop;

    @Column(name = "back_armhole_slope")
    private Double backArmholeSlope;

    @Column(name = "product_balance")
    private Double productBalance;

    @Column(name = "file_neck_width")
    private Double fileNeckWidth;

    @Column(name = "file_neck_depth")
    private Double fileNeckDepth;

    @Column(name = "chest_dart")
    private Double chestDart;

    @Column(name = "shoulder_slope")
    private Double shoulderSlope;

    @Column(name = "armhole")
    private Double armhole;

    @Column(name = "total_dart_deviation_by_waist_line")
    private Double totalDartDeviationByWaistLine;

    @Column(name = "side_dart")
    private Double sideDart;

    @Column(name = "file_dart")
    private Double fileDart;

    @Column(name = "back_dart")
    private Double backDart;

    @Column(name = "increase_to_width_by_chest_line")
    private Double increaseToWidthByChestLine;

    @Column(name = "increase_to_width_by_waist_line")
    private Double increaseToWidthByWaistLine;

    @Column(name = "increase_to_armhole_width")
    private Double increaseToArmholeWidth;

    @Column(name = "increase_to_armhole_depth")
    private Double increaseToArmholeDepth;

    @Column(name = "increase_to_neck_back")
    private Double increaseToNeckBack;

    @Column(name = "increase_to_back_width")
    private Double increaseToBackWidth;

    @Column(name = "increase_to_file_width")
    private Double increaseToFileWidth;
}
