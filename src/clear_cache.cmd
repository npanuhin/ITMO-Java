del /q "C:\Cache\*"
for /d %%x in ("C:\Cache\*") do @rd /s /q "%%x"
