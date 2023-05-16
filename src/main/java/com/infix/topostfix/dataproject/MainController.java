package com.infix.topostfix.dataproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private Label ExpressionLabel;
    @FXML
    private TextField ExpressionField;
    @FXML
    private Button ConvertButton;
    @FXML
    private Label ExpressionValue;

    @FXML
    protected void onConvertButtonClick() {
        String expression = ExpressionField.getText().trim();
        Node n = makeTree(expression);
        StringBuilder s = new StringBuilder();
        String postFix = postOrderTraversing(n,s);
        ExpressionLabel.setText(postFix);
        ExpressionValue.setText(String.valueOf(evaluate(postFix)));
    }
    static boolean isAnOperation(char x)
    {
        return x == '+' || x == '-' || x == '*' || x == '/'|| x == '^' || x == '%';
    }
    static Node makeTree(String expression)
    {
        Stack s = new Stack();
        Stack barc = new Stack();
        Node root = null;
        Node temp;

        for (int i = 0; i < expression.length();i++)
        {
            char x = expression.charAt(i);
            if (x == '(')
            {
                barc.push(new Node("("));
            }
            else if (!isAnOperation(x))
            {
                StringBuilder tempStr = new StringBuilder();
                tempStr.append(String.valueOf(x));
                int j;
                for (j = i+1;j<expression.length();j++)
                {
                    if (!isAnOperation(expression.charAt(j)))
                    {
                        tempStr.append(expression.charAt(j));
                    }
                    else
                    {
                        break;
                    }
                }
                s.push(new Node(tempStr.toString()));
                i = j-1;
            }
            else
            {
                if (!barc.isEmpty())
                {
                    StringBuilder tempStr = new StringBuilder();
                    int j;
                    for (j = i+1;j<expression.length();j++)
                    {
                        if (expression.charAt(j) == ')')
                        {
                            break;
                        }

                        else if (!isAnOperation(expression.charAt(j)))
                        {
                            tempStr.append(expression.charAt(j));
                        }
                    }
                    i = j;
                    barc.pop();
                    s.push(new Node(tempStr.toString()));
                }
                if (s.getSize() < 2)
                {
                    root = new Node(String.valueOf(x));
                }else{
                    temp = new Node(String.valueOf(x));
                    temp.setRight(s.pop());

                    temp.setLeft(s.pop());

                    s.push(temp);
                }
            }

        }
        root.setRight(s.pop());
        root.setLeft(s.pop());
        return root;
    }
    static String postOrderTraversing(Node n,StringBuilder s)
    {
        if(n == null){return "";}
        postOrderTraversing(n.getLeft(),s);
        postOrderTraversing(n.getRight(),s);
        s.append(n.getData() + " ");
        return s.toString();
    }
    
    static double evaluate(String expression)
    {
        String[] parts = expression.split(" ");
        // {n,m,-,x,y,+,/}
        int num1 = 0,num2 = 0;
        double[] partOfEqu = new double[2];
        boolean num1Pass = false;
        boolean num2Pass = false;
        int index = 0;
        int opp = 0;
        String finalOpp = null;
        for (String part: parts)
        {
            if (opp == 2)
            {
                finalOpp = part;
                break;
            }
            if (!num1Pass)
            {
                num1 = Integer.parseInt(part);
                num1Pass = true;
            } else if (!num2Pass) {
                num2 = Integer.parseInt(part);
                num2Pass = true;
            }
            else{
                partOfEqu[index] = calculate(num1,num2,part);
                index++;
                num1Pass = num2Pass = false;
                opp++;
            }
        }


        return calculate(partOfEqu[0],partOfEqu[1],finalOpp);
    }
    
    static double calculate(double num1, double num2,String expression)
    {
        if(expression.contains("+"))
        {
            return num1+num2;
        } else if (expression.contains("-")) {
            return num1-num2;
        } else if (expression.contains("*")) {
            return num1*num2;
        } else if (expression.contains("/")) {
            return num1/num2;
        } else if (expression.contains("^")) {
            return Math.pow(num1,num2);
        }
        else {
            return 0;
        }
    }
    
}