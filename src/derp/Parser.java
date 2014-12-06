package derp;

import java.util.*;

public class Parser {
    private ArrayList<Statement> tokens;
    private ArrayList<Variable> variables;
    private int currIter;

    public Parser(String in) {
        variables = new ArrayList();
        currIter = 0;
        tokens = getTokens(in);
        currIter++;
    }

    public Parser() {
        variables = new ArrayList();
        currIter = 0;
        tokens = null;
    }

    public void reInit(String in) {
        currIter = 0;
        tokens = getTokens(in);
        currIter++;
    }

    public ArrayList<Statement> getParser() {
        return tokens;
    }

    private int varsContains(ArrayList<Variable> vars, String var) {
        for (int i = 0; i < vars.size(); i++) {
            if (vars.get(i).getName().equals(var)) {
                return i;
            }
        }
        return -1;
    }

    public String excecuteInstructionTree() {
        ArrayList<Statement> instructions = tokens;
        if (checkSyntax(tokens)) {
            try {
                //ArrayList<Statement> instructions = tokens;
                ArrayList<Statement> currTuple = new ArrayList();
                int commandIndex = 0;
                while (instructions.size() > 1) {
                    //System.out.println(instructions);
                    //Start Mulitiple line handeling code
                    if (!(instructions.get(0).eval().equals("(")) && instructions.size() > 1) {
                        instructions.remove(0);
                    }
                    //End Mulitiple line handeling code
                    for (int i = 0; i < instructions.size() - 4; i++) {
                        if (instructions.get(i).eval().equals("(")) {
                            if (instructions.get(i + 4).eval().equals(")")) {
                                commandIndex = i;
                                currTuple = new ArrayList();
                                currTuple.add(instructions.get(i + 1));
                                currTuple.add(instructions.get(i + 2));  //Adds the single tuple to the execution statment
                                currTuple.add(instructions.get(i + 3));
                                //System.out.println("Found a tuple at "+commandIndex);
                                break;
                            }
                        }
                        //System.out.println("Not a tuple");
                    }
                    if (currTuple.get(0).eval().equalsIgnoreCase("derp") && currTuple.get(1).eval().equalsIgnoreCase("derp") && currTuple.get(2).eval().equalsIgnoreCase("derp")) {
                        Scanner s = new Scanner(System.in);
                        System.out.print("Input: ");
                        String userInput = s.nextLine();
                        //System.out.println("Thx");
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.set(commandIndex, new Statement(userInput));
                    } else if (currTuple.get(0).eval().equalsIgnoreCase("derp") && currTuple.get(1).eval().equalsIgnoreCase("derp")) {// Print out x and pass
                        String firstExpression = currTuple.get(2).eval();
                        int firstExpressionLocation = varsContains(variables, firstExpression);
                        if (firstExpressionLocation != -1) {
                            firstExpression = variables.get(firstExpressionLocation).eval();
                        }
                        String result = new Statement(firstExpression).eval().replace("_", " ");
                        System.out.print("Print: ");
                        if (isString(firstExpression)) {
                            System.out.println(result.substring(1, result.length() - 1));
                        } else {
                            System.out.println(result);
                        }
                        //System.out.println(new Statement(firstExpression).eval());
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.set(commandIndex, new Statement(firstExpression));

                    } else if (currTuple.get(0).eval().equalsIgnoreCase("derple") && currTuple.get(1).eval().equalsIgnoreCase("derp")) {
                        String firstExpression = currTuple.get(2).eval();
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.set(commandIndex, new Statement(firstExpression));
                    } else if (currTuple.get(0).eval().equalsIgnoreCase("derp") && currTuple.get(2).eval().equalsIgnoreCase("derp")) {
                        String firstExpression = currTuple.get(1).eval();
                        int firstExpressionLocation = varsContains(variables, firstExpression);
                        if (firstExpressionLocation != -1) {
                            firstExpression = variables.get(firstExpressionLocation).eval();
                        }
                        if (isNumber(firstExpression)) {
                            Double num = new Double(firstExpression);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(Math.abs(num) + ""));
                        } else {
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(firstExpression));
                        }
                    } else if (currTuple.get(1).eval().equalsIgnoreCase("derp") && currTuple.get(2).eval().equalsIgnoreCase("derp")) {
                        String firstExpression = currTuple.get(0).eval();
                        int firstExpressionLocation = varsContains(variables, firstExpression);
                        if (firstExpressionLocation != -1) {
                            firstExpression = variables.get(firstExpressionLocation).eval();
                        }
                        Double firstNum;
                        Double floorNum;
                        boolean comp;
                        int result = 1;
                        if (isNumber(firstExpression)) {
                            firstNum = Double.parseDouble(firstExpression);
                            floorNum = Math.floor(firstNum);
                            comp = (firstNum - floorNum) == 0;
                        } else {
                            comp = false;
                            result = 2;
                        }
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        if (comp) {
                            result = 0;
                        }
                        instructions.set(commandIndex, new Statement(result + ""));
                    } else if (currTuple.get(0).eval().equalsIgnoreCase("derple") && currTuple.get(1).eval().equalsIgnoreCase("derple")) {
                        Random r = new Random();
                        String firstExpression = currTuple.get(2).eval();
                        int firstExpressionLocation = varsContains(variables, firstExpression);
                        if (firstExpressionLocation != -1) {
                            firstExpression = variables.get(firstExpressionLocation).eval();
                        }
                        if (isNumber(firstExpression)) {
                            Double d = Double.parseDouble(firstExpression);
                            Integer result = r.nextInt(d.intValue());
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(result.toString()));
                        } else {
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(firstExpression));
                        }
                    } else if (currTuple.get(0).eval().equalsIgnoreCase("derp") && currTuple.get(1).eval().equalsIgnoreCase("derple")) {
                        String firstExpression = currTuple.get(2).eval();
                        int firstExpressionLocation = varsContains(variables, firstExpression);
                        if (firstExpressionLocation != -1) {
                            firstExpression = variables.get(firstExpressionLocation).eval();
                        }
                        int result = 0;
                        if (isList(firstExpression)) {
                            List temp = new List(firstExpression);
                            result = temp.size();
                        } else if (isString(firstExpression)) {
                            result = firstExpression.length() - 2;
                        } else if (isNumber(firstExpression)) {
                            result = new Double(firstExpression).intValue();
                        } else {
                            throw new Exception();
                        }
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.set(commandIndex, new Statement(result + ""));
                    } else if (currTuple.get(0).eval().equalsIgnoreCase("derp")) {// x + y
                        //System.out.println("ADD");
                        String firstExpression = currTuple.get(1).eval();
                        String secondExpression = currTuple.get(2).eval();
                        String firstName = currTuple.get(1).eval();
                        String secondName = currTuple.get(2).eval();
                        int firstExpressionLocation = varsContains(variables, firstExpression);
                        int secondExpressionLocation = varsContains(variables, secondExpression);
                        if (firstExpressionLocation != -1) {
                            firstExpression = variables.get(firstExpressionLocation).eval();
                        }
                        if (secondExpressionLocation != -1) {
                            secondExpression = variables.get(secondExpressionLocation).eval();
                        }
                        //System.out.println(firstExpressionLocation + ", " + secondExpressionLocation);
                        //System.out.println(firstExpression + ", " + secondExpression);
                        if (isNumber(firstExpression) && isNumber(secondExpression)) {
                            //System.out.println("Adding 2 nums");
                            Double result = Double.parseDouble(firstExpression) + Double.parseDouble(secondExpression);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(result.toString()));
                        } else if (isList(firstExpression) && !isList(secondExpression)) {
                            //System.out.println("Add to end of list");
                            List list = new List(firstExpression);
                            list.add(secondExpression);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(list.eval()));
                        } else if (!isList(firstExpression) && isList(secondExpression)) {
                            //System.out.println("add to beg of list");
                            List list = new List(secondExpression);
                            list.add(0, firstExpression);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(list.eval()));
                        } else if (isList(firstExpression) && isList(secondExpression)) { /////////////////////////////////////////////////////Glitchy
                            //System.out.println("add 2 lists");
                            List list = new List(firstExpression);
                            List list2 = new List(secondExpression);
                            //System.out.println(list2.eval());
                            list.add(list2.eval());
                            //System.out.println(list.eval());
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(list.eval()));
                        } else if (!isString(firstExpression) && isString(secondExpression)) {
                            //System.out.println("add str to beg");
                            String string1 = firstExpression;
                            String string2 = secondExpression;
                            string2 = string2.replace("\"", "");
                            string1 += string2;
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement("\"" + string1 + "\""));
                        } else if (isString(firstExpression) && !isString(secondExpression)) {
                            //System.out.println("add str to end");
                            String string2 = secondExpression;
                            String string1 = firstExpression;
                            string1 = string1.replace("\"", "");
                            //System.out.println(string1);
                            string1 += string2;
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement("\"" + string1 + "\""));
                        } else if (isString(firstExpression) && isString(secondExpression)) {
                            //System.out.println("Concat");
                            String string1 = firstExpression;
                            String string2 = secondExpression;
                            string1 = string1.replace("\"", "");
                            string2 = string2.replace("\"", "");
                            string1 += string2;
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement("\"" + string1 + "\""));
                        } else {
                            throw new Exception();
                        }
                    } else if (currTuple.get(1).eval().equalsIgnoreCase("derp")) {// define x as y
                        String name = currTuple.get(0).eval();
                        Expression exp = currTuple.get(2).get();
                        int firstExpressionLocation = varsContains(variables, name);
                        if (firstExpressionLocation != -1) {
                            //System.out.println("Variable " + name + " has already been defined");
                            variables.remove(firstExpressionLocation);
                        }
                        int secondExpressionLocation = varsContains(variables, exp.eval());
                        if (secondExpressionLocation != -1) {
                            exp = variables.get(secondExpressionLocation);
                        }
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);

                        variables.add(new Variable(name, exp));
                        instructions.set(commandIndex, new Statement(exp.eval()));
                        //instructions.remove(commandIndex);
                    } else if (currTuple.get(2).eval().equalsIgnoreCase("derp")) {// x - y
                        String firstExpression = currTuple.get(0).eval();
                        String secondExpression = currTuple.get(1).eval();
                        String firstName = currTuple.get(0).eval();
                        String secondName = currTuple.get(1).eval();
                        int firstExpressionLocation = varsContains(variables, firstExpression);
                        if (firstExpressionLocation != -1) {
                            firstExpression = variables.get(firstExpressionLocation).eval();
                        }
                        int secondExpressionLocation = varsContains(variables, secondExpression);
                        if (secondExpressionLocation != -1) {
                            secondExpression = variables.get(secondExpressionLocation).eval();
                        }
                        if (isNumber(firstExpression) && isNumber(secondExpression)) {
                            Double result = Double.parseDouble(firstExpression) - Double.parseDouble(secondExpression);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(result.toString()));
                        } else if (isList(firstExpression) && isNumber(secondExpression)) {
                            //System.out.println("Remove index");
                            Double index = new Double(secondExpression);
                            List list = new List(firstExpression);
                            String wasThere;
                            wasThere = list.get(index.intValue());
                            list.remove(index.intValue());
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(list.eval()));
                        } else if (!isList(firstExpression) && isList(secondExpression)) {
                            //System.out.println("remove data value");
                            List list = new List(secondExpression);
                            list.remove(firstExpression);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(list.eval()));
                        } else if (isString(firstExpression) && isNumber(secondExpression)) {
                            //System.out.println("remove str index");
                            Double index = new Double(secondExpression);
                            String string1 = firstExpression;
                            string1 = string1.replace("\"", "");
                            //System.out.println(string1);
                            string1 = string1.substring(0, index.intValue()) + string1.substring(index.intValue() + 1, string1.length());
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement("\"" + string1 + "\""));
                        } else if (isNumber(firstExpression) && isString(secondExpression)) {
                            //System.out.println("remove Num from str 1st");
                            Double num1 = new Double(firstExpression);
                            Integer num1Int = new Integer(num1.intValue());
                            String string1 = num1Int.toString();
                            String string2 = secondExpression;
                            string2 = string2.replace("\"", "");
                            string2 = string2.replaceFirst(string1, "");
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement("\"" + string2 + "\""));
                        } else if (isString(firstExpression) && isString(secondExpression)) {
                            //System.out.println("remove");
                            String string1 = firstExpression;
                            String string2 = secondExpression;
                            string1 = string1.replace("\"", "");
                            string2 = string2.replace("\"", "");
                            string2 = string2.replaceFirst(string1, "");
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement("\"" + string2 + "\""));
                        } else if (isList(firstExpression) && isString(secondExpression)) {
                            List list = new List(firstExpression);
                            String string2 = secondExpression.replace("\"", "");
                            Double index1 = new Double(list.get(0));
                            Double index2 = new Double(list.get(1));
                            string2 = string2.substring(index1.intValue(), index2.intValue());
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement("\"" + string2 + "\""));
                        } else if (isList(firstExpression) && isList(secondExpression)) { /////////////////////////////////////////////////////Glitchy
                            //System.out.println("add 2 lists");
                            List list = new List(firstExpression);
                            List list2 = new List(secondExpression);
                            //System.out.println(list2.eval());
                            list2.add(0, list.eval());
                            //System.out.println(list.eval());
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(list2.eval()));
                        } else {
                            throw new Exception();
                        }
                    } else if (currTuple.get(0).eval().equalsIgnoreCase("derple")) {// x * y
                        String firstExpression = currTuple.get(1).eval();
                        String secondExpression = currTuple.get(2).eval();
                        int firstExpressionLocation = varsContains(variables, firstExpression);
                        if (firstExpressionLocation != -1) {
                            firstExpression = variables.get(firstExpressionLocation).eval();
                        }
                        int secondExpressionLocation = varsContains(variables, secondExpression);
                        if (secondExpressionLocation != -1) {
                            secondExpression = variables.get(secondExpressionLocation).eval();
                        }
                        if (isNumber(firstExpression) && isNumber(secondExpression)) {
                            Double result = Double.parseDouble(firstExpression) * Double.parseDouble(secondExpression);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(result.toString()));
                        } else if (isList(firstExpression) && isNumber(secondExpression)) {
                            List list = new List(firstExpression);
                            Double num = new Double(secondExpression);
                            ArrayList<String> listOut = new ArrayList<>();
                            for (int i = 0; i < num; i++) {
                                listOut.addAll(list.getList());
                            }
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(listOut.toString()));
                        } else if (isString(firstExpression) && isNumber(secondExpression)) {
                            String string1 = firstExpression.replace("\"", "");
                            String out = "";
                            Double num = new Double(secondExpression);
                            for (int i = 0; i < num; i++) {
                                out += string1;
                            }
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(out.toString()));
                        } else if (isList(firstExpression) && isList(secondExpression)) {
                            //System.out.println("add 2 lists");
                            List list = new List(firstExpression);
                            List list2 = new List(secondExpression);
                            for (int i = 0; i < list2.size(); i++) {
                                list.add(list2.get(i));
                            }
                            //System.err.println("Why are you adding to an unsaved list?");
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(list.eval()));
                        } else {
                            throw new Exception();
                        }
                    } else if (currTuple.get(2).eval().equalsIgnoreCase("derple")) {// x / y
                        String firstExpression = currTuple.get(0).eval();
                        String secondExpression = currTuple.get(1).eval();
                        int firstExpressionLocation = varsContains(variables, firstExpression);
                        if (firstExpressionLocation != -1) {
                            firstExpression = variables.get(firstExpressionLocation).eval();
                        }
                        int secondExpressionLocation = varsContains(variables, secondExpression);
                        if (secondExpressionLocation != -1) {
                            secondExpression = variables.get(secondExpressionLocation).eval();
                        }
                        if (isNumber(firstExpression) && isNumber(secondExpression)) {
                            Double result = Double.parseDouble(firstExpression) / Double.parseDouble(secondExpression);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(result.toString()));
                        } else if (!isList(firstExpression) && isList(secondExpression)) {
                            List list = new List(secondExpression);
                            list.removeAll(firstExpression);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(list.eval()));
                        } else if (isList(firstExpression) && isList(secondExpression)) {
                            //System.out.println("add 2 lists");
                            List list = new List(firstExpression);
                            List list2 = new List(secondExpression);
                            for (int i = 0; i < list.size(); i++) {
                                list2.add(0, list.get(i));
                            }
                            //System.err.println("Why are you adding to an unsaved list?");
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement(list2.eval()));
                        } else if (isNumber(firstExpression) && isString(secondExpression)) {
                            Double num1 = new Double(firstExpression);
                            Integer num1Int = new Integer(num1.intValue());
                            String string1 = num1Int.toString();
                            String string2 = secondExpression;
                            string2 = string2.replace("\"", "");
                            string2 = string2.replace(string1, "");
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement("\"" + string2 + "\""));
                        } else if ((isString(firstExpression) && isString(secondExpression))) {
                            String string1 = firstExpression.replace("\"", "");
                            String string2 = secondExpression.replace("\"", "");
                            string2 = string2.replace(string1, "");
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement("\"" + string2 + "\""));
                        } else {
                            throw new Exception();
                        }
                    } else if (currTuple.get(1).eval().equalsIgnoreCase("isderp")) {// test if x == y
                        //Documentation (this is so confusing not really)

                        //returns 2 if second term is greater than first term
                        //(1 isderp 2) return 2
                        //
                        //return 0 if first term is greater than second term
                        //(2 isderp 1) returns 0
                        //
                        //return 0 if equal
                        //(1 isderp 1) and ("abc" isderp [1,2,3]) returns 0

                        String firstExpression = currTuple.get(0).eval();
                        String secondExpression = currTuple.get(2).eval();
                        int firstExpressionLocation = varsContains(variables, firstExpression);
                        if (firstExpressionLocation != -1) {
                            firstExpression = variables.get(firstExpressionLocation).eval();
                        }
                        int secondExpressionLocation = varsContains(variables, secondExpression);
                        if (secondExpressionLocation != -1) {
                            secondExpression = variables.get(secondExpressionLocation).eval();
                        }
                        double comp = 0;
                        if (isString(firstExpression) && isString(secondExpression)) {
                            //System.out.println("Comp Strings");
                            comp = secondExpression.compareTo(firstExpression);
                        } else if (isNumber(firstExpression) && isNumber(secondExpression)) {
                            //System.out.println("Comp Nums");
                            comp = Double.parseDouble(secondExpression) - Double.parseDouble(firstExpression);
                        } else if (isList(firstExpression) && isList(secondExpression)) {
                            //System.out.println("Comp Lists");
                            List list1 = new List(firstExpression);
                            String list1String = "";
                            for (int i = 0; i < list1.size(); i++) {
                                list1String += list1.get(i);
                            }
                            List list2 = new List(secondExpression);
                            String list2String = "";
                            for (int i = 0; i < list2.size(); i++) {
                                list2String += list2.get(i);
                            }
                            comp = list2String.compareTo(list1String);
                        } else if (isString(firstExpression) && isList(secondExpression)) {
                            //System.out.println("Comp String - List");
                            //System.out.println(new List(secondExpression).size());
                            //System.out.println(firstExpression.length() - 2);
                            List list2 = new List(secondExpression);
                            String list2String = "\"";
                            for (int i = 0; i < list2.size(); i++) {
                                list2String += list2.get(i);
                            }
                            list2String += "\"";
                            //System.out.println(list2String);
                            //System.out.println(firstExpression);
                            comp = list2String.compareTo(firstExpression);
                        } else if (isList(firstExpression) && isString(secondExpression)) {
                            //System.out.println("Comp List - String");
                            //System.out.println(new List(firstExpression).size());
                            //System.out.println(secondExpression.length() - 2);
                            List list1 = new List(firstExpression);
                            String list1String = "\"";
                            for (int i = 0; i < list1.size(); i++) {
                                list1String += list1.get(i);
                            }
                            list1String += "\"";
                            //System.out.println(secondExpression);
                            //System.out.println(list1String);
                            comp = secondExpression.compareTo(list1String);
                        } else {
                            throw new Exception();
                        }
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        int result = 1;
                        if (comp < 0) {
                            result--;
                        }
                        if (comp > 0) {
                            result++;
                        }
                        instructions.set(commandIndex, new Statement(result + ""));
                    } else if (currTuple.get(1).eval().equalsIgnoreCase("derple")) {// get(x)
                        String firstExpression = currTuple.get(0).eval();
                        int firstExpressionLocation = varsContains(variables, firstExpression);
                        if (firstExpressionLocation != -1) {
                            firstExpression = variables.get(firstExpressionLocation).eval();
                        }
                        String secondExpression = currTuple.get(2).eval();
                        int secondExpressionLocation = varsContains(variables, secondExpression);
                        if (secondExpressionLocation != -1) {
                            secondExpression = variables.get(secondExpressionLocation).eval();
                        }
                        if (isList(secondExpression)) {
                            List list = new List(secondExpression);
                            Double index = Double.parseDouble(firstExpression);
                            String listResult = list.get(index.intValue());
                            while (listResult.equals("")) {
                                index++;
                                listResult = list.get(index.intValue());
                            }
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            if (!isNumber(listResult) && varsContains(variables, listResult) == -1 && !isList(listResult) && !isString(listResult)) {
                                //System.out.println("Get returned Command Set");
                                listResult = listResult.replace("_", " ");
                                //System.out.println("List Result: "+listResult);
                                //System.out.println(commandIndex);
                                instructions.remove(commandIndex);
                                ArrayList<Statement> addIn = getTokens(listResult);
                                //System.out.println("Addin is: "+addIn);
                                for (int i = addIn.size() - 1; i >= 0; i--) {
                                    //System.out.println(instructions);
                                    instructions.add(commandIndex, addIn.get(i));
                                }
                            } else {
                                //System.out.println("Get did not return Command Set");
                                instructions.set(commandIndex, new Statement(listResult));
                            }
                        } else if (isString(secondExpression)) {
                            String string;
                            if (secondExpressionLocation != -1) {
                                //System.out.println("List passed in as Var");
                                string = variables.get(secondExpressionLocation).eval();
                            } else {
                                string = currTuple.get(2).get().eval();
                            }
                            string = string.replace("\"", "");
                            Double index = Double.parseDouble(firstExpression);
                            String result = string.charAt(index.intValue()) + "";
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.remove(commandIndex);
                            instructions.set(commandIndex, new Statement("\"" + result + "\""));
                        } else {
                            throw new Exception();
                        }
                    } else {
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                        instructions.remove(commandIndex);
                    }
                    //System.out.println(commandIndex);
                }
                //System.out.println(instructions);
                //System.out.println(variables);
                //System.out.println(commandIndex);
                return instructions.get(0).eval().toString();
            } catch (Exception e) {
                //System.out.println(e.getMessage());
                System.err.println("Error in command syntax");
                System.err.println("Current Runtime List:");
                System.err.println(instructions);
                return "0";
            }
        }
        System.err.println("Error in paren syntax");
        return "0";
    }

    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isList(String s) {
        if (s.startsWith("[") && s.endsWith("]")) {
            return true;
        }
        return false;
    }

    private boolean isString(String s) {
        if (s.startsWith("\"") && s.endsWith("\"")) {
            return true;
        }
        return false;
    }

    private ArrayList<Statement> getTokens(String in) {
        ArrayList<Statement> hadoken = new ArrayList();
        String input = in;
        if (in.isEmpty()) {
            System.err.println("Ur supposed to type something in...");
            input = "(derple derp 0)";
        }
        if (input.charAt(0) == '(') {
            input = "( " + input.substring(1);
        }
        //System.out.println(input);
        if (input.charAt(input.length() - 1) == ')') {
            input = input.substring(0, input.length() - 1) + " )";
        }
        //System.out.println(input);
        StringBuilder output = new StringBuilder(input.charAt(0) + " ");
        for (int i = 1; i < input.length() - 1; i++) {
            if (input.charAt(i) == '"') {
                StringBuilder toAppend = new StringBuilder("\"");
                i++;
                while (input.charAt(i) != '"') {
                    toAppend.append(input.charAt(i));
                    i++;
                }
                //System.out.println(toAppend.toString().replace(" ", "_"));
                //output.append(toAppend.toString().replace(" ", "\0"));
                output.append(toAppend.toString().replace(" ", "_"));
                //output.append(toAppend);
                //System.out.println(output);
            }
            if (input.charAt(i) == '[') {
                Stack parens = new Stack();
                parens.push(null);
                //System.out.println("found a list while parsing");
                StringBuilder toAppend = new StringBuilder();
                while (!parens.isEmpty()) {
                    toAppend.append(input.charAt(i));
                    i++;
                    if (input.charAt(i) == '[') {
                        parens.push(null);
                    }
                    if (input.charAt(i) == ']') {
                        parens.pop();
                    }
                    //System.out.println(toAppend);
                }
                //toAppend.append("]");
                int currIndex = 0;
                String listString = toAppend.toString();
                String op = "";
                while (currIndex < listString.length()) {
                    //System.out.println("Removing Spaces");
                    if (listString.charAt(currIndex) == ',' && listString.charAt(currIndex + 1) == ' ') {
                        op += listString.charAt(currIndex);
                        currIndex++;
                    } else {
                        op += listString.charAt(currIndex);
                    }
                    currIndex++;
                }
                //op = op+"]";
                op = op.replace(" ", "_");
                output.append(op);
                //i++;
            }
            if (input.charAt(i) == '(' || input.charAt(i) == ')') {
                if (input.charAt(i - 1) != ' ') {
                    output.append(" ");
                }
                output.append(input.charAt(i));
                //System.out.println(input.charAt(i));
                if (input.charAt(i + 1) != ' ') {
                    output.append(" ");
                }
            } else {
                output.append(input.charAt(i));
                //System.out.println(input.charAt(i));
            }
        }
        output.append(input.charAt(input.length() - 1));
        String[] temp = output.toString().split(" ");
        //System.out.println(output);
        //tokens.addAll(Arrays.asList(temp));
        for (int i = 0; i < temp.length; i++) {
            //System.out.println(temp[i]);
            if (!temp[i].equals("")) {
                //hadoken.add(new Statement(temp[i].replace("\0", " "))); //handles strings now not rlly yes it does
                hadoken.add(new Statement(temp[i]));
                //System.out.println(hadoken);
            }
        }
        //System.out.println(hadoken);
        //handle strings initialized in the list
        return hadoken;
    }

    private boolean checkSyntax(ArrayList<Statement> in) {
        Stack parenStack = new Stack();
        boolean addedTo = false;
        try {
            for (int i = 0; i < in.size(); i++) {
                if (in.get(i).eval().equals("(")) {
                    parenStack.push(null);
                    addedTo = true;
                }
                if (in.get(i).eval().equals(")")) {
                    parenStack.pop();
                }
                //System.out.println(parenStack);
            }
        } catch (Exception e) {
            //System.err.println("Paren mismatch");
            return false;
        }
        if (!parenStack.isEmpty() || !addedTo) {
            //System.err.println("Paren mismatch");
            return false;
        }
        return true;
    }
}