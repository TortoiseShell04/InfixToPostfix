package com.infix.to.postfix.infixtopostfix;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

public class InfixToPostfix {

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame();
        frame.setSize(358,311);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainAppPanel p = new MainAppPanel();
        frame.add(p);
        frame.setTitle("Infix to Postfix");
        frame.setLocationRelativeTo(null);
        ImageIcon icon;
        icon = new ImageIcon("icon.png");

        frame.setIconImage(icon.getImage());
        frame.setVisible(true);
    }
    static String runApp(String exp){
        String expression = exp;
        Node n = makeTree(expression);
        StringBuilder s = new StringBuilder();
        return postOrderTraversing(n,s);
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

}
