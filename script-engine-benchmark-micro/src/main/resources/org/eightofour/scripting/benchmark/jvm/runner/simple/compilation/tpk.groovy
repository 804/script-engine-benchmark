package org.eightofour.scripting.benchmark.jvm.runner.simple.compilation

int operation(number, index) {
    return number + 5*index*index
}

Reader stdin = new BufferedReader(new InputStreamReader(System.in))
def numbers = []
(0..11).forEach {
    print(">")
    numbers << Integer.parseInt(stdin.readLine())
}
(10..-1).forEach {
    print("for x = " + it + "y ")
    int result = operation(numbers[it], it)
    if (result > 400) {
        print("IS TOO LARGE")
    } else {
        println("= " + result)
    }
}