import java.io.InputStreamReader;
import java.io.StringReader;

import controller.ImageUtilController;
import controller.ImageUtilControllerGRIME;
import controller.ImageUtilControllerMIME;
import model.AbstractUtilModelMacro;
import model.FileTypeUtilModel;
import model.ImageUtilModelMacro;
import view.ImageUtilView;
import view.PPMUtilView;


/**
 * Class that has the {@code main} method for the {@code ImageUtil class.} Creates the
 * controller and runs it.
 */
public class ImageUtil {
  /**
   * Run file for {@code ImageUtil class.} Creates an {@code Appendable} object and the
   * model as an {@code ImageUtilModelMacro}. Depending on the command line
   * arguments (processed as {@code args}), it determines whether to create the view or to run
   * the controller. If there are no arguments, it creates the controller and starts it. If there
   * is one command line input, and it is -text, then it creates the view and starts
   * the controller, allowing for the user to use the text based editor. If the command line
   * arguments are -file script.txt, then the controller will run the script with the text
   * based editor and end the program.
   *
   * @param args the user input/command line arguments.
   */
  public static void main(String[] args) {
    Appendable appendable = System.out;
    ImageUtilModelMacro model = new FileTypeUtilModel(AbstractUtilModelMacro.FileType.PPM);
    if (args.length == 0) {
      ImageUtilController controller = new ImageUtilControllerGRIME(model, appendable);
      controller.startEditor();
    } else if (args.length == 2) {
      if (args[0].equals("-file")) {
        ImageUtilView view = new PPMUtilView(appendable);
        Readable readable = new StringReader("read-script " + args[1]);
        ImageUtilController controller = new ImageUtilControllerMIME(model, view, readable);
        controller.startEditor();
      } else {
        throw new IllegalArgumentException("Improper command line input.");
      }
    } else if (args.length == 1) {
      if (args[0].equals("-text")) {
        ImageUtilView view = new PPMUtilView(appendable);
        Readable readable = new InputStreamReader(System.in);
        ImageUtilController controller = new ImageUtilControllerMIME(model, view, readable);
        controller.startEditor();

      }
    } else {
      throw new IllegalArgumentException("Unable to interpret command line input.");
    }
  }
}

