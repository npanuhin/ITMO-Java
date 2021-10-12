@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" WordStatCount.java && "../run_jar" "%~dp0/tests/WordStatCountTest.jar"
