@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" ReverseSum2.java && "../run_jar" "%~dp0/tests/ReverseSum2Test.jar"
