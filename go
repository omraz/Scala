#!/bin/bash

function compile {
	SOURCE=$1
	result=0
	if [ -f "$SOURCE.class" ]; then
		DATECLASS=`stat -c"%Y" $SOURCE.class`
	else
		DATECLASS=0
	fi
	DATESRC=`stat -c"%Y" $SOURCE.java`
	if [ "$DATESRC" -gt "$DATECLASS" ]
	then
		echo -n Compiling $SOURCE.java ...
		javac $SOURCE.java
		if [ $? != 0 ]
		then
			echo FAILED.
			echo
			result=1
		else
			echo SUCCEED.
			echo
		fi
	fi
	return $result
}

compile scala/Add
if [ $? -ne 0 ]; then
	exit
fi

compile scala/Leasing
if [ $? -ne 0 ]; then
	exit
fi

compile scala/Kilometry
if [ $? -ne 0 ]; then
	exit
fi

compile scala/Config
if [ $? -ne 0 ]; then
	exit
fi

compile scala/Scala
if [ $? -ne 0 ]; then
	exit
fi

java -cp /home/mraz/lib/mysql-connector-java-8.0.20.jar:. scala.Scala -f scala.config $*
