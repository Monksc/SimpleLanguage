import java.io.*;
import java.util.*;
public class SimpleLanguage {
    
    public static final Scanner scanner = new Scanner(System.in);
    
    public static final String[] OPERANDS = {"*", "/", "+", "-", "^", "%"};
    
    public static int amountOfParenthesis = 0;
    public static int exitBlocksTo = -1;
    
    public static ArrayList<String> programLines = new ArrayList<String>();
    public static int currentProgramLine = 0;
    public static ArrayList<Integer> lineNumberOfLoop = new ArrayList<Integer>();
    public static ArrayList<Integer> amountOfParenthesisOfLoop = new ArrayList<Integer>();
    
    public static ArrayList<String> varStringNames = new ArrayList<String>();
    public static ArrayList<String> varString = new ArrayList<String>();
    
    public static ArrayList<String> varNumberNames = new ArrayList<String>();
    public static ArrayList<Double> varNumber = new ArrayList<Double>();
    
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = takeOutWhiteSpaces(line);
                if (isItALine(line)) {
                    programLines.add(line);
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        while (currentProgramLine < programLines.size()) {
            runLine(programLines.get(currentProgramLine));
            currentProgramLine++;
        }

        //printAllVarriables();
    }
    
    public static void printAllVarriables() {
        System.out.println("\nNow Printing Varriables");
        
        for (int i = 0; i < varStringNames.size(); i++) {
            System.out.println(varStringNames.get(i) + " == \"" + varString.get(i) + "\"");
        }
        System.out.println();
        for (int i = 0; i < varNumberNames.size(); i++) {
            System.out.println(varNumberNames.get(i) + " == " + varNumber.get(i));
        }
    }
    
    public static void runLine(String line) {
        
        if (line.indexOf("{") != -1) {
            amountOfParenthesis++;
        } else if (line.indexOf("}") != -1) {
            for (int i = 0; i < amountOfParenthesisOfLoop.size(); i++) {
                if (amountOfParenthesisOfLoop.get(i) > amountOfParenthesis) {
                    lineNumberOfLoop.remove(i);
                    amountOfParenthesisOfLoop.remove(i);
                }
            }
            amountOfParenthesis--;
        } 
        
        if (exitBlocksTo != -1) {
            if (amountOfParenthesis == exitBlocksTo) {
                exitBlocksTo = -1;
            }
        } else if (line.indexOf("//") == 0) {
        } else if (line.indexOf("==") != -1) {
            Double leftSide = fillInVarriablesForDouble(takeOutAllWhiteSpaces(line.substring(0, line.indexOf("=="))));
            Double rightSide = fillInVarriablesForDouble(takeOutAllWhiteSpaces(line.substring(line.indexOf("==") + 2, line.indexOf("{"))));

            String leftSideString = "";
            if (leftSide == null) {
                leftSideString = fillInVarriablesForString(line.substring(0, line.indexOf("==")));
            } else {
                leftSideString = "" + leftSide;
            }
            
            String rightSideString = "";
            if (rightSide == null) {
                rightSideString = fillInVarriablesForString(line.substring(line.indexOf("==") + 2, line.indexOf("{")));
            } else {
                rightSideString = "" + rightSide;
            }

            if (!leftSideString.equals(rightSideString)) {
                exitBlocksTo = amountOfParenthesis - 1;
            }
        } else if (line.indexOf("=") != -1) {
            int indexOfEqual = line.indexOf("=");
            setVar(line.substring(0,indexOfEqual), line.substring(indexOfEqual + 1)); 
        } else if (line.indexOf("loop") != -1) {
            lineNumberOfLoop.add(currentProgramLine);
            amountOfParenthesisOfLoop.add(amountOfParenthesis);
        } else if (line.indexOf("(") != -1 && line.indexOf(")") > line.indexOf("(")) {
            callFunction(line);
        } else if (line.indexOf("stop") != -1) {
            int numberToStop = toIntFromString(takeOutAllWhiteSpaces(line.substring(4)));
            exitBlocksTo = amountOfParenthesis - numberToStop;
        } else if (line.indexOf("}") != -1) {
            
            for (int i = 0; i < amountOfParenthesisOfLoop.size(); i++) {
                if (amountOfParenthesisOfLoop.get(i) == amountOfParenthesis + 1) {
                    currentProgramLine = lineNumberOfLoop.get(i) - 1;
                    break;
                }
            }
        } else {
            System.out.println("ERROR: I dont know what line: |" + line + "| means");
        }
    }
    
    public static boolean isItALine(String line) {
        return line.length() != 0;
    }
    
    public static String takeOutWhiteSpaces(String line) {
        line = line.trim();
        boolean isLastCharASpace = false;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                if (isLastCharASpace) {
                    line = line.substring(0,i) + line.substring(i + 1);
                    i--;
                } else {
                    isLastCharASpace = true;
                }
            } else {
                isLastCharASpace = false;
            }
        }
        
        return line;
    }
    public static String takeOutAllWhiteSpaces(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                line = line.substring(0, i) + line.substring(i + 1);
                i--;
            }
        }
        
        return line;
    }
    
    public static void setStringVar(String name, String value) {
        for (int i = 0; i < varStringNames.size(); i++) {
            if (name.equals(varStringNames.get(i))) {
                varString.set(i, value);
                return;
            }
        }
        
        varStringNames.add(name);
        varString.add(value);
    }
    public static void setNumberVar(String name, Double value) {
        for (int i = 0; i < varNumberNames.size(); i++) {
            if (name.equals(varNumberNames.get(i))) {
                varNumber.set(i, value);
                return;
            }
        }
        
        varNumberNames.add(name);
        varNumber.add(value);
    }
    
    public static String getStringValue(String varriable) {
        for (int i = 0; i < varStringNames.size(); i++) {
            if (varriable.equals(varStringNames.get(i))) {
                return varString.get(i);
            }
        }
        return null;
    }
    
    public static Double getNumberValue(String varriable) {
        for (int i = 0; i < varNumberNames.size(); i++) {
            if (varriable.equals(varNumberNames.get(i))) {
                return varNumber.get(i);
            }
        }
        return null;
    }
    
    public static String fillInVarriablesForString(String line) {
        
        String r = "";
        
        line = line + "+ \" \"";
        
        int index = 0;
        while (line.indexOf("+", index) != -1) {
            if (line.charAt(index) == '\"') {
                index++;
                int endQuotes = line.indexOf("\"", index);
                
                r = r + line.substring(index,endQuotes);
                index = line.indexOf("+", index);
            } else if (line.charAt(index) == ' ' || line.charAt(index) == '+') {
                index++;
            }else {
                int nextIndex = line.indexOf("+", index + 1);
                if (nextIndex == -1) {
                    nextIndex = line.length();
                }
                
                String varriableName = takeOutWhiteSpaces(line.substring(index, nextIndex));
                
                String value = getStringValue(varriableName);
                if (value == null) {
                    Double dValue = getNumberValue(varriableName);
                    if (dValue != null) {
                        r = r + dValue;
                    }
                } else {
                    r = r + value;
                }
                
                index = line.indexOf("+", index + 1);
            }
            
            
        }
        
        return r;
    }
    
    public static int toIntFromString(String numb) {
        int result = Integer.parseInt(numb);
        return result;
    }
    public static Double toDoubleFromString(String numb) {
        return Double.parseDouble(numb);
    }
    public static boolean isDigit(char d) {
        char[] digits = {'0','1','2','3','4','5','6','7','8','9'};
        for (char m: digits) {
            if (m == d) {
                return true;
            }
        }
        
        return false;
    }
    public static int indexOfNextOp(String line, int index) {
        int shortestIndex = line.length();
        for (int i = 0; i < OPERANDS.length; i++) {
            int indexOfOp = line.indexOf(OPERANDS[i], index);
            if (indexOfOp != -1 && indexOfOp < shortestIndex) {
                shortestIndex = indexOfOp;
            }
        }
        
        return shortestIndex;
    }
    public static Double fillInVarriablesForDouble(String line) {
        Double r = new Double(0);
        
        line = takeOutAllWhiteSpaces(line) + "+0";
        
        char lastOp = '+';
        int index = 0;
        while (indexOfNextOp(line, index) < line.length()) {
            if (isDigit(line.charAt(index))) {
                int indexOfNextOp = indexOfNextOp(line, index);
                
                Double value = toDoubleFromString(line.substring(index,indexOfNextOp));
                
                switch (lastOp) {
                case '+':
                    r+=value;
                    break;
                case '-':
                    r-=value;
                    break;
                case '*':
                    r*=value;
                    break;
                case '/':
                    r/=value;
                    break;
                case '^':
                    r = Math.pow(r,value);
                    break;
                case '%':
                    r%=value;
                    break;
                default:
                    System.out.println("ERROR was op is " + lastOp);
                }
                
                if (indexOfNextOp != line.length()) {
                    lastOp = line.charAt(indexOfNextOp);
                }
                
                index = indexOfNextOp + 1;
            }else {
                int nextIndex = indexOfNextOp(line, index);
                
                String varriableName = line.substring(index, nextIndex);
                
                Double value = getNumberValue(varriableName);
                if (value == null) {
                    //System.out.println("ERROR: couldnt get var name for Numbers: |" + varriableName + '|');
                    //scanner.nextLine();
                    return null;
                } else {
                    switch (lastOp) {
                    case '+':
                        r+=value;
                        break;
                    case '-':
                        r-=value;
                        break;
                    case '*':
                        r*=value;
                        break;
                    case '/':
                        r/=value;
                        break;
                    case '^':
                        r = Math.pow(r,value);
                        break;
                    case '%':
                        r%=value;
                        break;
                    default:
                        System.out.println("ERROR was op is " + lastOp);
                    }
                }
                
                if (nextIndex != line.length()) {
                    lastOp = line.charAt(nextIndex);
                }
                
                index = nextIndex + 1;
            }
        }
        
        return r;
    }
    
    public static void setVar(String before, String after) {
        before = takeOutWhiteSpaces(before);
        after = takeOutWhiteSpaces(after);
        
        String type = "";
        String name = "";
        if (before.indexOf(" ") != -1) {
            type = before.substring(0, before.indexOf(" "));
            name = before.substring(before.indexOf(" ") + 1);
        } else {
            name = before;
            if (getStringValue(name) != null) {
                type = "text";
            } else if (getNumberValue(name) != null) {
                type = "number";
            } 
        }
        
        switch (type) {
        case "text":
            String value = fillInVarriablesForString(after);
            setStringVar(name, value);
            break;
        case "number":
            Double dValue = fillInVarriablesForDouble(after);
            setNumberVar(name, dValue);
            break;
        default:
            System.out.println(type + " is not a valid type");
        }
        
    }
    
    
    public static void callFunction(String line) {
        String name = line.substring(0, line.indexOf("("));
        String param = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
        
        switch (name) {
        case "print":
            String toPrint = fillInVarriablesForString(param);
            System.out.println(toPrint);
            break;
        case "scan":
            String type = takeOutAllWhiteSpaces(line.substring(line.indexOf("(") + 1, line.indexOf(",")));
            String varName = takeOutAllWhiteSpaces(line.substring(line.indexOf(",") + 1, line.indexOf(")")));
            
            switch (type) {
            case "number":
                setNumberVar(varName, scanner.nextDouble());
                break;
            case "text":
                setStringVar(varName, scanner.nextLine());
                break;
            default:
                System.out.println("ERROR: " + type + " is not a real type on calling " + name + " on line number " + (currentProgramLine + 1));
            }
            break;
        default:
            System.out.println("ERROR: Dont know function |" + name + "|");
        }
    }
}