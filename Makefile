JAVA=java
JAVAC=javac
JFLEX=$(JAVA) -jar jflex-1.8.2/lib/jflex-full-1.8.2.jar
CUPJAR=./java-cup-11b.jar
CUP=$(JAVA) -jar $(CUPJAR)
CP=.:$(CUPJAR)

default: run

.SUFFIXES: $(SUFFIXES) .class .java

.java.class:
				$(JAVAC) -cp $(CP) $*.java

FILE=		MyScanner.java      parser.java    sym.java \
				MyTypeCheckerTest.java \
				Argdecl.java Argdecls.java ArgdeclList.java Args.java \
				Binaryop.java ExampleException.java ExampleToken.java Expr.java \
				Fielddecl.java Fielddecls.java FullType.java IfEnd.java \
        Methoddecl.java Methoddecls.java MethodTable.java MethodType.java\
        Memberdecls.java Name.java Optionalsemi.java Printlinelist.java \
        Printlist.java Program.java Readlist.java Stmt.java Stmts.java \
        Type.java Token.java VarTable.java

dump: parserD.java $(FILE:java=class)

run: testParse.txt

testParse.txt: all
		$(JAVA) -cp $(CP) MyTypeCheckerTest returnTypeBad.as > returnTypeBad-output.txt
		cat -n returnTypeBad-output.txt

lexTest.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest lexTest.txt > lexTest-output.txt
		cat -n lexTest-output.txt

orderPrec.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest orderPrec.txt > orderPrec-output.txt
		cat -n orderPrec-output.txt

invalidRedef.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest invalidRedef.txt > invalidRedef-output.txt
		cat -n invalidRedef-output.txt

invalidRedef.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest invalidRedef.txt > invalidRedef-output.txt
		cat -n invalidRedef-output.txt

undefUse.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest undefUse.txt > undefUse-output.txt
		cat -n undefUse-output.txt

validCoerc.txt: all
		$(JAVA) -cp $(CP) ExampleTypeCheckerTest validCoerc.txt > validCoerc-output.txt
		cat -n validCoerc-output.txt

all: MyScanner.java parser.java $(FILE:java=class)

clean:
		rm -f *.class *~ *.bak MyScanner.java parser.java sym.java

MyScanner.java: myGrammar.jflex
		$(JFLEX) myGrammar.jflex

parser.java: myTokens.cup
		$(CUP) -interface -progress < myTokens.cup

parserD.java: myTokens.cup
		$(CUP) -interface -dump < myTokens.cup
