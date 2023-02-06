package view;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ImageUtilController;

/**
 * Creates a view in a JFrame that allows the user to interact with a GUI.
 */
public class JFrameView extends JFrame implements ImageUtilViewFrame {
  private final Appendable appendable;
  private final JLabel fileOpenDisplay;
  private final JLabel fileSaveDisplay;
  private final JTextField brightenIncrement;
  private BufferedImage img;
  private JLabel imageLabel;
  private JLabel histogramLabel;
  private JScrollPane imageScrollPane;
  private JScrollPane histogramScrollPane;
  private JPanel scrollableHistogram;
  ImageUtilController controller;

  /**
   * Creates a JFrameView. Instantiates all final fields. Adds all panels to a main panel.
   *
   * @param appendable the appendable to which output messages are written
   * @param controller the controller to use as a listener
   */
  public JFrameView(Appendable appendable, ImageUtilController controller) {
    super();
    this.controller = controller;
    this.appendable = appendable;
    setTitle("GRIME");
    setSize(760, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // main panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    // scroll bars on main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // image with scrollbar
    JPanel scrollableImage = new JPanel();
    scrollableImage.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(scrollableImage);

    imageLabel = new JLabel();
    imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(700, 550));
    scrollableImage.add(imageScrollPane);

    // histogram image
    scrollableHistogram = new JPanel();
    scrollableHistogram.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(scrollableHistogram);

    histogramLabel = new JLabel();
    histogramScrollPane = new JScrollPane(histogramLabel);
    histogramScrollPane.setPreferredSize(new Dimension(260, 260));
    scrollableHistogram.add(histogramScrollPane);

    // dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog Boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    // file open
    JPanel fileOpenPanel = new JPanel();
    fileOpenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileOpenPanel);

    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener((ActionListener) controller);
    fileOpenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileOpenPanel.add(fileOpenDisplay);

    // file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);

    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener((ActionListener) controller);
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);

    // red-component
    JPanel redCompPanel = new JPanel();
    redCompPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(redCompPanel);

    JButton redCompButton = new JButton("Red Component");
    redCompButton.setActionCommand("red-component");
    redCompButton.addActionListener((ActionListener) controller);
    redCompPanel.add(redCompButton);

    // green-component
    JPanel greenCompPanel = new JPanel();
    greenCompPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(greenCompPanel);

    JButton greenCompButton = new JButton("Green Component");
    greenCompButton.setActionCommand("green-component");
    greenCompButton.addActionListener((ActionListener) controller);
    greenCompPanel.add(greenCompButton);

    // blue-component
    JPanel blueCompPanel = new JPanel();
    blueCompPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(blueCompPanel);

    JButton blueCompButton = new JButton("Blue Component");
    blueCompButton.setActionCommand("blue-component");
    blueCompButton.addActionListener((ActionListener) controller);
    blueCompPanel.add(blueCompButton);

    // value-component
    JPanel valueCompPanel = new JPanel();
    valueCompPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(valueCompPanel);

    JButton valueCompButton = new JButton("Value Component");
    valueCompButton.setActionCommand("value-component");
    valueCompButton.addActionListener((ActionListener) controller);
    valueCompPanel.add(valueCompButton);

    // intensity-component
    JPanel intensityCompPanel = new JPanel();
    intensityCompPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(intensityCompPanel);

    JButton intensityCompButton = new JButton("Intensity Component");
    intensityCompButton.setActionCommand("intensity-component");
    intensityCompButton.addActionListener((ActionListener) controller);
    intensityCompPanel.add(intensityCompButton);

    // luma-component
    JPanel lumaCompPanel = new JPanel();
    lumaCompPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(lumaCompPanel);

    JButton lumaCompButton = new JButton("Luma Component");
    lumaCompButton.setActionCommand("luma-component");
    lumaCompButton.addActionListener((ActionListener) controller);
    lumaCompPanel.add(lumaCompButton);

    // flip horizontal
    JPanel horizontalFlipPanel = new JPanel();
    horizontalFlipPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(horizontalFlipPanel);

    JButton horizontalFlipButton = new JButton("Horizontal Flip");
    horizontalFlipButton.setActionCommand("horizontal-flip");
    horizontalFlipButton.addActionListener((ActionListener) controller);
    horizontalFlipPanel.add(horizontalFlipButton);

    // flip vertical
    JPanel verticalFlipPanel = new JPanel();
    verticalFlipPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(verticalFlipPanel);

    JButton verticalFlipButton = new JButton("Vertical Flip");
    verticalFlipButton.setActionCommand("vertical-flip");
    verticalFlipButton.addActionListener((ActionListener) controller);
    verticalFlipPanel.add(verticalFlipButton);

    // brighten
    JPanel brightenPanel = new JPanel();
    brightenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(brightenPanel);

    JButton brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("brighten");
    brightenButton.addActionListener((ActionListener) controller);
    brightenPanel.add(brightenButton);

    brightenIncrement = new JTextField("Increment");
    brightenPanel.add(brightenIncrement);

    // greyscale
    JPanel greyscalePanel = new JPanel();
    greyscalePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(greyscalePanel);

    JButton greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("greyscale");
    greyscaleButton.addActionListener((ActionListener) controller);
    greyscalePanel.add(greyscaleButton);

    // sepia
    JPanel sepiaPanel = new JPanel();
    sepiaPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(sepiaPanel);

    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("sepia");
    sepiaButton.addActionListener((ActionListener) controller);
    sepiaPanel.add(sepiaButton);

    // blur
    JPanel blurPanel = new JPanel();
    blurPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(blurPanel);

    JButton blurButton = new JButton("Blur");
    blurButton.setActionCommand("blur");
    blurButton.addActionListener((ActionListener) controller);
    blurPanel.add(blurButton);

    // sharpen
    JPanel sharpenPanel = new JPanel();
    sharpenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(sharpenPanel);

    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen");
    sharpenButton.addActionListener((ActionListener) controller);
    sharpenPanel.add(sharpenButton);

    this.setVisible(true);
  }

  @Override
  public void buildImage(List<List<Color>> pixels, int width, int height) {
    this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int h = 0; h < height; h += 1) {
      for (int w = 0; w < width; w += 1) {
        this.img.setRGB(w, h, pixels.get(h).get(w).getRGB());
      }
    }
  }

  @Override
  public File showFileOpenPopUp() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PNG, PPM, and BMP Images", "jpg", "png", "bmp", "ppm");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileOpenDisplay.setText(f.getAbsolutePath());
      imageLabel.setIcon(new ImageIcon(f.getAbsolutePath()));
      return f;
    }
    return null;
  }

  @Override
  public File showFileSavePopUp() {
    final JFileChooser fSavechooser = new JFileChooser(".");
    int retvalueSave = fSavechooser.showSaveDialog(this);
    if (retvalueSave == JFileChooser.APPROVE_OPTION) {
      File f = fSavechooser.getSelectedFile();
      fileSaveDisplay.setText(f.getAbsolutePath());
      return f;
    }
    return null;
  }

  @Override
  public void updateImage() {
    this.imageLabel.setIcon(new ImageIcon(this.img));
  }

  @Override
  public void updateHistogram(JPanel histogram) {
    this.scrollableHistogram = histogram;
  }

  @Override
  public String getIncrement() {
    return this.brightenIncrement.getText();
  }

  @Override
  public void writeMessage(String message) throws IOException {
    try {
      this.appendable.append(message);
    } catch (Exception e) {
      throw new IOException("Error in writing message to appendable.");
    }
  }
}
