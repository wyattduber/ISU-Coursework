let x = 5, y = 5, w = 5, h = 5;
let direction = 'E';
let gameEnded = false;
let snake;
let gameInstance = null;
let button = null;
let beenThere = null;

function main() {
    // Check Current Button Value
    button = document.getElementById("startstop");
    if (button.value === "Stop") {
        button.value = "Start";
        clearInterval(gameInstance);
        return;
    }

    //Set up snake and start snake movement
    snake = canvas.getContext("2d");
    snake.fillStyle = "#ff0000";
    snake.fillRect(x, y, w, h);
    gameInstance = setInterval(gameTick, 1);
    button.value = "Stop";
}

function gameTick() {
    // Check if game is over
    if (gameEnded) return;

    // Move snake in current direction
    // Also check pixel ahead of snake
    switch (direction) {
        case 'N':
            y = y - 1;
            snake.fillRect(x, y, w, h);
            beenThere = snake.getImageData(x, y - h, w, h).data[3];
            break;
        case 'E':
            x = x + 1;
            snake.fillRect(x, y, w, h);
            beenThere = snake.getImageData(x + w, y, w, h).data[3];
            break;
        case 'S':
            y = y + 1;
            snake.fillRect(x, y, w, h);
            beenThere = snake.getImageData(x, y + h, w, h).data[3];
            break;
        case 'W':
            x = x - 1;
            snake.fillRect(x, y, w, h);
            beenThere = snake.getImageData(x - w, y, w, h).data[3];
            break;
        }

        //Check if game has ended
        hasGameEnded(gameEnded);
}

function hasGameEnded() {
    // Conditions to stop the snake movement
    if (x < 0 || y < 0 || x > 500 || y > 500 || beenThere > 0) {
        // Since true, end game
        gameEnded = true;
        clearInterval(gameInstance);
        button.value = "Start";
    }
}

function turnleft() {

    // Switch to moving in direction to the left
    switch (direction) {
        case 'N':
            direction = 'W';
            break;
        case 'W':
            direction = 'S';
            break;
        case 'S':
            direction = 'E';
            break;
        case 'E':
            direction = 'N';
            break;
    }
}

function turnright() {

    // Switch to moving in direction to the right
    switch (direction) {
        case 'N':
            direction = 'E';
            break;
        case 'E':
            direction = 'S';
            break;
        case 'S':
            direction = 'W';
            break;
        case 'W':
            direction = 'N';
            break;
    }
}