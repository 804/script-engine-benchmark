##JavaScript engines statistic benchmarks

Source: [dromaeo](http://dromaeo.com/?dromaeo)

###Step 1 <br/> _Dataset preparing._ 
###Step 1 <br/> _Dataset preparing._ 
#### a. Gradle benchmark tasks
You can run benchmarks in different manniers:
 - Run each case for benchmarks with corresponding task:
   * Default Nashorn: `./gradlew js-stats:hashorn`
   * Default Rhino: `./gradlew js-stats:rhino`
   
Parameters (see 'Parameters' below) for this case need to specify as Gradle option (`--key` if you want to set boolean flag to 'true' and `--key value` for other values) or as Gradle project properties (as `-P<key>=<value>`) after commands above.

 - Run all cases for benchmarks above as sequential build task: `./gradlew js-stats:datasets`
   
Parameters (see 'Parameters' below) for this case need to specify as Gradle project properties (as `-P<key>=<value>`) after commands above (propagate to each task in build task).

#### b. Gradle assemble tasks
1. Create jar with dependencies: 
   `./gradlew js-stats:clean js-stats:shadowJar`
2. Run it as:
 - for Rhino benchmarks: `java -cp script-engine-benchmark-js-stats-<version>-all.jar com.netcracker.mediation.scripting.benchmark.runner.rhino.RhinoBenchmark`
 - for Nashorn benchmarks: `java -cp script-engine-benchmark-js-stats-<version>-all.jar com.netcracker.mediation.scripting.benchmark.runner.nashorn.NashornBenchmark`

Parameters (see 'Parameters' below) for this case need to specify as `key=value` after commands above.

#### Parameters.
##### Common parameters:
 - _**use-warm-up**_: boolean flag for turning on of warming up (`false` by default)
 - _**warm-up-iterations**_: warming up iteration count (`5` by default)
 - _**benchmark-js-path**_: JavaScript benchmark directory (`src/main/js/` by default)
 - _**result-path**_: result dataset output directory (`out/` by default)


###Step 2 <br/> _Dataset analysis._ 
#### Run preparing:
1. Move folders `results` and `warmup` from result folder near to _**ScriptEnginePerformance.ipynb**_ (see `script-engine-benchmark-js-stats/analisys/` folder in repository)
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