@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../MyClasses/*.java" && javac -cp "C:\Cache" -d "C:\Cache" WsppSortedSecondG.java && (
    java -cp "C:\Cache" WsppSortedSecondG "input/input1.txt" "output/output1.txt"
    java -cp "C:\Cache" WsppSortedSecondG "input/input2.txt" "output/output2.txt"
    java -cp "C:\Cache" WsppSortedSecondG "input/input3.txt" "output/output3.txt"
)
