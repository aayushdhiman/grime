package view;

import java.awt.Color;
import java.io.File;
import java.util.List;

import javax.swing.*;

/**
 * An interface that extends ImageUtilView to allow the controller to access some methods from
 * the view for a JFrame. This facilitates the controller updating the view.
 */
public interface ImageUtilViewFrame extends ImageUtilView {
  /**
   * Builds a BufferedImage from the given {@code List} of {@code List} of {@code Color} objects.
   *
   * @param pixels each pixel of the image
   * @param width  the width of the iamge
   * @param height the height of the image
   */
  void buildImage(List<List<Color>> pixels, int width, int height);

  /**
   * Shows the pop up that allows the user to easily pick a file to load.
   *
   * @return a {@code File} object
   */
  File showFileOpenPopUp();

  /**
   * Shows the popup that allows the user to easily pick a place to save the file.
   *
   * @return a {@code File} object
   */
  File showFileSavePopUp();

  /**
   * Updates the image on the panel to the current state of the BufferedImage.
   */
  void updateImage();

  /**
   * Gets the value from the increment {@code JTextField} on the panel.
   *
   * @return the value of the {@code JTextField}
   */
  String getIncrement();

  /**
   * Updates the histogram on the panel to the current state of the Histogram.
   */
  void updateHistogram(JPanel histogram);

}
