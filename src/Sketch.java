import processing.core.PApplet;

/**
 * Template for programs with Processing graphics output.
 * @author Your Name
 */
public class Sketch extends PApplet {
    public static void main(String[] args) {
        PApplet.main("Sketch");
    }

    @Override
    public void settings() {
        size(800, 600); 
    }

    @Override
    public void setup() {
        textAlign(CENTER, CENTER);
    }

    @Override
    public void draw() {

        background(30, 30, 30);

        //  Title
        fill(255, 255, 255);
        textSize(50);
        text("AIM TRAINER", width / 2, 100);

        //  Play Button
        fill(0, 200, 0);
        rect(300, 200, 200, 60, 10);

        fill(255, 255, 255);
        textSize(30);
        text("PLAY", 400, 230);

        //  Difficulty
        fill(100);
        rect(150, 350, 150, 50, 10);
        rect(325, 350, 150, 50, 10);
        rect(500, 350, 150, 50, 10);

        fill(255, 255, 255);
        textSize(20);
        text("EASY", 225, 375);
        text("MEDIUM", 400, 375);
        text("HARD", 575, 375);

    }

    /** Additional helper methods below */

}
