package de.propra.defense.ui;

import de.propra.defense.Unit;
import de.propra.defense.UnitType;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JFrame;

public class GamePanel extends JFrame {

  public static final int H =  15;
  public static final int W = 30;
  public static final int TSZ = 32;


  private final Toolkit toolkit = Toolkit.getDefaultToolkit();
  private final Map<UnitType, Image> units = new HashMap<>();
  private final Image[] bg = new Image[4];

  private final Feld[][] felder = new Feld[H][W];
  private final Random random = new Random();

  private void createFeld() {
    for (int row = 0; row < H; row++) {
      for (int col = 0; col < W; col++) {
        int bgTile = random.nextInt(4);
        Feld f = new Feld(bg[bgTile]);
        felder[row][col] = f;
        add(f);
      }
    }
  }

  public GamePanel(String titel) {
    super(titel);
    loadTiles();
    createFeld();
  }

  private void loadTiles() {
    MediaTracker tracker = new MediaTracker(this);
    loadBackgroundTiles(tracker);
    loadUnitTiles(tracker);
    try {
      tracker.waitForAll();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void loadUnitTiles(MediaTracker tracker) {
    units.put(UnitType.FROG, load(5, tracker, "frog.jpg"));
    units.put(UnitType.BUG1, load(6, tracker, "bug1.jpg"));
    units.put(UnitType.BUG2, load(7, tracker, "bug_s.png"));
    units.put(UnitType.CROW, load(8, tracker, "crow_s.png"));
    units.put(UnitType.SCARECROW, load(9, tracker, "scarecrow.jpg"));
    units.put(UnitType.PLANT, load(10, tracker, "plant.jpg"));
  }

  private void loadBackgroundTiles(MediaTracker tracker) {
    for (int i = 0; i < 4; i++) {
      String filename = "gras" + i + ".jpg";
      Image image = getImage(filename);
      tracker.addImage(image, i);
      bg[i] = image;
    }
  }

  private Image load(int id, MediaTracker tracker, String filename) {
    Image image = getImage(filename);
    tracker.addImage(image, id);
    return image;
  }

  private Image getImage(String filename) {
    URL url = GamePanel.class.getResource(filename);
    return toolkit.getImage(url);
  }

  public void placeUnit(UnitType unit, int row, int col) {
    felder[row][col].setCurrentImage(units.get(unit));
  }

  public void removeUnit(int row, int col) {
    felder[row][col].setCurrentImage(null);
  }


  public boolean occupied(int row, int col) {
    return felder[row][col].occupied();
  }
}
