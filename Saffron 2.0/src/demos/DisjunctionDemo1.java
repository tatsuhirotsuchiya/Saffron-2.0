package demos;

import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * <pre>
 * DisjunctionDemo1
 * Copyright (c) 2005 Positronic Software
 * 
 * public static void main(String[] args) throws Exception
	{
		List<IBooleanLiteral> s;

		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IBooleanVariable Y = BooleanVariable.getBooleanVariable("Y");
		IBooleanVariable Z = BooleanVariable.getBooleanVariable("Z");

		INaturalNumber A = new NaturalNumber("A");
		INaturalNumber B = new NaturalNumber("B");
		INaturalNumber C = new NaturalNumber("C");
		INaturalNumber D = new NaturalNumber("D");

		IProblem p1 = new NaturalNumberFixer(A, 3);
		IProblem p2 = new NaturalNumberFixer(B, 1);
		IProblem p3 = new NaturalNumberFixer(C, 11);
		IProblem p4 = new NaturalNumberFixer(D, 5);

		IProblem problem;

		// IProblem-0
		problem = new Disjunction(p1);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-1
		problem = new Disjunction(p1, p2);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-2
		problem = new Disjunction(p2, p3, Y);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-3
		problem = new Disjunction(p1, p3, p4);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-4
		problem = new Disjunction(p1, p2, p3, p4);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-5
		problem = new Disjunction(new IProblem[]
		{ p1, p2, p3, p4 });
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-6
		problem = new Disjunction(new IProblem[]
		{ p1, p2, p3, p4 }, new IBooleanVariable[]
		{ X, Y, Z });
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-7
		problem = new Conjunction(new Disjunction(new IProblem[]
		{ p1, p2, p3, p4 }, new IBooleanVariable[]
		{ X, Y, Z }), new BitFixer(X, false), new BitFixer(Y, false),
				new BitFixer(Z, false));
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-8
		problem = Disjunction.inner(new IProblem[]
		{ p2, p3, p4 }, new IBooleanVariable[]
		{ X, Y, Z });
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
	}
	
produces the following output:
	
	***************************************
*** IProblem-0
***************************************
*** 	{ A_0 }
*** 	{ A_1 }
*** 	{ $A_2 }
*** 	{ $A_3 }
*** 	{ $A_4 }
***************************************
*****	5 clauses generated.
***************************************
X= false
Y= false
Z= false
A= 3
B= 0
C= 0
D= 0
***************************************
*** IProblem-1
***************************************
*** 	{ B_0  BooleanVariable-0 }
*** 	{ $B_1  BooleanVariable-0 }
*** 	{ $B_2  BooleanVariable-0 }
*** 	{ $B_3  BooleanVariable-0 }
*** 	{ $B_4  BooleanVariable-0 }
*** 	{ A_0  $BooleanVariable-0 }
*** 	{ A_1  $BooleanVariable-0 }
*** 	{ $A_2  $BooleanVariable-0 }
*** 	{ $A_3  $BooleanVariable-0 }
*** 	{ $A_4  $BooleanVariable-0 }
***************************************
*****	10 clauses generated.
***************************************
X= false
Y= false
Z= false
A= 3
B= 0
C= 0
D= 0
***************************************
*** IProblem-2
***************************************
*** 	{ C_0  Y }
*** 	{ C_1  Y }
*** 	{ $C_2  Y }
*** 	{ C_3  Y }
*** 	{ $C_4  Y }
*** 	{ B_0  $Y }
*** 	{ $B_1  $Y }
*** 	{ $B_2  $Y }
*** 	{ $B_3  $Y }
*** 	{ $B_4  $Y }
***************************************
*****	10 clauses generated.
***************************************
X= false
Y= true
Z= false
A= 0
B= 1
C= 0
D= 0
***************************************
*** IProblem-3
***************************************
*** 	{ BooleanVariable-1  BooleanVariable-2  D_0 }
*** 	{ BooleanVariable-1  BooleanVariable-2  $D_1 }
*** 	{ BooleanVariable-1  BooleanVariable-2  D_2 }
*** 	{ BooleanVariable-1  BooleanVariable-2  $D_3 }
*** 	{ BooleanVariable-1  BooleanVariable-2  $D_4 }
*** 	{ BooleanVariable-1  $BooleanVariable-2  C_0 }
*** 	{ BooleanVariable-1  $BooleanVariable-2  C_1 }
*** 	{ BooleanVariable-1  $BooleanVariable-2  $C_2 }
*** 	{ BooleanVariable-1  $BooleanVariable-2  C_3 }
*** 	{ BooleanVariable-1  $BooleanVariable-2  $C_4 }
*** 	{ A_0  $BooleanVariable-1 }
*** 	{ A_1  $BooleanVariable-1 }
*** 	{ $A_2  $BooleanVariable-1 }
*** 	{ $A_3  $BooleanVariable-1 }
*** 	{ $A_4  $BooleanVariable-1 }
***************************************
*****	15 clauses generated.
***************************************
X= false
Y= false
Z= false
A= 0
B= 0
C= 11
D= 0
***************************************
*** IProblem-4
***************************************
*** 	{ BooleanVariable-3  BooleanVariable-4  BooleanVariable-5  D_0 }
*** 	{ BooleanVariable-3  BooleanVariable-4  BooleanVariable-5  $D_1 }
*** 	{ BooleanVariable-3  BooleanVariable-4  BooleanVariable-5  D_2 }
*** 	{ BooleanVariable-3  BooleanVariable-4  BooleanVariable-5  $D_3 }
*** 	{ BooleanVariable-3  BooleanVariable-4  BooleanVariable-5  $D_4 }
*** 	{ BooleanVariable-3  BooleanVariable-4  $BooleanVariable-5  C_0 }
*** 	{ BooleanVariable-3  BooleanVariable-4  $BooleanVariable-5  C_1 }
*** 	{ BooleanVariable-3  BooleanVariable-4  $BooleanVariable-5  $C_2 }
*** 	{ BooleanVariable-3  BooleanVariable-4  $BooleanVariable-5  C_3 }
*** 	{ BooleanVariable-3  BooleanVariable-4  $BooleanVariable-5  $C_4 }
*** 	{ B_0  BooleanVariable-3  $BooleanVariable-4 }
*** 	{ $B_1  BooleanVariable-3  $BooleanVariable-4 }
*** 	{ $B_2  BooleanVariable-3  $BooleanVariable-4 }
*** 	{ $B_3  BooleanVariable-3  $BooleanVariable-4 }
*** 	{ $B_4  BooleanVariable-3  $BooleanVariable-4 }
*** 	{ A_0  $BooleanVariable-3 }
*** 	{ A_1  $BooleanVariable-3 }
*** 	{ $A_2  $BooleanVariable-3 }
*** 	{ $A_3  $BooleanVariable-3 }
*** 	{ $A_4  $BooleanVariable-3 }
***************************************
*****	20 clauses generated.
***************************************
X= false
Y= false
Z= false
A= 0
B= 0
C= 11
D= 0
***************************************
*** IProblem-5
***************************************
*** 	{ BooleanVariable-6  BooleanVariable-7  BooleanVariable-8  D_0 }
*** 	{ BooleanVariable-6  BooleanVariable-7  BooleanVariable-8  $D_1 }
*** 	{ BooleanVariable-6  BooleanVariable-7  BooleanVariable-8  D_2 }
*** 	{ BooleanVariable-6  BooleanVariable-7  BooleanVariable-8  $D_3 }
*** 	{ BooleanVariable-6  BooleanVariable-7  BooleanVariable-8  $D_4 }
*** 	{ BooleanVariable-6  BooleanVariable-7  $BooleanVariable-8  C_0 }
*** 	{ BooleanVariable-6  BooleanVariable-7  $BooleanVariable-8  C_1 }
*** 	{ BooleanVariable-6  BooleanVariable-7  $BooleanVariable-8  $C_2 }
*** 	{ BooleanVariable-6  BooleanVariable-7  $BooleanVariable-8  C_3 }
*** 	{ BooleanVariable-6  BooleanVariable-7  $BooleanVariable-8  $C_4 }
*** 	{ B_0  BooleanVariable-6  $BooleanVariable-7 }
*** 	{ $B_1  BooleanVariable-6  $BooleanVariable-7 }
*** 	{ $B_2  BooleanVariable-6  $BooleanVariable-7 }
*** 	{ $B_3  BooleanVariable-6  $BooleanVariable-7 }
*** 	{ $B_4  BooleanVariable-6  $BooleanVariable-7 }
*** 	{ A_0  $BooleanVariable-6 }
*** 	{ A_1  $BooleanVariable-6 }
*** 	{ $A_2  $BooleanVariable-6 }
*** 	{ $A_3  $BooleanVariable-6 }
*** 	{ $A_4  $BooleanVariable-6 }
***************************************
*****	20 clauses generated.
***************************************
X= false
Y= false
Z= false
A= 0
B= 0
C= 11
D= 0
***************************************
*** IProblem-6
***************************************
*** 	{ D_0  X  Y  Z }
*** 	{ $D_1  X  Y  Z }
*** 	{ D_2  X  Y  Z }
*** 	{ $D_3  X  Y  Z }
*** 	{ $D_4  X  Y  Z }
*** 	{ C_0  X  Y  $Z }
*** 	{ C_1  X  Y  $Z }
*** 	{ $C_2  X  Y  $Z }
*** 	{ C_3  X  Y  $Z }
*** 	{ $C_4  X  Y  $Z }
*** 	{ B_0  X  $Y }
*** 	{ $B_1  X  $Y }
*** 	{ $B_2  X  $Y }
*** 	{ $B_3  X  $Y }
*** 	{ $B_4  X  $Y }
*** 	{ A_0  $X }
*** 	{ A_1  $X }
*** 	{ $A_2  $X }
*** 	{ $A_3  $X }
*** 	{ $A_4  $X }
***************************************
*****	20 clauses generated.
***************************************
X= false
Y= false
Z= true
A= 0
B= 0
C= 11
D= 0
***************************************
*** IProblem-7
***************************************
*** 	{ D_0  X  Y  Z }
*** 	{ $D_1  X  Y  Z }
*** 	{ D_2  X  Y  Z }
*** 	{ $D_3  X  Y  Z }
*** 	{ $D_4  X  Y  Z }
*** 	{ C_0  X  Y  $Z }
*** 	{ C_1  X  Y  $Z }
*** 	{ $C_2  X  Y  $Z }
*** 	{ C_3  X  Y  $Z }
*** 	{ $C_4  X  Y  $Z }
*** 	{ B_0  X  $Y }
*** 	{ $B_1  X  $Y }
*** 	{ $B_2  X  $Y }
*** 	{ $B_3  X  $Y }
*** 	{ $B_4  X  $Y }
*** 	{ A_0  $X }
*** 	{ A_1  $X }
*** 	{ $A_2  $X }
*** 	{ $A_3  $X }
*** 	{ $A_4  $X }
*** 	{ $X }
*** 	{ $Y }
*** 	{ $Z }
***************************************
*****	23 clauses generated.
***************************************
X= false
Y= false
Z= false
A= 0
B= 0
C= 0
D= 5
***************************************
*** IProblem-8
***************************************
*** 	{ BooleanVariable-10  BooleanVariable-9  Z }
*** 	{ BooleanVariable-10  BooleanVariable-9  D_0 }
*** 	{ BooleanVariable-10  BooleanVariable-9  $D_1 }
*** 	{ BooleanVariable-10  BooleanVariable-9  D_2 }
*** 	{ BooleanVariable-10  BooleanVariable-9  $D_3 }
*** 	{ BooleanVariable-10  BooleanVariable-9  $D_4 }
*** 	{ $BooleanVariable-10  BooleanVariable-9  Y }
*** 	{ $BooleanVariable-10  BooleanVariable-9  C_0 }
*** 	{ $BooleanVariable-10  BooleanVariable-9  C_1 }
*** 	{ $BooleanVariable-10  BooleanVariable-9  $C_2 }
*** 	{ $BooleanVariable-10  BooleanVariable-9  C_3 }
*** 	{ $BooleanVariable-10  BooleanVariable-9  $C_4 }
*** 	{ $BooleanVariable-9  X }
*** 	{ B_0  $BooleanVariable-9 }
*** 	{ $B_1  $BooleanVariable-9 }
*** 	{ $B_2  $BooleanVariable-9 }
*** 	{ $B_3  $BooleanVariable-9 }
*** 	{ $B_4  $BooleanVariable-9 }
***************************************
*****	18 clauses generated.
***************************************
X= false
Y= true
Z= false
A= 0
B= 0
C= 11
D= 0

 * </pre>
 * 
 * @author Kerry Michael Soileau
 * @version 2.0
 */

public class DisjunctionDemo1
{
	public static void main(String[] args) throws Exception
	{
		List<IBooleanLiteral> s;

		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IBooleanVariable Y = BooleanVariable.getBooleanVariable("Y");
		IBooleanVariable Z = BooleanVariable.getBooleanVariable("Z");

		INaturalNumber A = new NaturalNumber("A");
		INaturalNumber B = new NaturalNumber("B");
		INaturalNumber C = new NaturalNumber("C");
		INaturalNumber D = new NaturalNumber("D");

		IProblem p1 = new NaturalNumberFixer(A, 3);
		IProblem p2 = new NaturalNumberFixer(B, 1);
		IProblem p3 = new NaturalNumberFixer(C, 11);
		IProblem p4 = new NaturalNumberFixer(D, 5);

		IProblem problem;

		// IProblem-0
		problem = new Disjunction(p1);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-1
		problem = new Disjunction(p1, p2);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-2
		problem = new Disjunction(p2, p3, Y);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-3
		problem = new Disjunction(p1, p3, p4);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-4
		problem = new Disjunction(p1, p2, p3, p4);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-5
		problem = new Disjunction(new IProblem[]
		{ p1, p2, p3, p4 });
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-6
		problem = new Disjunction(new IProblem[]
		{ p1, p2, p3, p4 }, new IBooleanVariable[]
		{ X, Y, Z });
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-7
		problem = new Conjunction(new Disjunction(new IProblem[]
		{ p1, p2, p3, p4 }, new IBooleanVariable[]
		{ X, Y, Z }), new BitFixer(X, false), new BitFixer(Y, false),
				new BitFixer(Z, false));
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
		// IProblem-8
		problem = Disjunction.inner(new IProblem[]
		{ p2, p3, p4 }, new IBooleanVariable[]
		{ X, Y, Z });
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
	}
}