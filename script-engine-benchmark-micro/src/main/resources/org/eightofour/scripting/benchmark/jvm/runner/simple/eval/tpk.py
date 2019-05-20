from java.lang import Math

def operation(number, index):
    return number + 5*index*index

def getRandomInt(min, max):
    return Math.floor(Math.random() * (max - min + 1)) + min

numbers = []
for i in xrange(11):
    numbers.append(getRandomInt(0, 500))
    print "> " + str(numbers[i]) + "\n"

for i in xrange(10, -1, -1):
    print "for x = " + str(i) + "y "
    result = operation(numbers[i], i)
    if result > 400:
        print "IS TOO LARGE"
    else:
        print "= " + str(result) + "\n"