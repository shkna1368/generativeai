package com.shabab.aiproject.bank;

import com.shabab.aiproject.flight.Flight;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



@Service
public class BankService {
   public List<BankAccount> bankAccounts=new ArrayList<>();
  public   List<Transaction> transactions=new ArrayList<>();

    public BankService() {

        seed();
    }

    public List<BankAccount> getAllBanAccount() {        return bankAccounts;}
    public List<BankAccount> findByPassport(String passport){
        return   bankAccounts.stream().filter(flight -> flight.getPassportNumber().equals(passport)).toList();

        }
    public List<BankAccount> save(JSONObject data) {


        String name= data.getString("name");
        long  balance=data.getLong("balance");
        String branchName=data.getString("branchName");
        String mobileNumber=data.getString("mobileNumber");
        String passportNumber=data.getString("passportNumber");



        BankAccount bankAccount=new BankAccount(name,balance,branchName,mobileNumber,passportNumber);

        bankAccounts.add(bankAccount);


        return bankAccounts;


    }


    public List<BankAccount> transfer(JSONObject data) {

      String from = String.valueOf(data.getLong("fromAccountNumber"));
        String to = String.valueOf(data.getLong("toAccountNumber"));
      long amount = data.getLong("amount");

      BankAccount bankAccountFrom=  bankAccounts.stream().filter(bankAccount1 -> bankAccount1.getAccountNumber().equals(from)).toList().get(0);
      BankAccount bankAccountTo=  bankAccounts.stream().filter(bankAccount1 -> bankAccount1.getAccountNumber().equals(to)).toList().get(0);

      bankAccountFrom.setBalance(bankAccountFrom.getBalance()-amount);
      bankAccountTo.setBalance(bankAccountTo.getBalance()+amount);


      Transaction transaction=new Transaction(from,to,amount);

      transactions.add(transaction);

        return bankAccounts;

    }


    private void  seed(){
        BankAccount b1=new BankAccount("Beritan Koohi",500000l,"B99100","++9876543210","V56989");
        BankAccount b2=new BankAccount("Shabab Koohi",400000l,"B9999","++9676442210","V56936");
        BankAccount b3=new BankAccount("Jim cole",350000l,"B99166","++9276549880","M56589");
        BankAccount b4=new BankAccount("Mohamad Jalil",320000l,"B66100","++9312543310","K56122");
        BankAccount b5=new BankAccount("Ferial Gol",220000l,"B89100","++93876543200","V56922");
        BankAccount b6=new BankAccount("James Siman",110000l,"B99100","++9876543216","V5696");

    bankAccounts.add(b1);
    bankAccounts.add(b2);
    bankAccounts.add(b3);
    bankAccounts.add(b4);
    bankAccounts.add(b5);
    bankAccounts.add(b6);








    }


  public   List<Transaction> getTransaction(JSONObject data){

        String accountNumber=String.valueOf(data.getString("accountNumber"));

        return transactions.stream().filter(transaction -> transaction.getFrom().equals(accountNumber) || transaction.getTo().equals(accountNumber)).toList();


    }




    public List<BankAccount> delete(JSONObject data) {
        String accountNumber=String.valueOf(data.getString("accountNumber"));
        bankAccounts.removeIf(bankAccount -> bankAccount.getAccountNumber().equals(accountNumber));

        return bankAccounts;

    }

    public List<BankAccount> search(JSONObject data) {
        String findBy=data.getString("findBy");

        if (findBy.equalsIgnoreCase("accountNumber")) {

            String accountNumber = String .valueOf(data.getString("accountNumber"));
            return bankAccounts.stream().filter(bankAccount -> bankAccount.getAccountNumber().equals(accountNumber)).toList();
        }

        else if (findBy.equalsIgnoreCase("passportNumber")) {
        String passportNumber = data.getString("passportNumber");
        return bankAccounts.stream().filter(bankAccount -> bankAccount.getPassportNumber().equals(passportNumber)).toList();
    }
        else if (findBy.equalsIgnoreCase("mobile")||findBy.equalsIgnoreCase("mobileNumber")){
            String mobileNumber = data.getString("mobileNumber");
        return bankAccounts.stream().filter(bankAccount -> bankAccount.getMobileNumber().equals(mobileNumber)).toList();
    }
return bankAccounts;

}
}
