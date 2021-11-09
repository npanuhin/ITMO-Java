@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/reverse/ReverseChecker.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/reverse/ReverseTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/reverse/FastReverseTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/reverse/ReverseAbcTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/reverse/ReverseHexAbc2Test.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/reverse/FastReverseHexAbc2Test.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../MyClasses/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" AbcScanner.java && ^
javac -cp "C:\Cache" -d "C:\Cache" ReverseHexAbc2.java && ^
java -cp "C:\Cache" reverse.FastReverseHexAbc2Test
