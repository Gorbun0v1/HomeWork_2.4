public class Main {
    public static void main(String[] args) {
        String login = "java_skypro_go";

        String password = "SecurePassword";
        String confirmPassword = "SecurePassword";
        try {
            checkDetails(login, password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("Регистрация закончена");
        }
    }

    public static void checkDetails(String login, String password, String confirmPassword) {
        if (login.length() <= 20) {
            throw new WrongLoginException("Логин слишком короткий");
        }
        if (password.length() <= 20) {
            throw new WrongPasswordException("Пароль слишком короткий");
        }
        checkInvalidSymbols(login, new WrongLoginException("Невалидные символы"));
        checkInvalidSymbols(password, new WrongPasswordException("Невалидный пароль"));
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Не совпадают пароли");
        }
    }

    public static void checkInvalidSymbols(String line, RuntimeException ex) {
        String invalidSymbols = "абвгдежзийклмнопрстуфхцчшщьыъэюя!@#$%^&*()-=_+?/><.,~`";
        char[] letters = line.toCharArray();
        for (char symbol : letters) {
            if (invalidSymbols.contains(String.valueOf(symbol))) {
                throw ex;
            }
        }
    }
}

class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) {
        super(message);
    }
}

class WrongLoginException extends RuntimeException {
    public WrongLoginException(String message) {
        super(message);
    }
}
