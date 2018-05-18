function operation(number, index) {
    return number + 5*index*index;
}

BufferedReader = Java.type("java.io.BufferedReader");
InputStreamReader = Java.type("java.io.InputStreamReader");
System = Java.type("java.lang.System");

var stdin = new BufferedReader(new InputStreamReader(System.in));
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