@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/reverse/ReverseTester.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/reverse/ReverseTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" Reverse.java && ^
java -cp "C:\Cache" reverse.ReverseTest Base
