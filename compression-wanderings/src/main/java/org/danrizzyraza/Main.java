package org.danrizzyraza;

import org.danrizzyraza.image.image_representations.RGBImage;
import org.danrizzyraza.image.image_representations.YCbCrImage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import static org.danrizzyraza.image.ColourSpaceTransformer.RGBtoYCbCr;
import static org.danrizzyraza.image.ColourSpaceTransformer.YCbCrtoRGB;
import static org.danrizzyraza.image.GenerateImage.saveBufferedImage;
import static org.danrizzyraza.image.GenerateImage.saveImageFromRGBRepresentation;
import static org.danrizzyraza.image.GetPixels.getRGBPixelsFromImage;
import static org.danrizzyraza.image.GetPixels.getRGBRepresentationFromImage;

public class Main {
    public static void main(String[] args) throws IOException {
        bananaTest();
    }

    static void bananaYCbCrTest() throws IOException {
        RGBImage bananaRGBImage = getRGBRepresentationFromImage("flying-banana.png");
        YCbCrImage bananaYCbCr = bananaRGBImage.convertToYCbCrImage();
//        bananaYCbCr.applyDownSample(2, 10);
        RGBImage bananaRGBImageBackTo = bananaYCbCr.convertToRGBImage();
        saveImageFromRGBRepresentation(bananaRGBImageBackTo, "banana-toYCbCr-backToRGB-before");
    }
    static void bananaTest() throws IOException {
        RGBImage bananaRGBImage = getRGBRepresentationFromImage("flying-banana.png");
        bananaRGBImage.applyDownSample(0, 10);
//        bananaRGBImage.applyDownSample(1, 10);
        bananaRGBImage.applyDownSample(2, 10);
        saveImageFromRGBRepresentation(bananaRGBImage, "banana-dSampled-RB-10x");
    }

    static void test1() throws IOException {
        short[][][] testArray = { {{255,0,0}, {128,0,0}, {255,0,0}, {255,255,0} }, { {255,0,0}, {128,0,0}, {255,0,0}, {255,255,0} }, { {128,0,0}, {255,255,0}, {255,0,0}, {128,0,0} }, { {128,0,0}, {255,255,0}, {255,0,0}, {128,0,0} }, { {255,0,0}, {255,0,0}, {255,255,0}, {255,255,0} } };
        RGBImage testRGB = new RGBImage(testArray);
        testRGB.applyDownSample(0, 3);
        saveImageFromRGBRepresentation(testRGB, "imageR3xDSampling");
    }
    static void getPixel() throws IOException {
        getRGBPixelsFromImage("test.png", "testRGB");
    }

    static void generateFromRGB() throws IOException {
        int alpha = 100;
        int red = 255;
        int green =255;
        int blue = 255;
        int color_argb = (0xFF & alpha) << 24 | (0xFF & red) << 16 | (0xFF & green) << 8 | (0xFF & blue);
//        int alpha = (rgb >> 24) & 0xFF;
//        int red =   (rgb >> 16) & 0xFF;
//        int green = (rgb >>  8) & 0xFF;
//        int blue =  (rgb      ) & 0xFF;
        BufferedImage image = new BufferedImage(3,3,BufferedImage.TYPE_INT_RGB);
        image.setRGB(0,0, color_argb);
        image.setRGB(0,1, color_argb);
        image.setRGB(0,2, (((0<<8) + 0)<<8) + 255);
        image.setRGB(1,0, 240);
        image.setRGB(1,1, 240 << 8);
        image.setRGB(1,2, 240 << 16);
        image.setRGB(2,0, 10);
        image.setRGB(2,1, 10 << 8);
        image.setRGB(2,2, 10 << 16);
        saveBufferedImage(image, "test.png");
    }

    static void transformerTest() {
        short[] testTriple = new short[3];
        testTriple[0] = 255;
        testTriple[1] = 128;
        testTriple[2] = 1;
        short[] toRGB = RGBtoYCbCr(testTriple);
        System.out.println(Arrays.toString(toRGB));
        short[] andBack = YCbCrtoRGB(toRGB);
        System.out.println(Arrays.toString(andBack));
        short[] toYCbCr = YCbCrtoRGB(testTriple);
        System.out.println(Arrays.toString(toYCbCr));
        short[] andBackAgain = RGBtoYCbCr(toYCbCr);
        System.out.println(Arrays.toString(andBackAgain));
    }
}