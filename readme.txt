1. Create jar with dependencies: mvn clean compile assembly:single
2. Run it as:
    java -cp script-engine-benchmark-version.jar com.netcracker.mediation.scripting.benchmark.rhino.RhinoBenchmark
    java -cp script-engine-benchmark-version.jar com.netcracker.mediation.scripting.benchmark.nashorn.NashornBenchmark
2a. You can also pass number of warmup iterations (5 by default):
    java -cp script-engine-benchmark-version.jar com.netcracker.mediation.scripting.benchmark.rhino.RhinoBenchmark 10
    java -cp script-engine-benchmark-version.jar com.netcracker.mediation.scripting.benchmark.nashorn.NashornBenchmark 20
