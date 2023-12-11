package java.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TestUser {

    public static void main(String[] args) {
        // criando um usuário, esse usuario será salvo no banco de dados
        User user = new User("Usuário", "usuario@email.com",
                LocalDate.of(1998, 7, 22));

        // criando o objeto userOperations e salvando o usuário acima
        UserOperations userOperations = new UserOperations();
        userOperations.saveUser(user);

        // criando o objeto viewScreens
        ViewScreens viewScreens = new ViewScreens();

        // criando o objeto Scanner
        Scanner scanner = new Scanner(System.in);

        // imprimindo tela de menu e recebendo opção
        viewScreens.printMainScreen();
        int options = getAndCheckMenuOption(
                scanner, EntryMessages.GETOPTIONS, viewScreens
        );

        // inicializando variáveis para bloco While-Switch
        String getEmail;
        String getName;
        int[] getBirthdayDate;

        // iniciando laço While-Switch
        while (true) {
            switch (options) {
                case 1:
                    viewScreens.printCreateScreen();

                    // validando e recebendo e-mail
                    getEmail = checkEmail(scanner, userOperations);

                    // recebendo os dados do usuário
                    getName = getString(scanner, EntryMessages.GETNAMES);
                    getBirthdayDate = getAndCheckBirthdayDate(
                            scanner, EntryMessages.GETBIRTHDATE, user
                    );

                    // carregando parametros do objeto
                    User inputUser = new User(
                            getName, getEmail, formatLocalDate(getBirthdayDate)
                    );

                    // carregando novo usuário no DB
                    userOperations.saveUser(inputUser);

                    // voltando para tela principal
                    viewScreens.printMainScreen();
                    options = getAndCheckMenuOption(
                            scanner, EntryMessages.GETOPTIONS, viewScreens
                    );

                    scanner.reset();
                    break;

                case 2:
                    viewScreens.printReadScreen();

                    // criando objeto de usuários salvos no DB e imprimindo
                    ArrayList<User> dataBaseUsers;
                    dataBaseUsers = userOperations.getDataBaseUsers();
                    printAllUsers(dataBaseUsers);

                    // voltando para tela principal
                    viewScreens.printMainScreen();
                    options = getAndCheckMenuOption(
                            scanner, EntryMessages.GETOPTIONS, viewScreens
                    );

                    scanner.reset();
                    break;

                case 3:
                    viewScreens.printUpdateScreen();

                    // localizando usuário no banco de dados e retorna e-mail
                    getEmail = findUser(scanner, userOperations);

                    // criando objeto de usuários salvos no DB
                    ArrayList<User> dataBaseUpdateUser;
                    dataBaseUpdateUser = userOperations.getDataBaseUsers();

                    // imprimindo dados do usuário
                    printOneUser(dataBaseUpdateUser, getEmail);

                    // recebendo novos dados do usuário
                    System.out.println("\nAltere somente Nome e Data de Nas" +
                            "cimento");
                    getName = getString(scanner, EntryMessages.GETNAMES);
                    getBirthdayDate = getAndCheckBirthdayDate(
                            scanner, EntryMessages.GETBIRTHDATE, user
                    );

                    // carregando parametros do objeto
                    User updateUser = new User(
                            getName, getEmail, formatLocalDate(getBirthdayDate)
                    );

                    // verifica se usuário deve ser atualizado
                    if (continueProcedure(scanner, EntryMessages.CHECKUPDATE)) {
                        userOperations.removeUser(getEmail);
                        userOperations.saveUser(updateUser);
                    }

                    // voltando para tela principal
                    viewScreens.printMainScreen();
                    options = getAndCheckMenuOption(
                            scanner, EntryMessages.GETOPTIONS, viewScreens
                    );

                    scanner.reset();
                    break;

                case 4:
                    viewScreens.printDeleteScreen();

                    // localizando usuário no banco de dados e retorna e-mail
                    getEmail = findUser(scanner, userOperations);

                    // criando objeto de usuários salvos no DB
                    ArrayList<User> dataBaseFindUser;
                    dataBaseFindUser = userOperations.getDataBaseUsers();

                    // imprimindo dados do usuário
                    printOneUser(dataBaseFindUser, getEmail);

                    // verifica se usuário deve ser excluído
                    if (continueProcedure(scanner, EntryMessages.CHECKUPDATE)) {
                        userOperations.removeUser(getEmail);
                    }

                    // voltando para tela principal
                    viewScreens.printMainScreen();
                    options = getAndCheckMenuOption(
                            scanner, EntryMessages.GETOPTIONS, viewScreens);

                    scanner.reset();
                    break;

                case 5:
                    // fechando Scanner e saindo do programa
                    scanner.close();
                    System.exit(0);

                default:
                    break;
            }
        }
    }

    // métodos
    private static String getString(Scanner scanner, EntryMessages message) {
        System.out.print(message.getUserMessage());
        return scanner.nextLine();
    }

    public static int[] splitBirthdayDate(String inputBirthdayDate) {
        int[] outputBirthdayDate = new int[3];
        String[] inputSplitBirthdayDate = inputBirthdayDate.split("/");

        for (int i = 0; i < inputSplitBirthdayDate.length; i++) {
            outputBirthdayDate[i] = Integer.parseInt(inputSplitBirthdayDate[i]);
        }

        return outputBirthdayDate;
    }

    private static boolean isBirthdayDateOk(User user,
                                            int[] inputBirthdayDate) {

        boolean isYearOk = user.checkYear(inputBirthdayDate[2]);
        boolean isMonthOk = user.checkMonth(inputBirthdayDate[1]);
        boolean isDayOk = user.checkDay(inputBirthdayDate[0],
                inputBirthdayDate[1],
                inputBirthdayDate[2]);

        return isYearOk && isMonthOk && isDayOk;
    }

    public static int[] getAndCheckBirthdayDate(Scanner scanner,
                                                EntryMessages message,
                                                User user) {
        int[] inputBirthdayDate;

            while (true) {
                try {
                    String inputDate = getString(scanner, message);
                    inputBirthdayDate = splitBirthdayDate(inputDate);
                    boolean isBirthdayDateOk = isBirthdayDateOk(user, inputBirthdayDate);

                    if (isBirthdayDateOk) break;
                    else System.out.println(">>> data inválida");

                } catch(RuntimeException e){
                 System.out.println(">>> data inválida");
                }
            }
        return inputBirthdayDate;
    }

    public static int getAndCheckMenuOption(Scanner scanner,
                                            EntryMessages message,
                                            ViewScreens viewScreens) {
        String inputString;

        while (true) {
            try {
                inputString = getString(scanner, message);
                boolean isMenuOptionOk = viewScreens.checkMenuOption(inputString);

                if (isMenuOptionOk) break;
                else System.out.println(">>> opção inválida");

            } catch(RuntimeException e){
                System.out.println(">>> opção inválida");
            }
        }
        return Integer.parseInt(inputString);
    }

    private static void printOneUser(ArrayList<User> dataBaseFindUser,
                                     String inputEmail) {
        for(User item : dataBaseFindUser) {

            if (item.getUserEmail().equals(inputEmail)) {
                System.out.println("Nome: " + item.getUserName());
                System.out.println("e-mail: " + item.getUserEmail());
                System.out.println("Nascimento: " + item.getUserBirthDate());
            }
        }
    }

    private static void printAllUsers(ArrayList<User> usuariosSalvos) {
        for(User item : usuariosSalvos) {
            System.out.println("\nNome: " + item.getUserName());
            System.out.println("e-mail: " + item.getUserEmail());
            System.out.println("Nascimento: " + item.getUserBirthDate());
        }
    }

    private static String checkEmail(Scanner scanner,
                                     UserOperations userOperations) {
        String inputEmail = "";
        boolean isRepeated = true;

        while (isRepeated) {
            inputEmail = getString(scanner, EntryMessages.GETEMAILS);
            isRepeated = userOperations.validarEmail(inputEmail);

            if (isRepeated) {
                System.out.println(">>> Usuário duplicado. Dados não serão" +
                        " salvos\n");
            }
        }
        return inputEmail;
    }

    private static String findUser(Scanner scanner,
                                   UserOperations userOperations) {
        String inputEmail = "";
        boolean hasFound = false;

        while (!hasFound) {
            inputEmail = getString(scanner, EntryMessages.GETEMAILS);
            hasFound = userOperations.validarEmail(inputEmail);

            if (!hasFound) {
                System.out.println(">>> Usuário não encontrado, digite " +
                        "novamente\n");
            }
        }
        return inputEmail;
    }

    private static LocalDate formatLocalDate(int[] inputBirthdayDate) {
        return LocalDate.of(
                inputBirthdayDate[2],
                inputBirthdayDate[1],
                inputBirthdayDate[0]);
    }

    private static boolean continueProcedure(Scanner scanner,
                                             EntryMessages message) {
        String updateUser = getString(scanner, message);
        return updateUser.equals("S") || updateUser.equals("s");
    }
}
