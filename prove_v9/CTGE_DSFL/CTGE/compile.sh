#!/bin/bash
javac -cp ./lib/antlr.jar -d bin/ src/transform/AST/*.java src/transform/Parser/*.java src/transform/CodeGeneration/*.java src/transform/DependenceGraph/*.java src/transform/*.java src/ctg/*.java src/system/*.java src/CodeAnalyzer/*.java

exit 0

