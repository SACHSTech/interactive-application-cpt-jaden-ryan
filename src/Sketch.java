import processing.core.PApplet;

/**
 * Template for programs with Processing graphics output.
 * @author Your Name
 */
public class Sketch extends PApplet {

    String difficulty = "Easy";
    int gameState = 0;

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

        if (gameState == 0) {
            drawMenu();
        }
        else if (gameState == 1) {
            drawGame();
        }
    }

    public void drawMenu() {
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

        textSize(25);
        text("Selected: " + difficulty, width / 2, 470);

    }

    public void drawGame() {

        background(50);

        fill(255, 255, 255);
        textSize(40);
        text("GAME STARTED", width / 2, height / 2);

        textSize(25);
        text("Difficulty: " + difficulty, width / 2, height / 2 + 50);
        
    }

    public void mousePressed() {

        //  Easy 
        if (mouseX >= 150 && mouseX <= 300 && mouseY >= 350 && mouseY <= 400) {

            difficulty = "Easy";
        }

        //  Medium
        if (mouseX >= 325 && mouseX <= 475 && mouseY >= 350 && mouseY <= 400) {

            difficulty = "Medium";
        }

        //  Hard
        if (mouseX >= 500 & mouseX <= 650 && mouseY >= 350 && mouseY <= 400) {

            difficulty = "Hard";
        }

        //  Play Button
        if (mouseX >= 300 && mouseX <= 500 && mouseY >= 200 && mouseY <= 260) {

            gameState = 1;
        }



    }

    /** Additional helper methods below */

}
