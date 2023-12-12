package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


public class Main {

    public enum Fruit {
        APPLE, PEAR, ORANGE, KIWI, AVOCADO
    }
    private static void sqlStatement() {
        String sql = """
            SELECT id, firstName, lastName\s\
            FROM Employee
            WHERE departmentId = "IT" \
            ORDER BY lastName, firstName""";
        System.out.println(sql);
    }

    private static void improvedSwitch(Fruit fruit) {
        String text = switch (fruit) {
            case APPLE, PEAR -> {
                System.out.println("the given fruit was: " + fruit);
                yield "Common fruit";
            }
            case ORANGE, AVOCADO -> "Exotic fruit";
            default -> "Undefined fruit";
        };
        System.out.println(text);
    }

    public static void jsonBlock() {
        // json sample
        String text = """
              {
                "name": "John Doe",
                "age": 45,
                "address": "Doe Street, 23, Java Town"
              }
            """;
        System.out.println(text);
    }

    public static void main(String[] args) {
        jsonBlock();
        sqlStatement();
        improvedSwitch(Fruit.APPLE);
        improvedSwitch(Fruit.PEAR);
        improvedSwitch(Fruit.KIWI);
    }
}