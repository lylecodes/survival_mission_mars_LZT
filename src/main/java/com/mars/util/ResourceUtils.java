package com.mars.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceUtils {
    private ResourceUtils() {};

    public static ImageIcon getImageIconScaledToLabelSize(String fileName, JLabel label) {
        BufferedImage bufferedImage = getBufferedImageFromResource(fileName);
        Image scaledInstance = bufferedImage.getScaledInstance(
                label.getWidth(),
                label.getHeight(),
                Image.SCALE_SMOOTH);
        return new ImageIcon(scaledInstance);
    }
    public static ImageIcon getImageIconScaledToLabelSizePopUp(String fileName) {
        BufferedImage bufferedImage = getBufferedImageFromResource(fileName);
        Image scaledInstance = bufferedImage;
        return new ImageIcon(scaledInstance);
    }

    public static BufferedImage getBufferedImageFromResource(String fileName) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(
                    ResourceUtils.class.getClassLoader().getResource(fileName)
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}