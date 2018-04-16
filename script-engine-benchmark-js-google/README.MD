##JavaScript engines Google benchmarks

Source: [google-v8](https://github.com/v8/v8/tree/master/benchmarks) 

###Step 1 <br/> _Dataset preparing._ 
1. Create jar with dependencies: 
   `mvn clean compile assembly:single`
2. Run it as:
 - `java -cp script-engine-benchmark-js-google-version.jar com.netcracker.mediation.scripting.benchmark.runner.rhino.RhinoBenchmark`
 - `java -cp script-engine-benchmark-js-google-version.jar com.netcracker.mediation.scripting.benchmark.runner.nashorn.NashornBenchmark`
<br/>
#### Optional parameters (need to specify as `key=value`):
 - _**iterations**_: iteration count (`500` by default)
 - _**benchmark_js_path**_: JavaScript benchmark directory (`script-engine-benchmark-js-google/src/main/js/` by default)
 - _**result_path**_: result dataset output directory (`script-engine-benchmark-js-google/target/` by default)

###Step 2 <br/> _Dataset analysis._ 
#### Run preparing:
1. Move folders `results` and `warmup` near to _**performance.ipynb**_ dependencies (see `script-engine-benchmark-js-google/analisys/` folder in repository)
2. Install Python 3.x and follow dependencies in your environment:
 - _**jupyter**_
 - _**matplotlib**_
 - _**sympy**_
 - _**scipy**_
 
Then you can run _**performance.ipynb**_ file in IPython/Jupyter.

#### Notebook parameters:
 - _**run_count**_: count of runs used in analysis of performance dynamic (see 2nd code input in notebook)
 - _**measure_count**_: count of last analysed runs for mean scoring analysis (see 2nd code input in notebook)
 
###Also you can see already handled dataset and analisys result:
 - `script-engine-benchmark-js-google/analisys/data/`: analysed datasets
 - `script-engine-benchmark-js-google/analisys/performance.html`: analisys result (for datasets specified above)