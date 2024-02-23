package org.danrizzyraza.image;

public class ColourSpaceTransformer {
    public static short[] RGBtoYCbCr(short[] RGBTriple) {
        short red = RGBTriple[0];
        short green = RGBTriple[1];
        short blue = RGBTriple[2];
        short[] YCbCr = new short[3];
        YCbCr[0] = (short) (16 + (65.681*red + 128.553*green + 24.966*blue)/255);
        YCbCr[1] = (short) (128 + (-37.797*red - 74.203*green + 112*blue)/255);
        YCbCr[2] = (short) (128 + (112*red - 93.786*green - 18.214*blue)/255);
        return YCbCr;
    }

    public static short[] YCbCrtoRGB(short[] YCbCrTriple) {
        short Y = YCbCrTriple[0];
        short Cb = YCbCrTriple[1];
        short Cr = YCbCrTriple[2];
        short[] RGB = new short[3];
        RGB[0] = (short) ( ((298.082*Y + 408.583*Cr)/256) - 222.921 );
        RGB[1] = (short) ( ((298.082*Y - 100.291*Cb - 208.120*Cr)/256) + 135.576 );
        RGB[2] = (short) ( ((298.082*Y + 516.412*Cb)/256) - 276.836 );
        return RGB;
    }
}
