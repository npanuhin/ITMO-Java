@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/Sum/SChecker.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/Sum/SumChecker.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/Sum/SumTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/Sum/SumBigIntegerHexTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" SumBigIntegerHex.java && ^
java -cp "C:\Cache" sum.SumBigIntegerHexTest
