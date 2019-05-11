package org.eightofour.scripting.benchmark.jvm.runner.simple.invocable

def call() {
    int a = 1
    return a
}

class Result {
    def call() {
        int a = 1
        return a
    }
}

__result = new Result()