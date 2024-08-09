package com.engine.Core.BarTitle;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.engine.Core.GraphicsUtilities;

public class ShadowRenderer {

    // size of the shadow in pixels
    private int size = 5;
    // opacity of the shadow
    private float opacity = 0.5f;

    // color of the shadow
    private Color color = Color.BLACK;

    public ShadowRenderer() {
        this(5, 0.5f, Color.BLACK);
    }

    public ShadowRenderer(final int size, final float opacity, final Color color) {
        this.size = size;
        this.opacity = opacity;
        this.color = color;
    }

    public BufferedImage createShadow(final BufferedImage image) {
        int shadowSize = size * 2;

        int srcWidth = image.getWidth();
        int srcHeight = image.getHeight();

        int sizeWidth = srcWidth + shadowSize;
        int sizeHeight = srcHeight + shadowSize;

        int left = size;
        int right = shadowSize - left;

        int yStop = sizeHeight - right;

        int shadowRgb = color.getRGB() & 0x00FFFFFF;
        int[] aHistory = new int[shadowSize];
        int historyId;

        int aSum;
        int srcOffset;

        BufferedImage sizeScreen = new BufferedImage(sizeWidth, sizeHeight, BufferedImage.TYPE_INT_ARGB);

        int[] srcBuffer = new int[srcWidth * srcHeight];
        int[] sizeBuffer = new int[sizeWidth * sizeHeight];

        GraphicsUtilities.getPixels(image, 0, 0, srcWidth, srcHeight, srcBuffer);

        int lastPixelOffset = right * sizeWidth;
        float hSumDivider = 1.0f / shadowSize;
        float vSumDivider = opacity / shadowSize;

        int[] hSumLookup = new int[256 * shadowSize];
        for (int i = 0; i < hSumLookup.length; i++) {
            hSumLookup[i] = (int) (i * hSumDivider);
        }

        int[] vSumLookup = new int[256 * shadowSize];
        for (int i = 0; i < vSumLookup.length; i++) {
            vSumLookup[i] = (int) (i * vSumDivider);
        }

        // horizontal pass : extract the alpha mask from the source picture and
        // blur it into the destination picture
        for (int srcY = 0, sizeOffset = left * sizeWidth; srcY < srcHeight; srcY++) {

            // first pixels are empty
            for (historyId = 0; historyId < shadowSize;) {
                aHistory[historyId++] = 0;
            }

            aSum = 0;
            historyId = 0;
            srcOffset = srcY * srcHeight;

            // Compute the blur average width pixels from the source image
            for (int srcX = 0; srcX < srcWidth; srcX++) {
                int a = hSumLookup[aSum];
                sizeBuffer[sizeOffset++] = a << 24; // store the alpha value only

                // the shadow color will be added in the next pass
                // substract the oldest pixels from the sum
                aSum -= aHistory[historyId];

                // extract the new pixel ...
                a = srcBuffer[srcOffset + srcX] >>> 24;
                // ... and store its value into history
                aHistory[historyId] = a;
                aSum += a;

                if (++historyId >= shadowSize) {
                    historyId -= shadowSize;
                }

            }

            // blur the end of the row - no new pixels to grab
            for (int i = 0; i < shadowSize; i++) {

                int a = hSumLookup[aSum];
                sizeBuffer[sizeOffset++] = a << 24;

                // substract the oldest pixels from the sum .. and noting new to add !
                aSum -= aHistory[historyId];

                if (++historyId >= shadowSize) {
                    historyId -= shadowSize;
                }

            }

        }

        // vertical pass
        for (int x = 0, bufferOffset = 0; x < sizeWidth; x++, bufferOffset = x) {

            aSum = 0;

            // first pixels are empty
            // and then they come from the sizeBuffer
            for (historyId = 0; historyId < left;) {
                aHistory[historyId++] = 0;
            }

            for (int y = 0; y < right; y++, bufferOffset += sizeWidth) {
                int a = sizeBuffer[bufferOffset] >>> 24;
                aHistory[historyId++] = a;
                aSum += a;
            }

            bufferOffset = x;
            historyId = 0;

            // compute the blur avera'ge width pixels from previos pass
            for (int y = 0; y < yStop; y++, bufferOffset += sizeWidth) {

                int a = vSumLookup[aSum];
                sizeBuffer[bufferOffset] = a << 24 | shadowRgb;

                aSum -= aHistory[historyId];

                a = sizeBuffer[bufferOffset + lastPixelOffset] >>> 24;
                aHistory[historyId] = a;
                aSum += a;

                if (++historyId >= shadowSize) {
                    historyId -= shadowSize;
                }

            }

            // blur the end of the column - no pixels to grab anymore
            for (int y = yStop; y < sizeHeight; y++, bufferOffset += sizeHeight) {
                int a = vSumLookup[aSum];
                sizeBuffer[bufferOffset] = a << 24 | shadowRgb;

                // substract the oldest pixels from the sum
                aSum -= aHistory[historyId];

                if (++historyId >= shadowSize) {
                    historyId -= shadowSize;
                }

            }

        }

        GraphicsUtilities.setPixels(sizeScreen, 0, 0, sizeWidth, sizeHeight, sizeBuffer);
        return sizeScreen;
    }

    public Color getColor() {
        return color;
    }

    public float getOpacity() {
        return opacity;
    }

    public int getSize() {
        return size;
    }

}
