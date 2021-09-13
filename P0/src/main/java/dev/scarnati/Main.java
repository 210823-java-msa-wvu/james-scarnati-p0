package dev.scarnati;

import dev.scarnati.service.exceptions.InvalidSelectionException;
import dev.scarnati.veiws.LoginMenu;

import java.sql.SQLException;

public class Main {

        public static void main(String[] args) throws InvalidSelectionException, SQLException {
            LoginMenu.display();
        }
    }

