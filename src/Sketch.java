import processing.core.PApplet;

/**
 * Template for programs with Processing graphics output.
 * @author Jaden and Ryan
 */
public class Sketch extends PApplet {

    String difficulty = "Easy";
    int gameState = 0;
    
    float targetX;
    float targetY;
    float targetSize = 60;

    int score = 0;
    
    int miss = 0;
    int accuracy = 0;

    int startTime;
    int timeLeft;

    //  Difficulty button variables
    int EASY_BUTTON_X = 150;
    int MEDIUM_BUTTON_X = 325;
    int HARD_BUTTON_X = 500;

    int DIFFICULTY_BUTTON_Y = 350;
    int DIFFICULTY_BUTTON_WIDTH = 150;
    int DIFFICULTY_BUTTON_HEIGHT = 50;
    
    //  Play button variables
    int PLAY_BUTTON_X = 300;
    int PLAY_BUTTON_Y = 200;
    int PLAY_BUTTON_WIDTH = 200;
    int PLAY_BUTTON_HEIGHT = 60;

    //  End button variables
    int RETURN_BUTTON_X = 240;
    int RETURN_BUTTON_Y = 400;

    int EXIT_BUTTON_X = 420;
    int EXIT_BUTTON_Y = 400;

    int END_BUTTON_WIDTH = 140;
    int END_BUTTON_HEIGHT = 60;

    //  Array of all difficulty buttons
    String[] difficultyNames = {"EASY", "MEDIUM", "HARD"};


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

        //  Play button
        fill(0, 200, 0);
        rect(PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT, 10);

        fill(255, 255, 255);
        textSize(30);
        text("PLAY", 400, 230);

        //  Difficulty
        fill(100, 100, 100);

        for (int i = 0; i < difficultyNames.length; i++) {

            int x = EASY_BUTTON_X + i * 175;

            rect(x,
                DIFFICULTY_BUTTON_Y,
                DIFFICULTY_BUTTON_WIDTH,
                DIFFICULTY_BUTTON_HEIGHT,
                10);

            fill(255, 255, 255);
            textSize(20);
            text(difficultyNames[i],
                x + DIFFICULTY_BUTTON_WIDTH / 2,
                DIFFICULTY_BUTTON_Y + DIFFICULTY_BUTTON_HEIGHT / 2);

            fill(100);
        }

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

        // Calculates how many seconds are left in the game
        timeLeft = 30 - (millis() - startTime) / 1000;
        text("Time: " + timeLeft, 400, 50);

        if (timeLeft <= 0) {
            gameState = 2;
        }

        //  Target
        fill(255, 0, 0);
        ellipse(targetX, targetY, targetSize, targetSize);  // Target 
        
    }
    
    public void drawEndScreen() {
        background(50);

        //  End message
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

        //  Accuracy = hits / total clicks x 100
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
        fill(100, 100, 100);
        rect(RETURN_BUTTON_X, RETURN_BUTTON_Y, END_BUTTON_WIDTH, END_BUTTON_HEIGHT);

        fill(255, 255, 255);
        textSize(25);
        text("Return", 310, 428);

        // Exit button
        fill(100, 100, 100);
        rect(EXIT_BUTTON_X, EXIT_BUTTON_Y, END_BUTTON_WIDTH, END_BUTTON_HEIGHT);

        fill(255);
        text("Exit", 490, 430);
    }

    public void mousePressed() {

        //  Home Screen
        if (gameState == 0) {

        //  Easy 
        if (mouseX >= EASY_BUTTON_X && 
            mouseX <= EASY_BUTTON_X + DIFFICULTY_BUTTON_WIDTH && 
            mouseY >= DIFFICULTY_BUTTON_Y && 
            mouseY <= DIFFICULTY_BUTTON_Y + DIFFICULTY_BUTTON_HEIGHT ) {

            difficulty = "Easy";
        }

        //  Medium
        if (mouseX >= MEDIUM_BUTTON_X &&
            mouseX <= MEDIUM_BUTTON_X + DIFFICULTY_BUTTON_WIDTH &&
            mouseY >= DIFFICULTY_BUTTON_Y &&
            mouseY <= DIFFICULTY_BUTTON_Y + DIFFICULTY_BUTTON_HEIGHT) {

            difficulty = "Medium";
        }

        //  Hard 
        if (mouseX >= HARD_BUTTON_X &&
            mouseX <= HARD_BUTTON_X + DIFFICULTY_BUTTON_WIDTH &&
            mouseY >= DIFFICULTY_BUTTON_Y && 
            mouseY <= DIFFICULTY_BUTTON_Y + DIFFICULTY_BUTTON_HEIGHT) {

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
        if (mouseX >= PLAY_BUTTON_X &&
            mouseX <= PLAY_BUTTON_X + PLAY_BUTTON_WIDTH &&
            mouseY >= PLAY_BUTTON_Y &&
            mouseY <= PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT) {

                gameState = 1;
                startTime = millis();
            }
        }

        //  Game screen
        if (gameState == 1) {

            //  Distance from mouse click to target center
            float distance = dist(mouseX, mouseY, targetX, targetY);

            if (distance < targetSize / 2) {

                score++;

                targetX = random(100, 700);
                targetY = random(100, 500);     
            } else {
                miss++;
            }
        }

        //  End Screen
        if (gameState == 2) {
            if (mouseX >= RETURN_BUTTON_X && 
                mouseX <= RETURN_BUTTON_X + END_BUTTON_WIDTH &&
                mouseY >= RETURN_BUTTON_Y &&
                mouseY <= RETURN_BUTTON_Y + END_BUTTON_HEIGHT) {

                gameState = 0;
                score = 0;
                accuracy = 0;
                miss = 0;
                startTime = 0;
            }

            // Exit button
            if (mouseX >= EXIT_BUTTON_X && 
                mouseX <= EXIT_BUTTON_X + END_BUTTON_WIDTH &&
                mouseY >= EXIT_BUTTON_Y &&
                mouseY <= EXIT_BUTTON_Y + END_BUTTON_HEIGHT) {

                exit();
            }
        }
    }
}