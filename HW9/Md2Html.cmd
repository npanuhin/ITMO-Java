@echo off
cls
"../src/clear_cache" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../MyClasses/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "../HW7/markup/*.java" && ^
javac -cp "C:\Cache" -d "C:\Cache" "md2html/*.java" && (
    java -cp "C:\Cache" md2html.Md2Html "input/input1.txt" "output/output1.txt"
    java -cp "C:\Cache" md2html.Md2Html "input/input2.txt" "output/output2.txt"
)
