import processing.core.PApplet;

/**
 * Template for programs with Processing graphics output.
 * @author Your Name
 */
public class Sketch extends PApplet {

    String difficulty = "Easy";
    int gameState = 0;
    
    float targetX;
    float targetY;
    float targetSize = 60;

    int score = 0;

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
        targetX = random(100, 700);
        targetY = random(100, 500); 
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
        textSize(20);
        text("Difficulty: " + difficulty, 650, 50);

        fill(255, 255, 255);
        textSize(30);
        text("Score: " + score, 100, 50);

        //  Target
        fill(255, 0, 0);
        ellipse(targetX, targetY, targetSize, targetSize);
        
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
        if (mouseX >= 500 && mouseX <= 650 && mouseY >= 350 && mouseY <= 400) {

            difficulty = "Hard";
        }

        //  Play Button
        if (mouseX >= 300 && mouseX <= 500 && mouseY >= 200 && mouseY <= 260) {

            gameState = 1;
        }

        if (gameState == 1) {
            
            float distance = dist(mouseX, mouseY, targetX, targetY);

            if (distance < targetSize / 2) {

                score++;

                targetX = random(100, 700);
                targetY = random(100, 500);
            }
        }


    }

    /** Additional helper methods below */

}
