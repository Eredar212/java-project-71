#Makefile
run-dist:
	./build/install/app/bin/app
gendiff:
	./build/install/app/bin/app -f=$(format) $(f1) $(f2)
help:
	./build/install/app/bin/app -h
.PHONY: build
build:
	./gradlew clean build
test_report:
	./gradlew jacocoTestReport
