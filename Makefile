ifeq ($(OS),Windows_NT)
    DETECTED_OS := Windows
else
    UNAME_S := $(shell uname -s)
    ifeq ($(UNAME_S),Darwin)
        DETECTED_OS := macOS
    else ifeq ($(UNAME_S),Linux)
        DETECTED_OS := Linux
    else
        DETECTED_OS := Unknown
    endif
endif

ifeq ($(DETECTED_OS),Windows)
all:
	powershell.exe -ExecutionPolicy Bypass -File build.ps1
else
all:
	mvn clean package
endif
	