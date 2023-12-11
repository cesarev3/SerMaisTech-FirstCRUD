import java.util.ArrayList;

public class UserOperations {

    private final ArrayList<User> dataBaseUsers;

    public UserOperations() {
        this.dataBaseUsers = new ArrayList<>();
    }

    public boolean validarEmail(String inputEmail) {

        for(User item : dataBaseUsers) {
            if (item.getUserEmail().equals(inputEmail)) {
                return true;
            }
        }
        return false;
    }

    public void removeUser(String inputEmail) {

        for(User item : dataBaseUsers) {
            if (item.getUserEmail().equals(inputEmail)) {
                this.dataBaseUsers.remove(item);
                System.out.println(">>> usuário excluído!");
                break;
            }
        }
    }

    public void saveUser(User usuario) {
        dataBaseUsers.add(usuario);
        System.out.println(">>> usuário cadastrado!");
    }

    public ArrayList<User> getDataBaseUsers() {
        return this.dataBaseUsers;
    }
}
