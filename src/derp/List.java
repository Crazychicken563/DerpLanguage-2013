package derp;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class List extends Expression {

    private ArrayList<String> list;

    public List(ArrayList<String> value) {
        //list = doubleList(value);
        list = value;
    }

    public List(String in) {
        //list = doubleList(convertToArray(in));
        list = convertToArray(in);
    }

    public List() {
        this.list = new ArrayList<>();
    }

    private ArrayList<String> doubleList(ArrayList<String> in) {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < in.size(); i++) {
            if (isNumber(in.get(i))) {
                output.add((new Double(in.get(i))).toString());
            } else {
                output.add(in.get(i));
            }
        }
        return output;
    }

    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private ArrayList<String> convertToArray(String in) {
        ArrayList<String> output = new ArrayList();
        String input = "";
        //handle empty staements
        for (int i = 0; i < in.length() - 1; i++) {
            if ((in.charAt(i) == ',' || in.charAt(i) == '[') && in.charAt(i + 1) == ',') {
                input += in.charAt(i) + "_,";
                //i++;
            } else {
                input += in.charAt(i);
            }
        }
        input += "]";
        //System.out.println(input);
        //end code
        StringTokenizer tokenizer = new StringTokenizer(input.substring(1, input.length() - 1), ",");
        String currToken = "";
        while (tokenizer.hasMoreTokens()) {
            currToken = tokenizer.nextToken();
            //System.out.println(currToken);
            if (currToken.contains("[")) {
                currToken += "," + tokenizer.nextToken();
                while (!(currToken.contains("]"))) {
                    currToken += "," + tokenizer.nextToken();
                }
            }
            //System.out.println(currToken);
            if (currToken.equals("_")) {
                output.add("");
            } else {
                output.add(currToken);
            }
        }
        return output;
    }

    public List(List list) {
        this.list = list.getList();
    }

    @Override
    public String eval() { //OMGRD FIX THIS YAY
        int currIndex = 0;
        String output = "";
        String listString = list.toString();
        while (currIndex < listString.length()) {
            if (listString.charAt(currIndex) == ',' && listString.charAt(currIndex) == ' ') {
                output += listString.charAt(currIndex);
                currIndex++;
            } else {
                output += listString.charAt(currIndex);
            }
            currIndex++;
        }
        //System.out.println(output);
        //remove white space...
        //System.out.println(output);
        output = output.replace(" ", "");
        //System.out.println(output);
        return output;
    }

    public ArrayList<String> getList() {
        //System.out.println("List Return");
        //System.out.println(list);
        return list;
    }

    public String get(int index) {
        return list.get(index);
    }

    public void set(int index, String newVal) {
        list.set(index, newVal);
    }

    public void add(String newVal) {
        list.add(newVal);
    }

    public void add(int index, String newVal) {
        list.add(index, newVal);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void remove(String value) {
        list.remove(value);
    }

    public void removeAll(String value) {
        //System.out.println(value);
        //System.out.println(list);
        while (list.contains(value)) {
            list.remove(value);
        }
    }

    public int size() {
        return list.size();
    }
}
