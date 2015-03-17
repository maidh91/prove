#!/bin/bash
basedir=$1
pathSort=$2

inputStudentCode="student.c"
inputSortName="sortname.txt"
outputVerify_c="verify.c"
outputOutput="output.txt"
pathVerify=$pathSort/verify.h
pathMain=$pathSort/main.cpp

pathAntlr=$pathSort/antlr/antlr.jar
CLASSPATH=$CLASSPATH:$pathAntlr:$pathSort
export CLASSPATH

cd $basedir
cp $pathVerify $basedir
cp $pathMain $basedir

java Transformer.Transform $inputStudentCode $outputVerify

g++ main.cpp -o main
./main

java Checker.Checker < $outputOutput > $inputSortName

exit



