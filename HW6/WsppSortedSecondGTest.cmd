@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/base/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/wordStat/WordStatChecker.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/wspp/WsppChecker.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" -encoding utf-8 "../tests/java/wspp/WsppTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../tests/java/wspp/WsppSortedSecondGTest.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../MyClasses/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" WsppSortedSecondG.java && ^
java -cp "C:\Cache" wspp.WsppSortedSecondGTest
