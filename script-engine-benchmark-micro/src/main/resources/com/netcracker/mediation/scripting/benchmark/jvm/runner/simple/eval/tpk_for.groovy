int operation(number, index) {
    return number + 5*index*index
}

int getRandomInt(int min, int max) {
    return Math.floor(Math.random() * (max - min + 1)) + min
}

List<Integer> numbers = []
for (int it = 0; it < 11; it++) {
    numbers << getRandomInt(0, 500)
    print("> " + numbers[it])
}
for (int it = 10; it >= 0; it--) {
    print("for x = " + it + " y ")
    int result = operation(numbers[it], it)
    if (result > 400) {
        print("IS TOO LARGE")
    } else {
        println("= " + result)
    }
}