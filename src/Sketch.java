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
    //new variables to calculate and store accuracy
    int miss = 0;
    int accuracy = 0;

    int startTime;
    int timeLeft;

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
        else if (gameState == 2) {
            drawEndScreen();
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

        // Timer
        timeLeft = 30 - (millis() - startTime) / 1000;
        text("Time: " + timeLeft, 400, 50);

        if (timeLeft <= 0) {
            gameState = 2;
        }

        //  Target
        fill(255, 0, 0);
        ellipse(targetX, targetY, targetSize, targetSize);
        
    }
    public void drawEndScreen() {
        background(50);

        // End message
        fill(255);
        textSize(50);

        if (score <= 5) {
            text("Good Try :(", 385, 150);
        }
        else if (score <= 15) {
            text("Keep up the great work!", 385, 150);
        }
        else if (score <= 30) {
            text("Good Job!", 385, 150);
        }
        else {
            text("Aim God!", 385, 150);
        }

        // Accuracy
        if (score + miss > 0) {
            accuracy = (int)((float) score / (score + miss) * 100);
        } else {
            accuracy = 0;
        }

        // Stats
        textSize(30);
        text("Score: " + score, 385, 250);
        text("Accuracy: " + accuracy + "%", 385, 320);

        // Return button
        fill(100);
        rect(240, 400, 140, 60);

        fill(255);
        textSize(25);
        text("Return", 310, 428);

        // Exit button
        fill(100);
        rect(420, 400, 140, 60);

        fill(255);
        text("Exit", 490, 428);
    }

    public void mousePressed() {


        if (gameState == 0) {

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

        // Size of targets get smaller with difficulty
        if (difficulty.equals("Easy")) {
            targetSize = 60;
        }
        else if (difficulty.equals("Medium")) {
            targetSize = 40;
        }
        else if (difficulty.equals("Hard")) {
            targetSize = 25;
        }

        //  Play Button
        if (mouseX >= 300 && mouseX <= 500 && mouseY >= 200 && mouseY <= 260) {

            gameState = 1;
            startTime = millis();
        }

        }
        
        if (gameState == 1) {
            
            float distance = dist(mouseX, mouseY, targetX, targetY);

            if (distance < targetSize / 2) {

                score++;

                targetX = random(100, 700);
                targetY = random(100, 500);  
            } else {
                miss++;
            }
        }
        //new
        if (gameState == 2) {
            if (mouseX >= 240 && mouseX <= 380 && mouseY >= 400 && mouseY <= 460) {

                gameState = 0;
                score = 0;
                accuracy = 0;
                miss = 0;
            }

            // Exit button
            if (mouseX >= 420 && mouseX <= 560 && mouseY >= 400 && mouseY <= 460) {

                exit();
            }
        }

    }

}