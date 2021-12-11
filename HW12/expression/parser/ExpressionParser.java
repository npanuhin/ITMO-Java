package expression.parser;

import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

import expression.*;
import expression.parser.*;


public class ExpressionParser implements Parser {
    private static Set<Character> variables = new HashSet<>(Arrays.asList('x', 'y', 'z'));
    private AbstractStreamer expr;

    private boolean isDigit(char c) {
        // return Character.isDigit(c);
        return '0' <= c && c <= '9';
    }

    private void skipWhitespaces() {
        while (expr.hasNextChar() && Character.isWhitespace(expr.curChar())) {
            expr.getChar();
        }
    }

    private int parseNextInt(boolean isNegative) {
        StringBuilder number = new StringBuilder();
        if (isNegative) {
            number.append('-');
        }
        while (expr.hasNextChar() && isDigit(expr.curChar())) {
            number.append(expr.getChar());
        }
        return Integer.parseInt(number.toString());
    }

    private AbstractExpression parsePriority3() {
        // System.err.println("parsePriority3(\"" + expr.substring(pos) + "\")");
        skipWhitespaces();

        if (expr.skipIfMatch('(')) {
            AbstractExpression result = parsePriority1();
            expr.expectChar(')');
            return result;
        }

        if (expr.skipIfMatch('-')) {
            if (isDigit(expr.curChar())) {
                return new Const(parseNextInt(true));
            } else {
                return new Minus(parsePriority3());
            }

        } else if (isDigit(expr.curChar())) {
            return new Const(parseNextInt(false));

        } else if (variables.contains(expr.curChar())) {
            return new Variable(expr.getChar());

        } else {
            throw new IllegalArgumentException(
                "Can not identify expression starting with char: \"" + expr.curChar() + "\"..."
            );
        }
    }

    private AbstractExpression parsePriority2() {
        // System.err.println("parsePriority2(\"" + expr.substring(pos) + "\")");
        AbstractExpression result = parsePriority3();;

        skipWhitespaces();
        while (expr.hasNextChar()) {

            if (expr.skipIfMatch('*')) {
                result = new Multiply(result, parsePriority3());
            } else if (expr.skipIfMatch('/')) {
                result = new Divide(result, parsePriority3());
            } else {
                break;
            }
            skipWhitespaces();
        }

        return result;
    }

    private AbstractExpression parsePriority1() {
        // System.err.println("parsePriority1(\"" + expr.substring(pos) + "\")");
        AbstractExpression result = parsePriority2();;

        skipWhitespaces();
        while (expr.hasNextChar()) {

            if (expr.skipIfMatch('+')) {
                result = new Add(result, parsePriority2());
            } else if (expr.skipIfMatch('-')) {
                result = new Subtract(result, parsePriority2());
            } else {
                break;
            }
            skipWhitespaces();
        }

        return result;
    }

    @Override
    public AbstractExpression parse(String expression) {
        this.expr = new StringStreamer(expression);
        return parsePriority1();
    }
}
