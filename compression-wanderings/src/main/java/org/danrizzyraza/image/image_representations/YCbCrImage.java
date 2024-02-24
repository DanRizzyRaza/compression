package org.danrizzyraza.image.image_representations;

import static org.danrizzyraza.image.ColourSpaceTransformer.YCbCrtoRGB;

public class YCbCrImage implements ImageRepresentation {
    private short[][][] pixelArray;
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

    @Override
    public void applyDownSample(int channel, int scale) {
        if (channel > pixelArray[0][0].length) {
            System.out.println("channel out of bounds");
            return;
        }

        int noCols = (int) Math.ceil(((double) width) / scale);
        int noRows = (int) Math.ceil(((double) height) / scale);

        for (int col = 0; col < noCols; col++) {
            for (int row = 0; row < noRows; row++) {

                int currValue = 0;
                int count = 0;

                int currCol = 0;
                int currRow = 0;

                for (int colInc = 0; colInc < scale; colInc++) {
                    for (int rowInc = 0; rowInc < scale; rowInc++) {

                        currCol = (col*scale) + colInc;
                        currRow = (row*scale) + rowInc;

                        if (currCol < width && currRow < height) {
                            currValue += pixelArray[currCol][currRow][channel];
                            count++;
                        }
                    }
                }

                currValue = (int) currValue / count;

                for (int colInc = 0; colInc < scale; colInc++) {
                    for (int rowInc = 0; rowInc < scale; rowInc++) {

                        currCol = (col*scale) + colInc;
                        currRow = (row*scale) + rowInc;

                        if (currCol < width && currRow < height) {
                            pixelArray[currCol][currRow][channel] = (short) currValue;
                        }
                    }
                }
            }
        }
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
