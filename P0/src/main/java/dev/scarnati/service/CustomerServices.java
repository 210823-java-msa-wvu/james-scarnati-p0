package dev.scarnati.service;

import dev.scarnati.exceptions.UsernameDoesNotExistException;
import dev.scarnati.model.Accounts;
import dev.scarnati.model.Customer;
import dev.scarnati.repositories.AccountsRepo;
import dev.scarnati.repositories.CrudInterface;
import dev.scarnati.repositories.CustomerRepo;

import java.sql.SQLException;


//Services only used in customer menu
public class CustomerServices {
    AccountsRepo accountsRepo = new AccountsRepo();
    CustomerRepo customerRepo= new CustomerRepo();
    CrudInterface<Accounts> accountsCrudInterface = new AccountsRepo();
    CrudInterface<Customer> customerCrudInterface = new CustomerRepo();
// assigns the car to a customer by the cars id
    public boolean buyCar(Customer customer){
       return customerCrudInterface.update(customer);

    }
    // pulls the customers info from the database by their accounts username
    //used when performing an operation that need the customers id
    public Customer getCustomerByUsername(String username){
        Customer c = customerRepo.getCustomerByUsername(username);
        if(c != null) {
            return c;
        }
        return null;
    }
    //pulls account info from database based on username used to
    public Accounts getByUsername(String username) throws UsernameDoesNotExistException, SQLException {
        Accounts a = accountsRepo.getAccountByUsername(username);
        if(a != null) {
            return a;
        }
        return null;
    }
    //changes the users password in the database
    public boolean changePass(Accounts account){
       return accountsCrudInterface.update(account);

    }
}
