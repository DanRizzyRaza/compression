package org.danrizzyraza.image;

import java.io.File;
import java.io.FileWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GenerateImage {
    public static void saveBufferedImage(BufferedImage bufferedImage, String name) throws IOException {
        File outputFile = new File(String.format("/Users/danyal.raza45/code/compression/compression-wanderings/Output/Images/%s", name));
        ImageIO.write(bufferedImage, "png", outputFile);
    }

    public static void saveRGBText(String inputName, String name) {

    }
}
