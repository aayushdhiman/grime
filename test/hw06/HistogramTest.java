package hw06;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.*;

import model.AbstractUtilModelMacro;
import model.FileTypeUtilModel;
import view.Histogram;
import view.HistogramImpl;

import static model.AbstractUtilModelMacro.FileType.PNG;
import static org.junit.Assert.fail;

public class HistogramTest {

  AbstractUtilModelMacro image;
  Histogram histogram;
  //String filename;


  @Before
  public void init() {
    this.image = new FileTypeUtilModel(PNG);
    //this.filename = "6Color.png";
    try {
      this.image.loadImage("res/6ColorRGB.png", "six");
    } catch (FileNotFoundException e) {
      fail(e.getMessage());
    }
    List<List<Color>> pixels = this.image.getImage("six");
    this.histogram = new HistogramImpl(pixels);
  }

  @Test
  public void testHistogram() {
    JPanel redHistogram = this.histogram.drawHistogram("red");
    JPanel greenHistogram = this.histogram.drawHistogram("green");
    JPanel blueHistogram = this.histogram.drawHistogram("blue");
    JPanel intensityHistogram = this.histogram.drawHistogram("intensity");


  }

}
