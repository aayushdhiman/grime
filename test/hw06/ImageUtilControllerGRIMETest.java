package hw06;

import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.*;

import controller.ImageUtilControllerGRIME;
import jdk.jfr.BooleanFlag;
import model.AbstractUtilModelMacro;
import model.FileTypeUtilModel;
import model.ImageUtilModelMacro;
import view.ImageUtilView;
import view.ImageUtilViewFrame;
import view.JFrameView;

public class ImageUtilControllerGRIMETest {
  private ImageUtilControllerGRIME controller;
  private ImageUtilModelMacro model;
  private Appendable appendable;


  @Before
  public void init(){
    model = new FileTypeUtilModel(AbstractUtilModelMacro.FileType.PPM);
    appendable = new StringBuilder();
    controller = new ImageUtilControllerGRIME(model, appendable);
    controller.startEditor();
  }

  @Test
  public void testActionPerformed() {
    ActionEvent[] actionEvents = new ActionEvent[] {
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "Open file"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "Save file"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "red-component"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "green-component"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "blue-component"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "value-component"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "intensity-component"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "luma-component"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "horizontal-flip"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "vertical-flip"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "brighten"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "greyscale"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "blur"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "sepia"),
      new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "sharpen")
    };

    // what does this do??
    for(ActionEvent ae : actionEvents) {
      controller.actionPerformed(ae);
    }
  }
}
