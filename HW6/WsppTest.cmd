@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" "../MyClasses/*.java" && javac -cp "C:\Cache" -d "C:\Cache" Wspp.java && "../src/run_jar" "%~dp0/tests/WsppTest.jar"
