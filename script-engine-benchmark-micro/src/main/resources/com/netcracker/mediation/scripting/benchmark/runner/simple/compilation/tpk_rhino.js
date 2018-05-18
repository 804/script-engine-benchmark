function operation(number, index) {
    return number + 5*index*index;
}

var stdin = new Packages.java.io.BufferedReader(
    new Packages.java.io.InputStreamReader(
        Packages.java.lang.System.in
    )
);
var numbers = [];
for (var i = 0; i < 11; i++) {
    print(">");
    numbers[i] = Packages.java.lang.Integer.parseInt(stdin.readLine())
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