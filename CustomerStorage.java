import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;
    public String regularEmail = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public String prefixSeven = "^([+]7|7)";
    public String staples = "([-]|[\\s]|[(]|[\\s][(])?";
    public String parenthesis = "([)]|[\\s]|[)][\\s]|[-])?";
    public String numThree = "[0-9]{3}";
    public String numTwo = "[0-9]{2}";
    public String separate = "([-]|[\\s])?";
    public String regularPhone = prefixSeven + staples + numThree + parenthesis +
            numThree + separate + numTwo + separate + numTwo;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;
        String[] components = data.split("\\s+");
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        if (components.length > 4) throw new IllegalArgumentException();
        if (!components[2].matches(regularEmail)) throw new IllegalArgumentException();
        if (!components[3].matches(regularPhone)) throw new IllegalArgumentException();
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}