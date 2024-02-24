package org.danrizzyraza.image.image_representations;

import static org.danrizzyraza.image.ChannelTransformations.generalDownSample;
import static org.danrizzyraza.image.ColourSpaceTransformer.YCbCrtoRGB;

public class YCbCrImage implements ImageRepresentation {
    short[][][] pixelArray;
    private final int width;
    private final int height;

    YCbCrImage(short[][][] pixelArray) {
        this.pixelArray = pixelArray;
        this.height = pixelArray.length;
        this.width = pixelArray[0].length;
    }

    public short[][][] getPixelArray() {
        return pixelArray;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void applyDownSample(int channel, int scale) {
        generalDownSample(this, channel, scale);
    }

    public RGBImage convertToRGBImage() {
        short[][][] RGBArray = new short[width][height][3];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                RGBArray[x][y] = YCbCrtoRGB(pixelArray[x][y]);
            }
        }

        return new RGBImage(RGBArray);
    }
}
