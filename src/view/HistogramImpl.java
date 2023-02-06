package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.swing.*;

public class HistogramImpl implements Histogram {

  protected Map<Integer, Integer> redVals;
  protected Map<Integer, Integer> greenVals;
  protected Map<Integer, Integer> blueVals;
  protected Map<Integer, Integer> intensityVals;

  public HistogramImpl(List<List<Color>> pixels) {
    this.redVals = new HashMap<>();
    this.greenVals = new HashMap<>();
    this.blueVals = new HashMap<>();
    this.intensityVals = new HashMap<>();

    List<Integer> redList = new ArrayList<>();
    List<Integer> greenList = new ArrayList<>();
    List<Integer> blueList = new ArrayList<>();
    List<Integer> intensityList = new ArrayList<>();

    for (int h = 0; h < pixels.size(); h += 1) {
      for (int w = 0; w < pixels.get(0).size(); w += 1) {
        Color pixel = pixels.get(h).get(w);
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        int avg = (red + green + blue) / 3;

        redList.add(red);
        greenList.add(green);
        blueList.add(blue);
        intensityList.add(avg);
      }
    }
    for (int i = 0; i < 256; i += 1) {
      this.redVals.put(redList.get(i), redVals.getOrDefault(redList.get(i), 0));
      this.greenVals.put(greenList.get(i), greenVals.getOrDefault(greenList.get(i), 0));
      this.blueVals.put(blueList.get(i), blueVals.getOrDefault(blueList.get(i), 0));
      this.intensityVals.put(intensityList.get(i), intensityVals.getOrDefault(intensityList.get(i), 0));
//      int redFreq = 0;
//      int greenFreq = 0;
//      int blueFreq = 0;
//      int intensityFreq = 0;
//      for (int j = 0; j < 256; j += 1) {
//        if (redList.get(j) == i) {
//          redFreq += 1;
//        }
//        if (greenList.get(j) == i) {
//          greenFreq += 1;
//        }
//        if (blueList.get(j) == i) {
//          blueFreq += 1;
//        }
//        if (intensityList.get(j) == i) {
//          intensityFreq += 1;
//        }
//      }
//      this.redVals.put(i, redFreq);
//      this.greenVals.put(i, greenFreq);
//      this.blueVals.put(i, blueFreq);
//      this.intensityVals.put(i, intensityFreq);
    }
  }

  @Override
  public JPanel drawHistogram(String type) {
    JPanel histogram = new JPanel();
    histogram.setLayout(new BoxLayout(histogram, BoxLayout.X_AXIS));
//    List<Graphics> bars = new ArrayList<Graphics>();
    for (int x = 0; x < 256; x += 1) {
      Graphics bar = histogram.getGraphics();
      //for (int i = 0; i < 256; i += 1) {
      int value;
      switch (type) {
        case "red":
          value = redVals.get(x);break;
        case "green":
          value = greenVals.get(x);
          break;
        case "blue":
          value = blueVals.get(x);
          break;
        case "intensity":
          value = intensityVals.get(x);
          bar.setColor(Color.GRAY);
          break;
        default:
          throw new IllegalArgumentException("Unsupported histogram type.");
      }
      bar.drawRect(x, 255 - value, 1, value);
      switch (type) {
        case "red":
          bar.setColor(Color.RED);
          break;
        case "green":
          bar.setColor(Color.GREEN);
          break;
        case "blue":
          bar.setColor(Color.BLUE);
          break;
        case "intensity":
          bar.setColor(Color.GRAY);
          break;
        default:
          throw new IllegalArgumentException("Unsupported histogram type.");
      }
      histogram.paint(bar);
//        bars.add(bar);
      //}
    }
    return histogram;
  }


}
