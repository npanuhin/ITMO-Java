@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" Sum.java && "../run_jar" "%~dp0/tests/SumTest.jar"
