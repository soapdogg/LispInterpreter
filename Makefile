all: 
	find -name "*.java" > sources.txt
	javac @sources.txt

clean:
	find . -name "*.class" -type f -delete

