package com.example.calculator;

import java.util.Stack;

public class ExpressionEvaluator {

    enum operator{MULTIPLY,DIVIDE,ADDITION,SUBTRACTION};

    public static float Run(String expression)
    {
        float result=0;
        Stack<Float> valueStack=new Stack<Float>();
        Stack<Character> operatorStack=new Stack<Character>();
        String fetchedNumber;
        for(int i=0;i<expression.length();i++)
        {
            fetchedNumber="";

            if(expression.charAt(i)==' ')
            {
                continue;
            }
            if(expression.charAt(i)>='0'&&expression.charAt(i)<='9')
            {

                while(expression.length()>i&&((expression.charAt(i)>='0'&&expression.charAt(i)<='9')||expression.charAt(i)=='.'))
                {

                    fetchedNumber+=expression.charAt(i++);

                }
                valueStack.push(Float.parseFloat(fetchedNumber));
                i--;
            }
            else if(expression.charAt(i)=='+'||expression.charAt(i)=='-'||expression.charAt(i)=='/'||expression.charAt(i)=='*')
            {
                while(!operatorStack.empty()&&checkPrecedence(operatorStack.peek(),expression.charAt(i)))
                {
                    valueStack.push(applyOperation(operatorStack.pop(),valueStack.pop(),valueStack.pop()));
                }
                operatorStack.push(expression.charAt(i));
            }
        }

        while(!operatorStack.empty())
        {
            valueStack.push(applyOperation(operatorStack.pop(),valueStack.pop(),valueStack.pop()));
        }

        result=valueStack.pop();
        return result;
    }

    public static boolean checkPrecedence(char a,char b)
    {

        if(getPrecedence(a)<getPrecedence(b))
        {
            return true;
        }
        return false;
    }

    public static int getPrecedence(char a)
    {
        int precedence=0;

        if(a=='+')
        {
            precedence=operator.ADDITION.ordinal();
        }
        else if(a=='-')
        {
            precedence=operator.SUBTRACTION.ordinal();
        }
        else if(a=='/')
        {
            precedence=operator.DIVIDE.ordinal();
        }
        else if(a=='*')
        {
            precedence=operator.MULTIPLY.ordinal();
        }
        else
        {
            precedence=-1;
        }
        return precedence;

    }

    public static float applyOperation(char operator,float val1,float val2)
    {
        if(operator=='+')
        {
            return val1+val2;
        }
        else if(operator=='-')
        {
            return val2-val1;
        }
        else if(operator=='/')
        {
            return val2/val1;
        }
        else if(operator=='*')
        {
            return val2*val1;
        }
        return -1;
    }
}