package org.danrizzyraza.image;

import org.danrizzyraza.image.image_representations.ImageRepresentation;

public class ChannelTransformations {
    public static void generalDownSample(ImageRepresentation imageRepresentation, int channel, int scale) {
        short[][][] pixelArray = imageRepresentation.getPixelArray();
        int width = imageRepresentation.getWidth();
        int height = imageRepresentation.getHeight();

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
}
