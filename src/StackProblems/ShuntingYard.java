/**
 * Class to Implement ShuntingYard
 *
 * @author Sharayu Sharad Mantri
 * Date : 5/30/19
 * Project : StackProblems
 **/

package StackProblems;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShuntingYard {

    enum Op {

        DIVIDE,
        MULTIPLY,
        ADD,
        SUBTRACT,
        OPEN,
        CLOSE,
        NUM,
        HASH;
    }

    public Op checkTYpe(char c) {

        switch (c) {
            case '/':
                return Op.DIVIDE;
            case '*':
                return Op.MULTIPLY;
            case '+':
                return Op.ADD;
            case '-':
                return Op.SUBTRACT;
            case '(':
                return Op.OPEN;
            case ')':
                return Op.CLOSE;
            default:
                return Op.NUM;
        }
    }

    public int precedence(char ch) {

        switch (ch) {
            case '/':
                return 3;
            case '*':
                return 2;
            case '+':
                return 1;
            case '-':
                return 1;
            case '(':
                return 0;
            default:
                return 0;
        }
    }

    public double performOp(double num1, double num2, char op) {

        switch (op) {
            case '/':
                return (double) (num1 / num2);
            case '*':
                return (double) num1 * num2;
            case '+':
                return (double) num1 + num2;
            case '-':
                return (double) num1 - num2;
            default:
                return (double) 0;
        }
    }

    public int precedenceComparator(char a, char b) {
//        System.out.println(a + " , " + b + " : " +  Integer.compare(precedence(a), precedence(b)));
        return Integer.compare(precedence(a), precedence(b));
    }

    public double evaluate(String s) {

        int num = 0;
        Deque<Double> numSt = new ArrayDeque<>();
        Deque<Character> opSt = new ArrayDeque<>();
        opSt.push('#');
        Op prevOpr = Op.HASH;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            System.out.println(c);
            Op opr = checkTYpe(c);
            if (opr == Op.NUM) {
                num = num * 10 + (c - '0');
            }
            else {
                if (prevOpr == Op.NUM) {
                    numSt.push((double) num);
                    num = 0;
                }

                if (opr == Op.CLOSE) {
                    //iterate and solve until Open brace in  opSt
                    while (opSt.size() > 1 && !opSt.peek().equals('(')) {
                        Double num2 = numSt.pop();
                        Double num1 = numSt.pop();
                        char op = opSt.pop();
                        numSt.push(performOp(num1, num2, op));
                    }
                    opSt.pop();
                } else if (opr == Op.OPEN) {
                    opSt.push('(');
                } else if (opSt.size() > 0 && precedenceComparator(opSt.peek(), c) <= 0) {
                    opSt.push(c);
                } else {
                    //process and solve until equal or smaller precedence is not found
                    while (opSt.size() > 1 && precedenceComparator(opSt.peek(), c) > 0) {
                        char op = opSt.pop();
                        double num2 = numSt.pop();
                        double num1 = numSt.pop();
                        numSt.push(performOp(num1, num2, op));
                    }
                    opSt.push(c);
                }
            }
            prevOpr = opr;
        }


        //process until empty all stacks
        if (prevOpr == Op.NUM) {
            numSt.push((double) num);
        }

        while (numSt.size() >= 2) {
            char op = opSt.pop();
            double num2 = numSt.pop();
            double num1 = numSt.pop();
            numSt.push(performOp(num1, num2, op));
        }
        return numSt.size() > 0 ? numSt.pop() : (double) 0;
    }

    public static void main(String args[]) {

        ShuntingYard s = new ShuntingYard();
//        double res = s.evaluate("1 + 12");
//        System.out.println("1 + 12 = " + res );
//        System.out.println(" 6-4 / 2 = " + s.evaluate(" 6-4 / 2 "));
//        System.out.println("2*(5+5*2)/3+(6/2+8) = " + s.evaluate("2*(5+5*2)/3+(6/2+8)"));
        System.out.println("(2+6* 3+5- (3*14/7+2)*5)+3 = " + s.evaluate("(2+6* 3+5- (3*14/7+2)*5)+3"));
        System.out.println(" 2 - 1 + 2 =" + s.evaluate(" 2-1 + 2 "));
    }
}
