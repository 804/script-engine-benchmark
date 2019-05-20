## JSR-223 Script Engines Microbenchmarks  

Author: [804](https://github.com/804)

[JMH](http://hg.openjdk.java.net/code-tools/jmh) benchmarks for JSR-223 script engines.
Followed engines were compared: Graal JS (JavaScript, for Graal VM only), Rhino (JavaScript), Nashorn (JavaScript), Groovy (Groovy), Jython (Python).

### Step 1 <br/> _Dataset preparation._ 
#### a. Gradle benchmark task
You can run all benchmarks by Gradle task using: 
 - for common Hotspot JVM (Nashorn in my case): `./gradlew micro:jmh-common-hotspot [<parameters>]`
 - for GraalVM: `./gradlew micro:jmh-graalvm [<parameters>]`
   
Parameters (see 'Parameters' below) for this case need to specify as Gradle option (`--key` if you want to set boolean flag to 'true' and `--key value` for other values) or as Gradle project properties (as `-P<key>=<value>`) after commands above.

Parameters have following priorities depends on their specifying method: Gradle option > Gradle Property > default values.

#### Parameters (Gradle only).
 - _**result-path**_: result dataset output directory (`out/` by default)
 - _**include**_: masked string for defining package for performed benchmark discovery (`.*` by default)
 
#### b. Gradle assemble task
1. Create jar with dependencies:  
`./gradlew micro:clean micro:shadowJar`
2. Run:
 - common JMH runner (you can specify JMH parameters as you want): `java -cp script-engine-benchmark-micro-<version>-all.jar [<parameters>]`
 - specified runner (for debug purposes, parameters have debug values: _result-path_=_"jmh-result.json"_ and _include_=_<runner_class>_): `java -cp script-engine-benchmark-micro-<version>-all.jar <runner_class>` 

Parameters for this case need to specify as standard JMH arguments (execute `java -jar script-engine-benchmark-micro-<version>-all.jar -h` for help).

### Step 2 <br/> _Dataset analysis._ 
#### Run preparing:
1. Move `result` folder from result destination directory to the folder `result` (see `script-engine-benchmark-micro/analysis/` folder in repository)
2. Install Python 3.x and follow dependencies in your environment:
 - _**jupyter**_
 - _**matplotlib**_
 - _**sympy**_
 - _**scipy**_
 
Then you can run `ipynb/{common-hotspot, graalvm-ce, graalvm-ee, comparision}/performance.ipynb` (in case of desired analysis result) file in IPython/Jupyter.
You may see analysis result for following cases: 
 - _common-hotspot_: for benchmark results run on common JVM (in my case it is Hotspot). All datasets from `micro:jmh-common-hotspot` should be moved to `result` folder. See `ipynb/common-hotspot/` folder for IPython notebook.
 - _graalvm-ce_: for benchmark results run on GraalVM CE. All datasets from `micro:jmh-graalvm` (run with GraalVM CE) should be renamed to `result-jmh-graalvm-ce.json` and moved to `result` folder. See `ipynb/graalvm-ce/` folder for IPython notebook.
 - _graalvm-ee_: for benchmark results run on GraalVM EE. All datasets from `micro:jmh-graalvm` (run with GraalVM EE) should be renamed to `result-jmh-graalvm-ee.json` and moved to `result` folder. See `ipynb/graalvm-ee/` folder for IPython notebook.
 - _comparision_: for benchmark run on common Hotspot JVM and GraalVM results comparision. All datasets from `micro:jmh-common-hotspot` and `micro:jmh-graalvm` (run with GraalVM CE and EE and renamed as described above) should be moved to `result` folder. See `ipynb/comparision/` folder for IPython notebook.


#### Also you can see already handled dataset and analysis result:
 - `script-engine-benchmark-js-micro/analysis/result/result-jmh-{common-hotspot, graalvm-ce, graalvm-ee}.json`: analysed datasets
 - `script-engine-benchmark-js-micro/analysis/ipynb/{common-hotspot, graalvm-ce, graalvm-ee}/performance.html`: analysis result (for datasets specified above)
 - `script-engine-benchmark-js-micro/analysis/ipynb/comparision/performance.html`: analysis result (for comparision of datasets specified above)
 
_**PS**: Choice of JVM for common cases isn't strict. You can replace Hotspot JVM by any JVM (with any version) which supports all the tested script engine for your experiments (and has Nashorn engine in it's JDK)._ 