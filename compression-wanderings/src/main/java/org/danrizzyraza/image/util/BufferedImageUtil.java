package org.danrizzyraza.image.util;

public class BufferedImageUtil {
    public static int RGBtoARGB(short[] RGBTriple) {
        int red = RGBTriple[0];
        int green = RGBTriple[1];
        int blue = RGBTriple[2];
        int color_argb = 0xFF << 24 | (0xFF & red) << 16 | (0xFF & green) << 8 | (0xFF & blue);

        return color_argb;
    }
}
