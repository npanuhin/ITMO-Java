@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/reverse/ReverseChecker.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/reverse/ReverseTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/reverse/ReverseSum2Test.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" ReverseSum2.java && ^
java -cp "C:\Cache" reverse.ReverseSum2Test
