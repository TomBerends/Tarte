package com.tarte;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(final String[] args) {
        final ArtGenerator generator;
        try {
            generator = new ArtGenerator(new File("src/main/resources/img.jpg"));

            final BufferedImage img = generator.generate().generateImage();
            ImageIO.write(img, "png", new File("src/main/resources/generated.png"));
        } catch(final IOException | IllegalStateException e) {
            e.printStackTrace();
        }

//        final BufferedImage img = ImageIO.read(new File("src/main/resources/img.jpg"));
//        final int res = 1;
//        final int resSqr = res * res;
//        final int width = img.getWidth();
//        final int height = img.getHeight();
//        final int scaledWidth = width / res;
//        final int scaledHeight = width / res;
//
//        final char[][] asciiMatrix = new char[scaledWidth][scaledHeight];
//        final char[] brightnessToAscii = " `^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$".toCharArray();
//
//
//        for(int i = 0; i < scaledWidth; i++) {
//            for(int j = 0; j < scaledHeight; j++) {
//                final int x = i * res;
//                final int y = j * res;
//                final int[] rgbs = new int[resSqr];
//
//                for(int xoff = 0; xoff < res && x + xoff < width; xoff++) {
//                    for(int yoff = 0; yoff < res && y + yoff < height; yoff++) {
//                        final int idx = yoff + xoff * res;
//                        rgbs[idx] = img.getRGB(x + xoff, y + yoff);
//                    }
//                }
//
//                int red = 0;
//                int green = 0;
//                int blue = 0;
//                for(final int rgb : rgbs) {
//                    red += (rgb >> 16) & 0xFF;
//                    green += (rgb >> 8) & 0xFF;
//                    blue += rgb & 0xFF;
//                }
//
//                red /= resSqr;
//                green /= resSqr;
//                blue /= resSqr;
//
//                final int avg = (int) (0.21 * red + 0.72 * green + 0.07 * blue);
//                final char ascii = brightnessToAscii[avg * brightnessToAscii.length / 256];
//                asciiMatrix[i][j] = ascii;
//            }
//        }
//
//        for(int x = 0; x < scaledWidth; x++) {
//            for(int y = 0; y < scaledHeight; y++) {
//                for(int i = 0; i < 3; i++) {
//                    System.out.print(asciiMatrix[y][x]);
//                }
//            }
//            System.out.println();
//        }
    }
}
