package java.classes;

import java.time.DateTimeException;
import java.time.LocalDate;

public class User {

    private final String userName;

    private final String userEmail;

    private final LocalDate userBirthDate;

    public User(String userName, String userEmail, LocalDate userBirthDate) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userBirthDate = userBirthDate;
    }

    public boolean checkMonth (int inputMonth) {
        if (inputMonth >= 1 && inputMonth <= 12) return true;
        else {
            throw new DateTimeException("data inválida");
        }
    }

    public boolean checkYear (int inputYear) {
        LocalDate currentYear = LocalDate.now();
        return (inputYear >= 1900 && inputYear <= currentYear.getYear());
    }

    public boolean checkDay(int inputDay, int inputMonth, int inputYear) {
        int daysInMonth;
        boolean dayIsOk = false;
        switch (inputMonth) {
            case 1: case 3: case 5:
            case 7: case 8: case 10:
            case 12:
                daysInMonth = 31;
                if (inputDay >= 1 && inputDay <= daysInMonth) dayIsOk = true;
                break;

            case 4: case 6:
            case 9: case 11:
                daysInMonth = 30;
                if (inputDay >= 1 && inputDay <= daysInMonth) dayIsOk = true;
                break;

            case 2:
                if ((inputYear % 4 == 0 && inputYear % 100 != 0) ||
                        inputYear % 400 == 0) {
                    daysInMonth = 29;
                }
                else daysInMonth = 28;

                if (inputDay >= 1 && inputDay <= daysInMonth) dayIsOk = true;
                break;
        }
        return dayIsOk;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public LocalDate getUserBirthDate() {
        return this.userBirthDate;
    }



}
