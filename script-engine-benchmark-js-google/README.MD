## JavaScript engines Google benchmarks

Benchmarks source: [google-v8](https://github.com/v8/v8/tree/master/benchmarks)  
Author: [804](https://github.com/804)

### Step 1 <br/> _Dataset preparing._ 
#### a. Gradle benchmark tasks
You can run benchmarks in different manniers:
 - Run each case for benchmarks with corresponding task:
   * Graal JS (chosen Graal VM required): `./gradlew js-google:graal-js [<parameters>]`
   * Nashorn without 'optimistic-types': `./gradlew js-google:hashorn [<parameters>]`
   * Nashorn with 'optimistic-types': `./gradlew js-google:hashorn-opt [<parameters>]`
   * Rhino with 'optimization-level=0': `./gradlew js-google:rhino-0 [<parameters>]`
   * Rhino with 'optimization-level=9': `./gradlew js-google:rhino-9 [<parameters>]`
   
Parameters (see 'Parameters' below) for this case need to specify as Gradle option (`--key` if you want to set boolean flag to 'true' and `--key value` for other values) or as Gradle project properties (as `-P<key>=<value>`) after commands above.

 - Run all common JVM cases for benchmarks above as sequential build task: `./gradlew js-google:datasets-common [<parameters>]` 
 - Run all Graal VM cases for benchmarks above as sequential build task: `./gradlew js-google:datasets-graal [<parameters>]` 
   
Parameters (see 'Parameters' below) for this case need to specify as Gradle project properties (as `-P<key>=<value>`) after commands above (propagate to each task in build task).

Parameters have following priorities depends on their specifying method: Gradle option > task configuration parameter value (for example, for "rhino-9" task "optimization-level" = 9) > Gradle Property > default values.

#### b. Gradle assemble tasks
1. Create jar with dependencies: 
   `./gradlew js-google:clean js-google:shadowJar`
2. Run it as:
 - for Rhino benchmarks: `java -cp script-engine-benchmark-js-google-<version>-all.jar com.netcracker.mediation.scripting.benchmark.runner.rhino.RhinoBenchmark [<parameters>]`
 - for Nashorn benchmarks: `java -cp script-engine-benchmark-js-google-<version>-all.jar com.netcracker.mediation.scripting.benchmark.runner.nashorn.NashornBenchmark [<parameters>]`
 - for Graal JS benchmarks (chosen Graal VM required): `java -cp script-engine-benchmark-js-google-<version>-all.jar com.netcracker.mediation.scripting.benchmark.runner.graaljs.GraalJSBenchmark [<parameters>]`

Parameters (see 'Parameters' below) for this case need to specify as `key=value` after commands above.


#### Parameters.
##### Common parameters:
 - _**iterations**_: iteration count (`500` by default)
 - _**result-path**_: result dataset output directory (`out/` by default)
##### Nashorn parameters:
 - _**optimistic-types**_: boolean flag for turning on 'optimistic-types' option in Nashorn engine
##### Rhino parameters:
 - _**optimization-level**_: integer value for 'optimization-level' option in Rhino engine (range for benchmark is `0-9`)

### Step 2 <br/> _Dataset analysis._ 
#### Run preparing:
1. Move files from `result` folder from result destination directory to the folder `data/{common, graal}` (in case of run benchmarks) (see `script-engine-benchmark-js-google/analisys/` folder in repository)
2. Install Python 3.x and follow dependencies in your environment:
 - _**jupyter**_
 - _**matplotlib**_
 - _**sympy**_
 - _**scipy**_
 
Then you can run `ipynb/{common, graal, comparision}/performance.ipynb` (in case of desired analysis result) file in IPython/Jupyter.
You may see analysys result for following cases: 
 - _common_: for benchmark results run on common JVM (in my case it is Hotspot). All datasets from `js-google:datasets-common` should be moved to `data/common/` folder. See `ipynb/common/` folder for IPython notebook.
 - _graal_: for benchmark results run on Graal JS. All datasets from `js-google:datasets-graal` should be moved to `data/graal/` folder. See `ipynb/graal/` for IPython notebook.
 - _comparision_: for benchmark run on common JVM (in my case it is Hotspot) and Graal JS results comparision. All datasets from `js-google:datasets-common` and `js-google:datasets-graal` should be moved to `data/common/` and `data/graal/` folders accordingly. See `ipynb/comparision/` folder for IPython notebook.

#### Notebook parameters:
 - _**run_count**_: count of runs used in analysis of performance dynamic (see 2nd code input in notebook)
 - _**measure_count**_: count of last analysed runs for mean scoring analysis (see 2nd code input in notebook)
 
#### Also you can see already handled dataset and analisys result:
 - `script-engine-benchmark-js-google/analisys/data/{common, graal}/`: analysed datasets
 - `script-engine-benchmark-js-google/analisys/ipynb/{common, graal}/performance.html`: analisys result (for datasets specified above)
 - `script-engine-benchmark-js-google/analisys/ipynb/comparision/performance.html`: analisys result (for comparision of datasets specified above)