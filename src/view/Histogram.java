package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import model.ImageUtilModel;

public interface Histogram {


  JPanel drawHistogram(String type);/*
    List<Rectangle> bars = new ArrayList<Rectangle>();
    int y = 256;
    for (int x = 0; x < 256; x += 1) {
      Rectangle bar;
      for (int i = 0; i < 256; i += 1) {
        int value;
        switch (type) {
          case "red":
            value = redVals.get(i);
            break;
          case "green":
            value = greenVals.get(i);
            break;
          case "blue":
            value = blueVals.get(i);
            break;
          case "intensity":
            value = intensityVals.get(i);
            break;
          default:
            throw new IllegalArgumentException("Unsupported histogram type.");
        }
        bar = new Rectangle(x, y, 1, value);
        bars.add(bar);
      }
    }
    return bars;
  }*/
}
