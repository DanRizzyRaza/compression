package org.danrizzyraza.image.image_representations;

public interface ImageRepresentation {
    public short[][][] getPixelArray();
    public int getWidth();
    public int getHeight();
    public void applyDownSample(int channel, int scale);
    public void applyChannelMultiplier(int channel, int multipler);
    public void swapChannels(int channel1, int channel2);
}
