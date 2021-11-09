@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/wordStat/WordStatChecker.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/wordStat/WordStatInputTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/wordStat/WordStatCountTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../MyClasses/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" AbcScanner.java && ^
javac -cp "C:\Cache" -d "C:\Cache" WordStatCount.java && ^
java -cp "C:\Cache" wordStat.WordStatCountTest
