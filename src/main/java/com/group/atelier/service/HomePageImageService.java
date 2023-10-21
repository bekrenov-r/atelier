package com.group.atelier.service;

import com.group.atelier.dto.response.HomePageImagesResponse;
import com.group.atelier.util.ResourcesConstants;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static com.group.atelier.util.ResourcesConstants.*;

@Service
@RequiredArgsConstructor
public class HomePageImageService {
    public HomePageImagesResponse getAllImages() {
        File jacketCoatDir = new File(HOMEPAGE_IMG_DIR_PATH + "/" + JACKET_COAT_IMG_DIR_NAME);
        List<byte[]> jacketCoatImages = this.getAllImagesFromDirectory(jacketCoatDir);

        File midiCoatDir = new File(HOMEPAGE_IMG_DIR_PATH + "/" + MIDI_COAT_IMG_DIR_NAME);
        List<byte[]> midiCoatImages = this.getAllImagesFromDirectory(midiCoatDir);

        File maxiCoatDir = new File(HOMEPAGE_IMG_DIR_PATH + "/" + MIDI_COAT_IMG_DIR_NAME);
        List<byte[]> maxiCoatImages = this.getAllImagesFromDirectory(maxiCoatDir);

        return HomePageImagesResponse.builder()
                .jacketCoatImages(jacketCoatImages)
                .midiCoatImages(midiCoatImages)
                .maxiCoatImages(maxiCoatImages)
                .build();
    }

    private List<byte[]> getAllImagesFromDirectory(File directory) {
        if(!directory.isDirectory()) throw new RuntimeException("Not a directory");
        if(directory.listFiles() == null) return List.of();

        return Arrays.stream(directory.listFiles())
                .map(file -> Try.of(
                        () -> Files.readAllBytes(file.toPath())
                ))
                .map(tryImage -> tryImage.getOrElseThrow(
                        () -> new RuntimeException("Error occurred processing image")
                ))
                .toList();
    }


}
