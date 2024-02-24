package org.danrizzyraza.image.image_representations;

public interface ImageRepresentation {
    short[][][] getPixelArray();

    public void applyDownSample(int channel, int scale);
}
