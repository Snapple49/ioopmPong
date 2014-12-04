JAVAFILES=$(shell ls model/*.java) $(shell ls view/*.java) $(shell ls controller/*.java)
CLASSFILES=$(JAVAFILES:.java=.class)

all: $(CLASSFILES)

$(CLASSFILES): %.class: %.java
	javac $<

build: all 

run: all
	./run
	
options: all
	javac controller/Pong.java
	

BallTest.class: modeltests/BallTest.java
	javac -g modeltests/BallTest.java
	
MyPongModelTest.class: modeltests/MyPongModelTest.java
	javac -g modeltests/MyPongModelTest.java
	
unitTests: BallTest.class MyPongModelTest.class
	java -cp .:/usr/share/java/junit.jar org.junit.runner.JUnitCore BallTest
	java -cp .:/usr/share/java/junit.jar org.junit.runner.JUnitCore MyPongModelTest


clean:
	rm -rf $(CLASSFILES) model/*.class view/*.class controller/*.class

.PHONY: all run clean

