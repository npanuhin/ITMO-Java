@echo off
cls
javac -cp "C:\Cache" -d "C:\Cache" "../MyClasses/*.java" && javac -cp "C:\Cache" -d "C:\Cache" Wspp.java && (
    java -cp "C:\Cache" Wspp "input/input1.txt" "output/output1.txt"
    java -cp "C:\Cache" Wspp "input/input2.txt" "output/output2.txt"
    java -cp "C:\Cache" Wspp "input/input3.txt" "output/output3.txt"
)
