package com.tarte;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {
        final BufferedImage img = ImageIO.read(new File("src/main/resources/img.jpg"));
    }
}
