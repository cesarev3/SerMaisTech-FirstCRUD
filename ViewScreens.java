public class ViewScreens {
    public boolean checkMenuOption(String inputString) {
        int inputOption = Integer.parseInt(inputString);
        int numberOfOptions = 5;
        return inputOption >= 1 && inputOption <= numberOfOptions;
    }

    public void printMainScreen() {
        System.out.println("\n|------------------------------------------------------|");
        System.out.println("|                  Opções do programa                  |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("|                 1- Cadastrar Usuário                 |");
        System.out.println("|                 2- Listar Usuários                   |");
        System.out.println("|                 3- Alterar Usuário                   |");
        System.out.println("|                 4- Excluir Usuário                   |");
        System.out.println("|                 5- sair do programa                  |");
        System.out.println("|------------------------------------------------------|");
    }

    public void printCreateScreen() {
        System.out.println("\n|------------------------------------------------------|");
        System.out.println("|             Tela de cadastro de usuários             |");
        System.out.println("|------------------------------------------------------|");
    }

    public void printReadScreen() {
        System.out.println("\n|------------------------------------------------------|");
        System.out.println("|            Tela de leitura de usuários               |");
        System.out.println("|------------------------------------------------------|");
    }

    public void printUpdateScreen() {
        System.out.println("\n|------------------------------------------------------|");
        System.out.println("|               Tela de alterar usuários               |");
        System.out.println("|------------------------------------------------------|\n");
    }

    public void printDeleteScreen() {
        System.out.println("\n|------------------------------------------------------|");
        System.out.println("|              Tela de excluir usuários                |");
        System.out.println("|------------------------------------------------------|\n");
    }
}
