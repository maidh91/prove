#!/bin/bash
# $1 = .../4_1380007072066     $2 = .../modelchecking
if [ "$1" == "" ] || [ "$2" == "" ]; then
	echo "Missing arguments !!!"
	exit 1
else
	echo ""
fi
inputStudentCode="student.c"
inputSolutionCode="solution.c"
inputVarInitCode="var_init.c"
outputVerify_c="verify.c"
executionFileName="execution"
basedir=$1		# basedir = .../4_1380007072066
pathC2p=$2		# pathC2p = .../modelchecking
pathAntlr="$pathC2p"/antlr/antlr.jar
pathSpin="$pathC2p"/spin_linux/spin
pathF2I="$pathC2p"/F2I/translate
pathDomain="$pathC2p"/createdomain.h
CLASSPATH=$CLASSPATH:$pathAntlr:$pathC2p
export CLASSPATH
cd $basedir
cp $pathDomain $basedir
clearfirst()
{
	rm -f $executionFileName
	clearend
}
clearend()
{
	rm -f $outputVerify_c
	rm -f verify.spin
	rm -f mappingObject
	rm -f pan.*
	rm -f errors.txt
	rm -f verify.spin.trail
	rm -f spin_trails.txt
	rm -f sample.txt
}
run_transform()
{	
	java Transformer.DeleteFragmaLine $inputStudentCode
	java Transformer.CheckFloat $inputSolutionCode isFloatSol	#check file solution.c
	if [ `head -1 isFloatSol` == "false" ]		#if there is no float in solution.c
	then	
		java Transformer.CheckFloat $inputStudentCode isFloatStu	#check file student.c
		if [ `head -1 isFloatStu` == "false" ]	#if there is no float in student.c
		then		
			java Transformer.Transform $inputStudentCode $inputSolutionCode $inputVarInitCode $outputVerify_c verify.spin mappingObject > transform.out
			cp mappingObject mappingObject2
			#create a file scale for PrintExecution 
			java Transformer.CreateScale scale > scale.out
		else
			#translate 2 files
			$pathF2I -o student_i.c -mcs $inputStudentCode
			$pathF2I -o solution_i.c -mcs $inputSolutionCode
			#create file for CounterExample
			java Transformer.Transform student_i.c solution_i.c $inputVarInitCode verify_f.c verify.spin mappingObject2
			java Transformer.Transform $inputStudentCode $inputSolutionCode var_init_f.c $outputVerify_c verify_f.spin mappingObject
			#add parameter for PrintExecution
			java Transformer.Add2Scale student_scale.txt scale
		fi
	else
		#translate 2 files
		$pathF2I -o student_i.c -mcs $inputStudentCode
		$pathF2I -o solution_i.c -mcs $inputSolutionCode
		#create file for CounterExample
		java Transformer.Transform student_i.c solution_i.c $inputVarInitCode verify_f.c verify.spin mappingObject2
		java Transformer.Transform $inputStudentCode $inputSolutionCode var_init_f.c $outputVerify_c verify_f.spin mappingObject
		#add parameter for PrintExecution
		java Transformer.Add2Scale student_scale.txt scale
	fi
}
run_verify()
{   
	$pathSpin -a verify.spin
	gcc pan.c -o pan.o -DVECTORSZ=5000 -DSAFETY
	./pan.o -m100 > errors.txt
	set $(grep "errors:" errors.txt)	
	if (expr $8 = 1) then
		run_print_execution
	else
		./pan.o -m500 > errors.txt
		set $(grep "errors:" errors.txt)
		if (expr $8 = 1) then
			run_print_execution
		else
			./pan.o -m1000 > errors.txt
			set $(grep "errors:" errors.txt)
			if (expr $8 = 1) then
				run_print_execution
			else
				./pan.o -m5000 > errors.txt
				set $(grep "errors:" errors.txt)
				if (expr $8 = 1) then
					run_print_execution
				else
					./pan.o -m10000 > errors.txt
					set $(grep "errors:" errors.txt)
					if (expr $8 = 1) then
						run_print_execution
					else
						echo ""
					fi
				fi
			fi
		fi
	fi
}
run_print_execution()
{
	./pan.o -r > spin_trails.txt
	java Transformer.CounterExample.PrintExecution mappingObject mappingObject2 spin_trails.txt $executionFileName scale
}

clearfirst
run_transform
run_verify
#clearend
exit 0
