@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" Reverse.java && "../run_jar" "%~dp0/tests/ReverseTest.jar"