#!/bin/bash
path=$1
cd $path
whyfile="std_why.c"
gccfile="student.c"
resultfile="result.txt"
tempfile="temp"
array=(alt-ergo redlog)

clearall() 
{
	rm -fr std_why.jessie
	rm -f $tempfile
}

gccsyntaxerror()
{
	declare -x LANG="en.US"
	gcc -fsyntax-only $gccfile 2> $tempfile
	sed "s/"$gccfile": //g" $tempfile > $resultfile
	sed "s/"$gccfile":/Line /g" $resultfile > $tempfile
	sed "1i\syntaxerror" $tempfile > $resultfile
}

check() 
{
	frama-c -jessie -jessie-atp $prover $whyfile > $resultfile
	grep "total   " $resultfile > $tempfile
	grep "valid   " $resultfile >> $tempfile
	set $(grep -v sec $tempfile)
	total=$3
	valid=$6
	if (expr $total = 0) then return 0 
	elif (expr $valid = $total) then return 1
	else return 0	
	fi
}

gccsyntaxerror
if (!(test -s $resultfile)) then
{
	for (( i = 0 ; i < ${#array[@]} ; i++ ))
	do
		prover=${array[$i]}
		check
		result=$?
		if (expr $result = 1) then
		{
			clearall
			exit 0
		}
		fi
	done
}
fi
clearall
exit 0

