JAVAFILES=$(shell ls model/*.java) $(shell ls view/*.java) $(shell ls controller/*.java)
CLASSFILES=$(JAVAFILES:.java=.class)

all: $(CLASSFILES)

$(CLASSFILES): %.class: %.java
	javac $<

run: all
	./run

BallTest.class: model/BallTest.java
	javac -g model/BallTest.java
	
MyPongModelTest.class: model/MyPongModelTest.java
	javac -g model/MyPongModelTest.java
	
unitTests: BallTest.class MyPongModelTest.class
	java -cp .:/usr/share/java/junit.jar org.junit.runner.JUnitCore BallTest
	java -cp .:/usr/share/java/junit.jar org.junit.runner.JUnitCore MyPongModelTest


clean:
	rm -rf $(CLASSFILES) model/*.class view/*.class controller/*.class

.PHONY: all run clean

