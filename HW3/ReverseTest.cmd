@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" Reverse.java && "../src/run_jar" "%~dp0/tests/ReverseTest.jar"
