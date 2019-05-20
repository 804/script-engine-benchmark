## JavaScript engines statistic benchmarks

Benchmarks source: [dromaeo](http://dromaeo.com/?dromaeo)  
Authors: [ankulikov](https://github.com/ankulikov), [804](https://github.com/804)

### Step 1 <br/> _Dataset preparing._ 
#### a. Gradle benchmark tasks
You can run benchmarks in different manners:
 - Run each case for benchmarks with corresponding task:
   * Default Graal JS (chosen Graal VM required): `./gradlew js-stats:graal-js [<parameters>]`
   * Default Nashorn: `./gradlew js-stats:hashorn [<parameters>]`
   * Default Rhino: `./gradlew js-stats:rhino [<parameters>]`
   
Parameters (see 'Parameters' below) for this case need to specify as Gradle option (`--key` if you want to set boolean flag to 'true' and `--key value` or `--key=value` for other values) or as Gradle project properties (as `-P<key>=<value>`) after commands above.

 - Run all common Hotspot JVM cases for benchmarks above as sequential build task: `./gradlew js-stats:datasets-common-hotspot [<parameters>]`
 - Run all Graal VM cases for benchmarks above as sequential build task: `./gradlew js-stats:datasets-graalvm [<parameters>]`
   
Parameters (see 'Parameters' below) for this case need to specify as Gradle project properties (as `-P<key>=<value>`) after commands above (propagate to each task in build task).

#### b. Gradle assemble tasks
1. Create jar with dependencies: 
   `./gradlew js-stats:clean js-stats:shadowJar`
2. Run it as:
 - for Rhino benchmarks: `java -cp script-engine-benchmark-js-stats-<version>-all.jar org.eightofour.scripting.benchmark.runner.rhino.RhinoBenchmark [<parameters>]`
 - for Nashorn benchmarks: `java -cp script-engine-benchmark-js-stats-<version>-all.jar org.eightofour.scripting.benchmark.runner.nashorn.NashornBenchmark [<parameters>]`
 - for Graal JS benchmarks (chosen GraalVM required): `java -cp script-engine-benchmark-js-stats-<version>-all.jar org.eightofour.scripting.benchmark.runner.graaljs.GraalJSBenchmarkk [<parameters>]`

Parameters (see 'Parameters' below) for this case need to specify as `key=value` after commands above.

Parameters have following priorities depends on their specifying method: Gradle option > Gradle Property > default values.

#### Parameters.
##### Common parameters:
 - _**use-warm-up**_: boolean flag for turning on of warming up (`false` by default)
 - _**warm-up-iterations**_: warming up iteration count (`5` by default)
 - _**result-path**_: result dataset output directory (`out/` by default)


### Step 2 <br/> _Dataset analysis._ 
#### Run preparing:
1. Move folders `results` and `warmup` from result destination directory to the folder `data/{common, graal}` (in case of run benchmarks) (see `script-engine-benchmark-js-stats/analysis/` folder in repository)
2. Install Python 3.x and follow dependencies in your environment:
 - _**jupyter**_
 - _**matplotlib**_
 - _**sympy**_
 - _**scipy**_
 
Then you can run `ipynb/{common-hotspot, graalvm-ce, graalvm-ee, comparision}/performance.ipynb` (in case of desired analysis result) file in IPython/Jupyter.
You may see analysis result for following cases: 
 - _common-hotspot_: for benchmark results run on common Hotspot JVM. All datasets from `js-stats:datasets-common-hotspot` (run with Hotspot JVM) should be moved to `data/common-hotspot/` folder. See `ipynb/common-hotspot/` folder for IPython notebook.
 - _graalvm-ce_: for benchmark results run on GraalVM CE. All datasets from `js-stats:datasets-graalvm` (run with GraalVM CE) should be moved to `data/graalvm-ce/` folder. See `ipynb/graalvm-ce/` folder for IPython notebook.
 - _graalvm-ee_: for benchmark results run on GraalVM EE. All datasets from `js-stats:datasets-graalvm` (run with GraalVM EE) should be moved to `data/graalvm-ee/` folder. See `ipynb/graalvm-ee/` folder for IPython notebook.
 - _comparision_: for benchmark run on common Hotspot JVM and GraalVM results comparision. All datasets from `js-stats:datasets-common-hotspot` (run with Hotspot JVM) and `js-stats:datasets-graalvm` (run with GraalVM CE and EE) should be moved to `data/common-hotspot/`, `data/graalvm-ce/` and `data/graalvm-ee/` folders accordingly. See `ipynb/comparision/` folder for IPython notebook.

#### Notebook parameters:
 - _**warmup_count**_: analysed warmup iterations count (see 1st code input in notebook)
 
### Also you can see already handled dataset and analysis result:
 - `script-engine-benchmark-js-stats/analysis/data/{common-hotspot, graalvm-ce, graalvm-ee}/results/`, `script-engine-benchmark-js-stats/analysis/data/{common-hotspot, graalvm-ce, graalvm-ee}/warmup/`: analysed datasets
 - `script-engine-benchmark-js-stats/analysis/data/{common-hotspot, graalvm-ce, graalvm-ee}/performance.html`: analysis result (for datasets specified above)
 - `script-engine-benchmark-js-stats/analysis/ipynb/comparision/performance.html`: analysis result (for comparision of datasets specified above)
 
_**PS**: Choice of JVM for common cases isn't strict. You can replace Hotspot JVM by any JVM (with any version) which supports all the tested script engine for your experiments (and has Nashorn engine in it's JDK)._ 