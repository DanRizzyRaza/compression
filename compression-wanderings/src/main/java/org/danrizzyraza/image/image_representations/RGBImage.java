package org.danrizzyraza.image.image_representations;

import static org.danrizzyraza.image.ColourSpaceTransformer.RGBtoYCbCr;
import static org.danrizzyraza.image.ChannelTransformations.generalDownSample;
import static org.danrizzyraza.image.ChannelTransformations.generalChannelMultiplier;
import static org.danrizzyraza.image.ChannelTransformations.generalSwapChannels;

public class RGBImage implements ImageRepresentation {
    short[][][] pixelArray;
    final int width;
    final int height;

    public RGBImage(short[][][] pixelArray) {
        this.pixelArray = pixelArray;
        this.width = pixelArray.length;
        this.height = pixelArray[0].length;
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

    //    private void print() {
//        StringBuilder str = new StringBuilder();
//        for (short[][] columns: pixelArray) {
//            for (short[] row: columns) {
//                str.append(row[0]);
//            }
//            str.append(" ");
//        }
//        System.out.println(str.toString());
//    }

    @Override
    public void applyDownSample(int channel, int scale) {
        generalDownSample(this, channel, scale);
    }
    @Override
    public void applyChannelMultiplier(int channel, int multiplier) {
        generalChannelMultiplier(this, channel, multiplier);
    }

    @Override
    public void swapChannels(int channel1, int channel2) {
        generalSwapChannels(this, channel1, channel2);
    }

    public YCbCrImage convertToYCbCrImage() {
        short[][][] YCbCrArray = new short[width][height][3];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                YCbCrArray[x][y] = RGBtoYCbCr(pixelArray[x][y]);
            }
        }

        return new YCbCrImage(YCbCrArray);
    }
}
