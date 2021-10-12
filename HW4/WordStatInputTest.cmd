@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" WordStatInput.java && "../run_jar" "%~dp0/tests/WordStatInputTest.jar"
