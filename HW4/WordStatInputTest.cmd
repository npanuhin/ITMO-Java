@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/wordStat/WordStatChecker.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/wordStat/WordStatTester.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/wordStat/WordStatTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" WordStatInput.java && ^
java -cp "C:\Cache" wordStat.WordStatTest Base
