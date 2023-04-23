package lab4;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.io.*;
import java.util.*;
import org.junit.*;
// ...
import org.junit.rules.*;

import javax.naming.InsufficientResourcesException;
// ...

public class HandsOnExperience {
	class InsufficientFundsException extends RuntimeException {
	      public InsufficientFundsException(String message) {
	         super(message);
	      }

	      private static final long serialVersionUID = 1L;
	   }
	   class Account {
	      int balance;
	      String name;

	      Account(String name) {
	         this.name = name;
	      }

	      void deposit(int dollars) {
	         balance += dollars;
	      }

	      void withdraw(int dollars) {
	         if (balance < dollars) {
	            throw new InsufficientFundsException("balance only " + balance);
	         }
	         balance -= dollars;
	      }

	      public String getName() {
	         return name;
	      }

	      public int getBalance() {
	         return balance;
	      }

	      public boolean hasPositiveBalance() {
	         return balance > 0;
	      }
	   }

	   class Customer {
	      List<Account> accounts = new ArrayList<>();

	      void add(Account account) {
	         accounts.add(account);
	      }

	      Iterator<Account> getAccounts() {
	         return accounts.iterator();
	      }
	   }
	   private Account account;
	   //TO DO

	   // You need to create test cases in Here

		@Before
		public void createAccount() {
			account = new Account("CompanyName");
		}

		@Test
		public void hasPositiveBalance() {
			account.deposit(50);
			assertTrue(account.hasPositiveBalance());
		}

		@Test
		public void depositIncreasesBalance() {
			int initialBalance = account.getBalance();
			account.deposit(100);
			assertTrue(account.getBalance() > initialBalance);
		}

		@Test
		public void testWithWorthlessAssertionComment() {
			account.deposit(50);
			assertThat("account balance is 100", account.getBalance(), equalTo(50));
		}

		@Test
		public void proofThatCodeIsBroken() {
			Customer customer = new Customer();
			customer.add(account);
			account.deposit(100);
			account.withdraw(-10);
			assertEquals(90, customer.getAccounts().next().balance);
		}

		@Test
		public void throwsWhenWithdrawingTooMuch() {
			account.withdraw(100);
		}


}
