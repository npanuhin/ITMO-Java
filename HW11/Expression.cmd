@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "expression/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" Main.java && ^
java -cp "C:\Cache" Main.java
