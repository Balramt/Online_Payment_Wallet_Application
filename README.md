# Online_Payment_Wallet_Application

The MZ Project is an application built using Java, Spring Boot, JPA, and an Oracle Database. It provides various functionalities related to banking, including creating user accounts, depositing and withdrawing funds, performing fund transfers, viewing transaction history, and more.

createAccount(User user): Creates a new user account and returns the account number.

deposite(int accountNo, double amount): Deposits a specified amount into a user's account.

withdrawl(int accountNo, double amount): Withdraws a specified amount from a user's account.

fundTransfer(int senderAccountNo, int recieverAccountNo, double amount): Transfers funds from one user's account to another.

printTransaction(int accountNo): Retrieves a list of transactions for a given user account.

getBalance(int accountNo): Retrieves the balance of a user's account.

IsNumber(int accountNo): Validates whether a given value is a valid integer.

IsEmail(String email): Validates whether a given string is a valid email address.

IsMobileNo(Long mobileNo): Validates whether a given long value is a valid mobile number.

IsAlpha(String str): Validates whether a given string contains only alphabetic characters.

login(String emailId, String password): Checks user credentials for login.

Dependencies
This class uses the BankDao interface for database operations, which is presumably implemented elsewhere in your project.
Usage
This BankServiceImpl class serves as the backend logic for your banking application, handling user account management and transactions. To use this class effectively, you should integrate it with appropriate controllers and user interfaces in your Spring Boot application.

Please ensure that you have proper exception handling and validation mechanisms integrated into your project for enhanced security and reliability.

For further information and detailed usage instructions, refer to the rest of your project documentation.
