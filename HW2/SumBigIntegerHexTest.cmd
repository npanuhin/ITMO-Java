@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/Sum/SumTester.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/Sum/SumTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" Sum.java && ^
javac -cp "C:\Cache" -d "C:\Cache" SumBigIntegerHex.java && ^
java -cp "C:\Cache" sum.SumTest Base BigIntegerHex
