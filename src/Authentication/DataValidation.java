package Authentication;
public class DataValidation {
    public static boolean validUser(String input) {
        if (input.length() < 8) {
            System.out.println("[Important] Username must be at least 8 characters.");
            return false;
        } else
            return true;
    }

    public static boolean validPass(String input) {
        if (input.length() < 8) {
            System.out.println("[Important] Password must be at least 8 characters.");
            return false;
        } else
            return true;
    }

    public boolean validLast(String input) {
        return true;
    }

    public boolean validFirst(String input) {
        return true;
    }
}
