#Makefile
run-dist:
	./build/install/app/bin/app
help:
	./build/install/app/bin/app -h
.PHONY: build
build:
	./gradlew clean build jacocoTestReport
test_report:
	./gradlew jacocoTestReport
