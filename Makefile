JAVAFILES=$(shell ls model/*.java) $(shell ls view/*.java) $(shell ls controller/*.java)
CLASSFILES=$(JAVAFILES:.java=.class)
JAVATESTFILES=$(shell ls modeltests/*.java)
CLASSTESTFILES=$(JAVATESTFILES:.java=.class)


all: $(CLASSFILES)

$(CLASSFILES): %.class: %.java
	javac $<

build: all 

run: all
	java controller/Pong
	
options: all
	javac controller/Pong.java
	
	
alltests: $(CLASSTESTFILES)

$(CLASSTESTFILES): %.class: %.java
	javac $<
	
runtests: alltests
	java -cp .:/usr/share/java/junit.jar org.junit.runner.JUnitCore modeltests.OurTestSuite
	
	

clean:
	rm -rf $(CLASSFILES) model/*.class view/*.class controller/*.class

.PHONY: all run clean

