pathC2p=/home/mrbubu/save/apache-tomcat-6.0.37/webapps/prove/modelchecking
pathAntlr="$pathC2p"/antlr/antlr.jar
pathSpin="$pathC2p"/spin_linux/spin

CLASSPATH=$CLASSPATH:$pathAntlr:$pathC2p
export CLASSPATH

$pathSpin -a verify.spin
gcc pan.c -o pan.o -DVECTORSZ=5000 -DSAFETY
./pan.o -m500 > errors.txt
./pan.o -r > spin_trails.txt
java Transformer.CounterExample.PrintExecution mappingObject mappingObject2 spin_trails.txt $executionFileName scale > print.out
