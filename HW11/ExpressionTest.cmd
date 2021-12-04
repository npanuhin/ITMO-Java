@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "expression/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/expression/common/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/expression/*.java" && ^
java -cp "C:\Cache" -ea expression.ExpressionTest hard Base