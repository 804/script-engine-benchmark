function operation(number, index) {
    return number + 5*index*index;
}

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

var numbers = [];
for (var i = 0; i < 11; i++) {
    numbers[i] = getRandomInt(0, 500);
    print("> " + numbers[i] + "\n");
}
for (var i = 10; i >= 0; i--) {
    print("for x = " + i + "y ");
    var result = operation(numbers[i], i);
    if (result > 400) {
        print("IS TOO LARGE");
    } else {
        print("= " + result + "\n");
    }
}