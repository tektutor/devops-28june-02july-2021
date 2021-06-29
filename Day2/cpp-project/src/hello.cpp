#include "hello.h"

Hello::Hello() {
	cout << "Constructor ..." << endl;
}

Hello::~Hello() {
	cout << "Destructor ..." << endl;
}

string Hello::sayHello() {
	return "Hello DevOps!";
}
