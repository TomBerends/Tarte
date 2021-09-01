package com.tarte;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.Consumer;


public class ArtGenerator {
    private static byte[] getPixels(final BufferedImage img) {
//        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageIO.write(img, "jpg", byteArrayOutputStream);
//        return byteArrayOutputStream.toByteArray();
        return ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
    }

    private final byte[] pixels;
    private final int width;
    private final int height;

    public ArtGenerator(final File imgFile) throws IOException {
        this(ImageIO.read(imgFile));
    }

    public ArtGenerator(final BufferedImage img) {
        pixels = getPixels(img);
        width = img.getWidth();
        height = img.getHeight();
    }

    public class ArtStream {
        private int pixelIdx;
        private char previousCharacter;
        private final char[] brightnessToAscii = " `^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$".toCharArray();
        private boolean consumed;

        private ArtStream() {
        }

        private boolean hasNext() {
            return pixelIdx < pixels.length - 3;
        }

        private char computeCharacter() {
            if(previousCharacter != '\n' && pixelIdx % (width * 3) == 0)
                return '\n';

            final int avg = (int) (0.21 * (pixels[++pixelIdx] & 0xFF) + 0.72 * (pixels[++pixelIdx] & 0xFF) + 0.07 * (pixels[++pixelIdx] & 0xFF));
            return brightnessToAscii[avg * (brightnessToAscii.length - 1) / 255];
        }

        private char next() {
            return previousCharacter = computeCharacter();
        }

        private void checkConsumed() throws IllegalStateException {
            if(consumed)
                throw new IllegalStateException("ArtStream has already been consumed!");
        }

        public void forEach(final Consumer<Character> action) {
            checkConsumed();

            while(hasNext()) {
                final char c = next();
                if(c != '\n') {
                    action.accept(c);
                    action.accept(c);
                } else {
                    break;
                }

                action.accept(c);
            }

            consumed = true;
        }

        public void print() {
            print(System.out);
        }

        public void print(final PrintStream printStream) {
            forEach(printStream::print);
        }

        public BufferedImage generateImage() {
            checkConsumed();

            final BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            final Graphics2D g2d = (Graphics2D) img.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.drawString(toString(), 100, 100);

            return img;
        }

        @Override
        public String toString() {
            checkConsumed();

            final StringBuilder builder = new StringBuilder(); //TODO use char[]

            forEach(builder::append);

            return builder.toString();
        }
    }

    public ArtStream generate() {
        return new ArtStream();
    }
}
