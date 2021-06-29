all: bin/hello.o bin/main.o
	mkdir -p bin
	g++ bin/hello.o bin/main.o -o bin/hello.exe

bin/hello.o: src/hello.cpp
	mkdir -p bin
	g++ -c src/hello.cpp -o bin/hello.o

bin/main.o: src/main.cpp
	mkdir -p bin
	g++ -c src/main.cpp -o bin/main.o

clean:
	rm -f bin/*