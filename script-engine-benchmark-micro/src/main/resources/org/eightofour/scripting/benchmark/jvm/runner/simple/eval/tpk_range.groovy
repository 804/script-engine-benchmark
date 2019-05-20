package org.eightofour.scripting.benchmark.jvm.runner.simple.eval

int operation(number, index) {
    return number + 5*index*index
}

int getRandomInt(int min, int max) {
    return Math.floor(Math.random() * (max - min + 1)) + min
}

def numbers = []
(0..11).forEach {
    numbers << getRandomInt(0, 500)
    print("> ${numbers[it]}")
}
(10..-1).forEach {
    print("for x = ${it} y ")
    int result = operation(numbers[it], it)
    if (result > 400) {
        print("IS TOO LARGE")
    } else {
        println("= " + result)
    }
}