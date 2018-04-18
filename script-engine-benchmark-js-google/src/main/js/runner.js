// Copyright 2008 the V8 project authors. All rights reserved.
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
//     * Redistributions of source code must retain the above copyright
//       notice, this list of conditions and the following disclaimer.
//     * Redistributions in binary form must reproduce the above
//       copyright notice, this list of conditions and the following
//       disclaimer in the documentation and/or other materials provided
//       with the distribution.
//     * Neither the name of Google Inc. nor the names of its
//       contributors may be used to endorse or promote products derived
//       from this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


load(benchmarkJsPath + 'base.js');
load(benchmarkJsPath + 'richards.js');
load(benchmarkJsPath + 'deltablue.js');
load(benchmarkJsPath + 'crypto.js');
load(benchmarkJsPath + 'raytrace.js');
load(benchmarkJsPath + 'earley-boyer.js');
load(benchmarkJsPath + 'regexp.js');
load(benchmarkJsPath + 'splay.js');
load(benchmarkJsPath + 'navier-stokes.js');

var success = true;
var results;

function PrintResult(name, score, result) {
  print('Benchmark - ' + name);
  var suitResult = {};
  suitResult.case = [];
  for (var i = 0; i < result.length; i++) {
      var caseName = result[i].benchmark.name;
      var caseMean = result[i].time;
      print('Case - ' + caseName + ': ' + caseMean);
      suitResult.case.push({
          name: caseName,
          meanTime: caseMean
      })
  }
  print('Total - ' + score);
  suitResult.score = score;

  results.suits[name] = suitResult;
}


function PrintError(name, error) {
  PrintResult(name, error);
  success = false;
}


function PrintScore(score) {
  if (success) {
    print('----');
    print('Score (version ' + BenchmarkSuite.version + '): ' + score);
    print('----------------------');
    results.totalScore = score;
  }
}

function runBenchmark() {
    results = {
        suits: {}
    };
    BenchmarkSuite.RunSuites({
        NotifyResult: PrintResult,
        NotifyError: PrintError,
        NotifyScore: PrintScore
    });
    var retResult = results;
    results = undefined;
    return retResult;
}