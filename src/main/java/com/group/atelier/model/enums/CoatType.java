package com.group.atelier.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoatType {
    JACKET_COAT("jacket_coat"),
    MIDI_COAT("midi_coat"),
    MAXI_COAT("maxi_coat");

    private final String imgDirName;
}
