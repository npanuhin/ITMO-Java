@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" WordStatCount.java && "../src/run_jar" "%~dp0/tests/WordStatCountTest.jar"
