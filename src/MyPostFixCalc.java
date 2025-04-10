/*
    Primary class focused on PostFix operations that verifies
    incoming notations and finds a result for the
    associated input.
 */

public class MyPostFixCalc {

    // Supporting method that checks if an input is an operator.
    public boolean operatorCheck(String input) {
        return input.equals("+") ||
                input.equals("-") ||
                input.equals("*") ||
                input.equals("/") ||
                input.equals("%");
    }

    // Supporting method that reviews the input format.
    public boolean formatCheck(String input) {
        // Regular expression used to identify spacing and space constraints.
        if (input.matches(".*\\s{2,}.*") || input.startsWith(" ") || input.endsWith(" ")) {
            return false;
        }
        // Each item in the notation is stored and reviewed for purpose
        String[] tempContainer = input.split(" ");

        int values = 0;
        int operators = 0;

        // Check each item and assign them as a value or an operator.
        for (String i : tempContainer) {
            // Regex check for values based on digit identifier.
            if (i.matches("\\d+")) {
                values++;
            } else if (operatorCheck(i)) {
                operators++;
            } else {
                return false;
            }
            if (operators > values - 1) {
                return false;
            }
        }
        // There should always be one greater value.
        return values - operators == 1;
    }

    // Supporting method that manages the actual math process for each value pair.
    public double calculate(String operator, double value1, double value2) {
        switch (operator) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                return value1 / value2;
            case "%":
                return value1 % value2;
            default:
                throw new IllegalArgumentException("The " + operator + " operator is not allowed.");
        }
    }

    // Primary method that combines all supporting method to process a provided notation.
    public double postFixProcessing(String notation) {
        // Formatting fail state check
        if (!formatCheck(notation)) {
            throw new IllegalArgumentException("Formatting is incorrect, check the notation!");
        }

        SimpleStack stack = new SimpleStack();
        String[] tempContainer = notation.split(" ");

        /*
            Common PostFix method, push 2 values, calculate on those
            values, push new value and next value, repeat.
         */
        for (String i : tempContainer) {
            if (operatorCheck(i)) {
                double value2 = stack.pop();
                double value1 = stack.pop();
                double calculated = calculate(i, value1, value2);
                stack.push(calculated);
            } else {
                stack.push(Double.parseDouble(i));
            }
        }
        return stack.pop();
    }
}
