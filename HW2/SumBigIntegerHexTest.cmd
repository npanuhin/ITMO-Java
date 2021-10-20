@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" SumBigIntegerHex.java && "../src/run_jar" "%~dp0/tests/SumBigIntegerHexTest.jar"
