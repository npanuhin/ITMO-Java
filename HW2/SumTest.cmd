@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" Sum.java && "../src/run_jar" "%~dp0/tests/SumTest.jar"
