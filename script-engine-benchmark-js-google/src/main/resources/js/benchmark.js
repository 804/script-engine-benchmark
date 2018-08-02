var benchmarksResult = [];

for (var i = 0; i < iterations; i++) {
    print('iteration ' + (i + 1));
    print('----');
    benchmarksResult.push(runBenchmark());
}

writeToFile(resultPath + 'results/' + engine + '.json', JSON.stringify(benchmarksResult));

