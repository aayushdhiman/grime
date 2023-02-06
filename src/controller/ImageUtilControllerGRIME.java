package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.AbstractUtilModelMacro.FileType;
import model.BlurMacroCommand;
import model.FileTypeUtilModel;
import model.GreyscaleMacro;
import model.ImageUtilModel.Grayscale;
import model.ImageUtilModelMacro;
import model.MacroCommand;
import model.SepiaMacro;
import model.SharpenMacroCommand;
import view.Histogram;
import view.HistogramImpl;
import view.ImageUtilViewFrame;
import view.JFrameView;

/**
 * A controller for the ImageUtilViewFrame. Implements ActionListener so that it can act as a
 * listener for the JFrame.
 */
public class ImageUtilControllerGRIME implements ImageUtilController, ActionListener {
  private ImageUtilModelMacro model;
  private ImageUtilViewFrame view;
  private final Appendable appendable;
  private String referenceName;
  private Histogram histogram;

  /**
   * Creates an ImageUtilControllerGRIME with an ImageUtilModelMacro and an ImageUtilViewFrame.
   * @param model the model that will be used to complete the controller's operations
   * @param appendable the appendable that the view will print errors to
   */
  public ImageUtilControllerGRIME(ImageUtilModelMacro model, Appendable appendable) {
    this.model = model;
    this.appendable = appendable;
  }

  /**
   * Tries to complete the load command, and prints a message with the view if it was
   * successful or not.
   *
   * @param filepath         the file to load
   * @param newReferenceName the name to call the loaded file
   */
  private void tryLoad(String filepath, String newReferenceName) {
    String fileType = filepath.substring(filepath.length() - 3);
    FileType ending = null;
    switch (fileType) {
      case "png":
        ending = FileType.PNG;
        break;
      case "jpg":
        ending = FileType.JPG;
        break;
      case "bmp":
        ending = FileType.BMP;
        break;
      case "ppm":
        ending = FileType.PPM;
        break;
      default:
        // view prints that there was an error
        try {
          view.writeMessage("Illegal filetype to save as.");
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
        break;
    }
    try {
      this.model = new FileTypeUtilModel(ending);
      model.loadImage(filepath, newReferenceName);
      view.buildImage(model.getImage(newReferenceName), model.getWidth(newReferenceName),
              model.getHeight(newReferenceName));
      this.histogram = new HistogramImpl(this.model.getImage(this.referenceName));
//      this.view.updateHistogram(this.histogram.drawHistogram("Red"));
      this.view.updateImage();
//      this.view.updateHistogram(this.histogram.drawHistogram());
    } catch (IndexOutOfBoundsException e) {
      try {
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (FileNotFoundException | IllegalStateException e) {
      try {
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }
  }

  /**
   * Tries to save the file.
   *
   * @param filePath          the filepath to save to
   * @param filenameReference the file to save
   */
  private void trySave(String filePath, String filenameReference) {
    String fileType = filePath.substring(filePath.length() - 3);
    FileType ending = null;
    switch (fileType) {
      case "png":
        ending = FileType.PNG;
        break;
      case "jpg":
        ending = FileType.JPG;
        break;
      case "bmp":
        ending = FileType.BMP;
        break;
      case "ppm":
        ending = FileType.PPM;
        break;
      default:
        // view prints that there was an error
        try {
          view.writeMessage("Illegal filetype to save as.");
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
        break;
    }

    try {
      ImageUtilModelMacro tempModel = new FileTypeUtilModel(ending);
      tempModel.addImage(this.model.getImage(filenameReference), filenameReference);
      tempModel.saveImage(filePath, filenameReference);
    } catch (IOException e) {
      try {
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }
  }

  /**
   * Tries to complete the red-component command.
   *
   * @param filename          the file to greyscale
   * @param filenameReference the name of the greyscaled file
   */
  private void tryRedComponent(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      model.grayscale(Grayscale.Red, filename, filenameReference);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
        view.buildImage(this.model.getImage(filename), this.model.getWidth(filename),
                this.model.getHeight(filename));
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the green-component command.
   *
   * @param filename          the file to greyscale
   * @param filenameReference the name of the greyscaled file
   */
  private void tryGreenComponent(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      model.grayscale(Grayscale.Green, filename, filenameReference);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the blue-component command.
   *
   * @param filename          the file to greyscale
   * @param filenameReference the name of the greyscaled file
   */
  private void tryBlueComponent(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      model.grayscale(Grayscale.Blue, filename, filenameReference);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the value-component command.
   *
   * @param filename          the file to greyscale
   * @param filenameReference the name of the greyscaled file
   */
  private void tryValueComponent(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      model.grayscale(Grayscale.Value, filename, filenameReference);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the intensity-component command.
   *
   * @param filename          the file to greyscale
   * @param filenameReference the name of the greyscaled file
   */
  private void tryIntensityComponent(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      model.grayscale(Grayscale.Intensity, filename, filenameReference);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the luma-component command.
   *
   * @param filename          the file to greyscale
   * @param filenameReference the name of the greyscaled file
   */
  private void tryLumaComponent(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      model.grayscale(Grayscale.Luma, filename, filenameReference);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the brighten command.
   *
   * @param increment         how much to increase/decrease the brightness by
   * @param filename          the file to brighten or darken
   * @param filenameReference what to call the new file
   */
  private void tryBrighten(String increment, String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      model.brighten(Integer.parseInt(increment), filename, filenameReference);
    } catch (NumberFormatException e) {
      try {
        completedCommand = false;
        view.writeMessage("Increment is not a number.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the horizontal-flip command.
   *
   * @param filename          the file to flip
   * @param filenameReference the name of the new file
   */
  private void tryHorizontal(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      model.flipHorizontal(filename, filenameReference);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the vertical-flip command.
   *
   * @param filename          the file to flip
   * @param filenameReference the name of the new file
   */
  private void tryVertical(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      model.flipVertical(filename, filenameReference);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the blur command.
   *
   * @param filename          the file to blur
   * @param filenameReference the name of the new file
   */
  private void tryBlur(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      MacroCommand blur = new BlurMacroCommand(filename, filenameReference);
      model.execute(blur);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the sharpen command.
   *
   * @param filename          the file to sharpen
   * @param filenameReference the name of the new file
   */
  private void trySharpen(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      MacroCommand sharpen = new SharpenMacroCommand(filename, filenameReference);
      model.execute(sharpen);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the sepia command.
   *
   * @param filename          the file to color transform
   * @param filenameReference the name of the new file
   */
  private void trySepia(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      MacroCommand sepia = new SepiaMacro(filename, filenameReference);
      model.execute(sepia);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  /**
   * Tries to complete the sepia command.
   *
   * @param filename          the file to color transform
   * @param filenameReference the name of the new file
   */
  private void tryGreyscale(String filename, String filenameReference) {
    boolean completedCommand = true;
    try {
      MacroCommand greyscale = new GreyscaleMacro(filename, filenameReference);
      model.execute(greyscale);
    } catch (IndexOutOfBoundsException e) {
      try {
        completedCommand = false;
        view.writeMessage("Not enough inputs.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalArgumentException e) {
      try {
        completedCommand = false;
        view.writeMessage("That image hasn't been loaded yet.");
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (IllegalStateException e) {
      try {
        completedCommand = false;
        view.writeMessage(e.getMessage());
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    if (completedCommand) {
      view.buildImage(model.getImage(filenameReference), model.getWidth(filenameReference),
              model.getHeight(filenameReference));
      this.view.updateImage();

    }
  }

  @Override
  public void startEditor() throws IllegalArgumentException {
    this.view = new JFrameView(appendable, this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Open file":
        File f = this.view.showFileOpenPopUp();
        if (f != null) {
          this.referenceName = f.getName().substring(0, f.getName().length() - 4);
          this.tryLoad(f.getAbsolutePath(), this.referenceName);
        }
        break;
      case "Save file":
        File saveFile = this.view.showFileSavePopUp();
        if (saveFile != null) {
          this.trySave(saveFile.getAbsolutePath(), this.referenceName);
        }
        break;
      case "red-component":
        this.tryRedComponent(this.referenceName, this.referenceName + "Red");
        this.view.updateImage();
        this.referenceName = this.referenceName + "Red";
        break;
      case "green-component":
        this.tryGreenComponent(this.referenceName, this.referenceName + "Green");
        this.view.updateImage();
        this.referenceName = this.referenceName + "Green";
        break;
      case "blue-component":
        this.tryBlueComponent(this.referenceName, this.referenceName + "Blue");
        this.view.updateImage();
        this.referenceName = this.referenceName + "Blue";
        break;
      case "value-component":
        this.tryValueComponent(this.referenceName, this.referenceName + "Value");
        this.view.updateImage();
        this.referenceName = this.referenceName + "Value";
        break;
      case "intensity-component":
        this.tryIntensityComponent(this.referenceName, this.referenceName + "Inten");
        this.view.updateImage();
        this.referenceName = this.referenceName + "Inten";
        break;
      case "luma-component":
        this.tryLumaComponent(this.referenceName, this.referenceName + "Luma");
        this.view.updateImage();
        this.referenceName = this.referenceName + "Luma";
        break;
      case "horizontal-flip":
        this.tryHorizontal(this.referenceName, this.referenceName + "H");
        this.referenceName = this.referenceName + "H";
        this.view.updateImage();
        break;
      case "vertical-flip":
        this.tryVertical(this.referenceName, this.referenceName + "V");
        this.view.updateImage();
        this.referenceName = this.referenceName + "V";
        break;
      case "brighten":
        this.tryBrighten(view.getIncrement(), this.referenceName, this.referenceName + "Bright");
        this.view.updateImage();
        this.referenceName = this.referenceName + "Bright";
      case "greyscale":
        this.tryGreyscale(this.referenceName, this.referenceName + "Grey");
        this.view.updateImage();
        this.referenceName = this.referenceName + "Grey";
      case "blur":
        this.tryBlur(this.referenceName, this.referenceName + "Blur");
        this.view.updateImage();
        this.referenceName = this.referenceName + "Blur";
      case "sepia":
        this.trySepia(this.referenceName, this.referenceName + "Sepia");
        this.view.updateImage();
        this.referenceName = this.referenceName + "Sepia";
      case "sharpen":
        this.trySharpen(this.referenceName, this.referenceName + "Sharp");
        this.view.updateImage();
        this.referenceName = this.referenceName + "Sharp";
      default:
        break;
    }
  }
}
