package org.danrizzyraza.image;

import org.danrizzyraza.image.image_representations.RGBImage;

import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import static org.danrizzyraza.image.util.BufferedImageUtil.RGBtoARGB;

public class GenerateImage {
    public static void saveBufferedImage(BufferedImage bufferedImage, String name) throws IOException {
        File outputFile = new File(String.format("/Users/danyal.raza45/code/compression/compression-wanderings/Output/Images/%s", name));
        ImageIO.write(bufferedImage, "png", outputFile);
    }

    public static void saveImageFromRGBRepresentation(RGBImage imageRepresentation, String name) throws IOException {
        short[][][] pixelArray = imageRepresentation.getPixelArray();
        int width = imageRepresentation.getWidth();
        int height = imageRepresentation.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int col = 0; col < width; col ++) {
            for (int row = 0; row < height; row++) {
                int currColour = RGBtoARGB(pixelArray[col][row]);
                image.setRGB(col,row, currColour);
            }
        }

        saveBufferedImage(image, String.format("%s.png", name));
    }
}
