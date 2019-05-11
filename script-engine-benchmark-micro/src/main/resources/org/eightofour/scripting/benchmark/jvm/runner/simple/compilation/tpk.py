from java.io import BufferedReader, InputStreamReader
from java.lang import System, Integer

def operation(number, index):
    return number + 5*index*index

stdin = BufferedReader(InputStreamReader(System.in))

numbers = []
for i in xrange(11):
    print ">"
    numbers.append(Integer.parseInt(stdin.readLine()))

for i in xrange(10, -1, -1):
    print "for x = " + i + "y "
    result = operation(numbers[i], i)
    if result > 400:
        print "IS TOO LARGE"
    else:
        print "= " + result + "\n"