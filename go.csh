#!/bin/csh

setenv CLASS $1

if ( "$CLASS" == "" ) then
  echo Usage: $0 \<class-name\>
  exit
endif

if ( ! -f scala/$CLASS.class ) then
  goto Compile
endif

if ( "`stat -c %Y scala/$CLASS.java`" > "`stat -c %Y scala/$CLASS.class`" ) then
  goto Compile
else
  goto Run
endif

Compile:
echo Compiling $CLASS ...
javac scala/$CLASS.java
if ( $status == 0 ) then
  goto Run
else
  exit
endif

Run:
echo Running $CLASS ...
java -cp /usr/share/java/mysql-connector-java-8.0.20.jar:. scala.$CLASS
