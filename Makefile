#Java compiler
JAVAC = javac

#Java compiler flags
JAVAFLAGS = -g

#Creating a .class file
COMPILE = $(JAVAC) $(JAVAFLAGS)

#One of these should be the main class listed in Runfile
CLASS_FILES = Interpreter.class ITokenizer.class Tokenizer.class tokens/IToken.class

#The first targer is the one that is executed when you invoke
#"make"

all: $(CLASS_FILES)

#The line describing the actions starts with <TAB>
%.class : %.java
	$(COMPILE) $<

clean:
	rm -f *.class

