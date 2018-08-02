## JavaScript engines statistic benchmarks

Benchmarks source: [dromaeo](http://dromaeo.com/?dromaeo)  
Authors: [ankulikov](https://github.com/ankulikov), [804](https://github.com/804)

### Step 1 <br/> _Dataset preparing._ 
#### a. Gradle benchmark tasks
You can run benchmarks in different manniers:
 - Run each case for benchmarks with corresponding task:
   * Default Graal JS (chosen Graal VM required): `./gradlew js-stats:graal-js [<parameters>]`
   * Default Nashorn: `./gradlew js-stats:hashorn [<parameters>]`
   * Default Rhino: `./gradlew js-stats:rhino [<parameters>]`
   
Parameters (see 'Parameters' below) for this case need to specify as Gradle option (`--key` if you want to set boolean flag to 'true' and `--key value` for other values) or as Gradle project properties (as `-P<key>=<value>`) after commands above.

 - Run all common JVM cases for benchmarks above as sequential build task: `./gradlew js-stats:common-datasets [<parameters>]`
 - Run all Graal VM cases for benchmarks above as sequential build task: `./gradlew js-stats:graal-datasets [<parameters>]`
   
Parameters (see 'Parameters' below) for this case need to specify as Gradle project properties (as `-P<key>=<value>`) after commands above (propagate to each task in build task).

#### b. Gradle assemble tasks
1. Create jar with dependencies: 
   `./gradlew js-stats:clean js-stats:shadowJar`
2. Run it as:
 - for Rhino benchmarks: `java -cp script-engine-benchmark-js-stats-<version>-all.jar com.netcracker.mediation.scripting.benchmark.runner.rhino.RhinoBenchmark [<parameters>]`
 - for Nashorn benchmarks: `java -cp script-engine-benchmark-js-stats-<version>-all.jar com.netcracker.mediation.scripting.benchmark.runner.nashorn.NashornBenchmark [<parameters>]`
 - for Graal JS benchmarks (chosen Graal VM required): `java -cp script-engine-benchmark-js-stats-<version>-all.jar com.netcracker.mediation.scripting.benchmark.runner.graaljs.GraalJSBenchmarkk [<parameters>]`

Parameters (see 'Parameters' below) for this case need to specify as `key=value` after commands above.

Parameters have following priorities depends on their specifying method: Gradle option > Gradle Property > default values.

#### Parameters.
##### Common parameters:
 - _**use-warm-up**_: boolean flag for turning on of warming up (`false` by default)
 - _**warm-up-iterations**_: warming up iteration count (`5` by default)
 - _**result-path**_: result dataset output directory (`out/` by default)


### Step 2 <br/> _Dataset analysis._ 
#### Run preparing:
1. Move folders `results` and `warmup` from result destination directory to the folder `data/{common, graal}` (in case of run benchmarks) (see `script-engine-benchmark-js-stats/analisys/` folder in repository)
2. Install Python 3.x and follow dependencies in your environment:
 - _**jupyter**_
 - _**matplotlib**_
 - _**sympy**_
 - _**scipy**_
 
Then you can run `ipynb/{common, graal, comparision}/ScriptEnginePerformance.ipynb` (in case of desired analysis result) file in IPython/Jupyter.
You may see analysys result for following cases: 
 - _common_: for benchmark results run on common JVM (in my case it is Hotspot). All datasets from `js-stats:common-datasets` should be moved to `data/common/` folder. See `ipynb/common/` folder for IPython notebook.
 - _graal_: for benchmark results run on Graal JS. All datasets from `js-stats:graal-datasets` should be moved to `data/graal/` folder. See `ipynb/graal/` folder for IPython notebook.
 - _comparision_: for benchmark run on common JVM (in my case it is Hotspot) and Graal JS results comparision. All datasets from `js-stats:common-datasets` and `js-stats:graal-datasets` should be moved to `data/common/` and `data/graal/` folders accordingly. See `ipynb/comparision/` folder for IPython notebook.

#### Notebook parameters:
 - _**warmup_count**_: analysed warmup iterations count (see 1st code input in notebook)
 
### Also you can see already handled dataset and analisys result:
 - `script-engine-benchmark-js-stats/analisys/data/{common, graal}/results/`, `script-engine-benchmark-js-stats/analisys/data/{common, graal}/warmup/`: analysed datasets
 - `script-engine-benchmark-js-stats/analisys/data/{common, graal}/ScriptEnginePerformance.html`: analisys result (for datasets specified above)
 - `script-engine-benchmark-js-stats/analisys/ipynb/comparision/ScriptEnginePerformance.html`: analisys result (for comparision of datasets specified above)