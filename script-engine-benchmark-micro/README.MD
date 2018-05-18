## JSR-223 Script Engines Microbenchmarks

[JMH](http://hg.openjdk.java.net/code-tools/jmh) benchmarks for JSR-223 script engines.
Followed engines were compared: Rhino (JavaScript), Nashorn (JavaScript), Groovy (Groovy).

###Step 1 <br/> _Dataset preparing._ 
#### a. Gradle benchmark task
You can run all benchmarks by Gradle task using: `./gradlew micro:jmh`
   
Parameters (see 'Parameters' below) for this case need to specify as Gradle option (`--key value`).

#### Parameters.
 - _**result-path**_: result dataset output directory (`out/` by default)
 
#### b. Gradle assemble task
1. Create jar with dependencies:  
`./gradlew micro:clean micro:shadowJar`
2. Run it:  
 - all benchmarks:  
 `java -jar script-engine-benchmark-micro-<version>-all.jar`
 - only for specified runner (for debug purposes only, file wit results will be saved _"jmh-result.json"_ at Java run directory):  
 `java -cp script-engine-benchmark-micro-<version>-all.jar <runner_class>` 

Parameters for this case need to specify as standard JMH arguments (execute `java -jar script-engine-benchmark-micro-<version>-all.jar -h` for help).

###Step 2 <br/> _Dataset analysis._ 
#### Run preparing:
1. Move `result` folder from result destination directory near to _**performance.ipynb**_ (see `script-engine-benchmark-micro/analisys/` folder in repository)
2. Install Python 3.x and follow dependencies in your environment:
 - _**jupyter**_
 - _**matplotlib**_
 - _**sympy**_
 - _**scipy**_
 
Then you can run _**performance.ipynb**_ file in IPython/Jupyter.

####Also you can see already handled dataset and analisys result:
 - `script-engine-benchmark-js-google/analisys/result/result.json`: analysed datasets
 - `script-engine-benchmark-js-google/analisys/performance.html`: analisys result (for datasets specified above)