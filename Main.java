import java.util.Scanner;

public class Main
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter expression: ");
        String expression = sc.nextLine();
        Node n = makeTree(expression);
        postOrderTraversing(n);
        sc.close();
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
    static void postOrderTraversing(Node n)
    {
        if(n == null){return;}
        postOrderTraversing(n.getLeft());
        postOrderTraversing(n.getRight());
        System.out.print(n.getData() + " ");
    }

}

