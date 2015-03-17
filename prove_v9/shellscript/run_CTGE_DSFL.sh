#!/bin/bash
gccfile="student.c"
resultfile="result_syntax.txt"
tempfile="temp"

realpath_CTGE_DSFL=$1
realpath_CTGE="$realpath_CTGE_DSFL"/CTGE
realpath_DSFL="$realpath_CTGE_DSFL"/DSFL


# Step 1: Check syntax
cd $realpath_CTGE
declare -x LANG="en.US"
gcc -fsyntax-only $gccfile 2> $tempfile
sed "s/"$gccfile": //g" $tempfile > $resultfile
sed "s/"$gccfile":/Line /g" $resultfile > $tempfile
sed "1i\syntaxerror" $tempfile > $resultfile


# Step 2: CTGE process
# copy student and solution from web submit
# run script
$realpath_CTGE/run.sh


# Step 3: DSFL process
# copy testcases from CTGE
# run script
cd $realpath_DSFL
cp $realpath_CTGE/src.cpp $realpath_DSFL/solution.c
cp $realpath_CTGE/dst.cpp $realpath_DSFL/student.c
cp $realpath_CTGE/concolicSE.txt $realpath_DSFL/concolicSE.txt
$realpath_DSFL/run.sh

exit 0
