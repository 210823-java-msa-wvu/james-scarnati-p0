package dev.scarnati;

import dev.scarnati.exceptions.UsernameDoesNotExistException;
import dev.scarnati.veiws.LoginMenu;

import java.sql.SQLException;

public class Main {

        public static void main(String[] args) throws UsernameDoesNotExistException, SQLException {
            LoginMenu.display();
        }
    }

