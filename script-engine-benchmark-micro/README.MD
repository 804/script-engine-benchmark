## JSR-223 Script Engines Microbenchmarks  

Author: [804](https://github.com/804)

[JMH](http://hg.openjdk.java.net/code-tools/jmh) benchmarks for JSR-223 script engines.
Followed engines were compared: Graal JS (JavaScript, for Graal VM only), Rhino (JavaScript), Nashorn (JavaScript), Groovy (Groovy), Jython (Python).

### Step 1 <br/> _Dataset preparing._ 
#### a. Gradle benchmark task
You can run all benchmarks by Gradle task using: 
 - for common JVM (Nashorn in my case): `./gradlew micro:jmh-common`
 - for Graal VM: `./gradlew micro:jmh-graal`
   
Parameters (see 'Parameters' below) for this case need to specify as Gradle option (`--key value`).

#### Parameters.
 - _**result-path**_: result dataset output directory (`out/` by default)
 - _**include**_: masked string for defining package for performed benchmark discovery (`.*` by default)
 
#### b. Gradle assemble task
1. Create jar with dependencies:  
`./gradlew micro:clean micro:shadowJar`
2. Run specified runner (for debug purposes only, file wit results will be saved _"jmh-result.json"_ at Java run directory):  
 `java -cp script-engine-benchmark-micro-<version>-all.jar <runner_class>` 

Parameters for this case need to specify as standard JMH arguments (execute `java -jar script-engine-benchmark-micro-<version>-all.jar -h` for help).

### Step 2 <br/> _Dataset analysis._ 
#### Run preparing:
1. Move `result` folder from result destination directory to the folder `result` (see `script-engine-benchmark-micro/analisys/` folder in repository)
2. Install Python 3.x and follow dependencies in your environment:
 - _**jupyter**_
 - _**matplotlib**_
 - _**sympy**_
 - _**scipy**_
 
Then you can run `ipynb/{common, graal, comparision}/performance.ipynb` (in case of desired analysis result) file in IPython/Jupyter.You may see analysys result for following cases: 
 - _common_: for benchmark results run on common JVM (in my case it is Hotspot). All datasets from `micro:jmh-common` should be moved to `result` folder. See `ipynb/common/` folder for IPython notebook.
 - _graal_: for benchmark results run on Graal JS. All datasets from `micro:jmh-graal` should be moved to `result` folder. See `ipynb/graal/` folder for IPython notebook.
 - _comparision_: for benchmark run on common JVM (in my case it is Hotspot) and Graal JS results comparision. All datasets from `micro:jmh-common` and `micro:jmh-graal` should be moved to `result` folder. See `ipynb/comparision/` folder for IPython notebook.


#### Also you can see already handled dataset and analisys result:
 - `script-engine-benchmark-js-micro/analisys/result/result-jmh-{common, graal}.json`: analysed datasets
 - `script-engine-benchmark-js-micro/analisys/ipynb/{common, graal}/performance.html`: analisys result (for datasets specified above)
 - `script-engine-benchmark-js-micro/analisys/ipynb/comparision/performance.html`: analisys result (for comparision of datasets specified above)