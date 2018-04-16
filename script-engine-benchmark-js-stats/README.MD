##JavaScript engines statistic benchmarks

Source: [dromaeo](http://dromaeo.com/?dromaeo)

###Step 1 <br/> _Dataset preparing._ 
1. Create jar with dependencies: 
   `mvn clean compile assembly:single`
2. Run it as:
 - `java -cp script-engine-benchmark-js-stats-version.jar com.netcracker.mediation.scripting.benchmark.runner.rhino.RhinoBenchmark`
 - `java -cp script-engine-benchmark-js-stats-version.jar com.netcracker.mediation.scripting.benchmark.runner.nashorn.NashornBenchmark`
<br/>
#### Optional parameters (need to specify as `key=value`):
 - _**warmup_iter**_: warmup iteration count (`5` by default)
 - _**benchmark_js_path**_: JavaScript benchmark directory (`script-engine-benchmark-js-stats/src/main/js/` by default)
 - _**result_path**_: result dataset output directory (`script-engine-benchmark-js-stats/target/` by default)

###Step 2 <br/> _Dataset analysis._ 
#### Run preparing:
1. Move folders `results` and `warmup` near to _**ScriptEnginePerformance.ipynb**_ dependencies (see `script-engine-benchmark-js-stats/analisys/` folder in repository)
2. Install Python 3.x and follow dependencies in your environment:
 - _**jupyter**_
 - _**matplotlib**_
 - _**sympy**_
 - _**scipy**_
 
Then you can run _**ScriptEnginePerformance.ipynb**_ file in IPython/Jupyter.

#### Notebook parameters:
 - _**warmup_count**_: analysed warmup iterations count (see 1st code input in notebook)
 
###Also you can see already handled dataset and analisys result:
 - `script-engine-benchmark-js-stats/analisys/results/`, `script-engine-benchmark-js-stats/analisys/warmup/`: analysed datasets
 - `script-engine-benchmark-js-stats/analisys/ScriptEnginePerformance.html`: analisys result (for datasets specified above)