package org.danrizzyraza.image;

import org.danrizzyraza.image.image_representations.RGBImage;

import java.io.File;
import java.io.FileWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GetPixels {
    public static void getRGBPixelsFromImage(String inputName, String saveName) throws IOException {
        // This function scans across rows
        FileWriter writer = new FileWriter(String.format("/Users/danyal.raza45/code/compression/compression-wanderings/Output/Images/%s.txt", saveName));
        //Reading the image
        File file= new File(String.format("/Users/danyal.raza45/code/compression/compression-wanderings/Input/Images/%s", inputName));
        BufferedImage img = ImageIO.read(file);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //Retrieving contents of a pixel
                int pixel = img.getRGB(x,y);
                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                //Retrieving the R G B values
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                writer.append(red+":");
                writer.append(green+":");
                writer.append(blue+"");
                writer.append("\n");
                writer.flush();
            }
        }
        writer.close();
        System.out.println("RGB values at each pixel are stored in the specified file");
    }

    public static RGBImage getRGBRepresentationFromImage(String inputName) throws IOException {
        File file= new File(String.format("/Users/danyal.raza45/code/compression/compression-wanderings/Input/Images/%s", inputName));
        BufferedImage img = ImageIO.read(file);

        int width = img.getWidth();
        int height = img.getHeight();

        short[][][] pixelArray = new short[width][height][3];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //Retrieving contents of a pixel
                int pixel = img.getRGB(x,y);
                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                //Retrieving the R G B values
                pixelArray[x][y][0] = (short) color.getRed();
                pixelArray[x][y][1] = (short) color.getGreen();
                pixelArray[x][y][2] = (short) color.getBlue();
            }
        }
    return new RGBImage(pixelArray);
    }
}
