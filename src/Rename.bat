@echo off
setlocal enabledelayedexpansion

set "filename=archivo-1.txt"
set "newname=delitos(!date:/=-!).txt"

ren "%filename%" "%newname%