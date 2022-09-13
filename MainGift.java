import java.util.Scanner;
import java.util.*;
public class MainGift extends Account{
	private static HashMap<Integer,String> account=new HashMap<Integer,String>();
    HashMap<Integer,HashMap<Integer,GiftBalance>> accountGift=new HashMap<Integer,HashMap<Integer,GiftBalance>>();
	HashMap<Integer,HashMap<Integer,Balance>> accountBalance=new HashMap<Integer,HashMap<Integer,Balance>>();
    HashMap<Integer,ArrayList<Transaction>> giftTransaction=new HashMap<Integer,ArrayList<Transaction>>();
	static Scanner input=new Scanner(System.in);
	public static void main(String[] args){
		MainGift mg=new MainGift();
           mg.uiPage();
   
	}



     void uiPage(){
     System.out.println("1) New User\n 2) LogIn \n 3) Exit");
     int choose=input.nextInt();
     if(choose==1){
            if(newAccout())
            	System.out.println("Account Created  !");
            else
            	System.out.println("User Already Exist Enter Different User ID");
     
       uiPage();

     }


     else if(choose==2){
            loginPage();
            uiPage();  
     }
     else{
     	System.out.println("Thanking You  !");
     }

  }


 boolean newAccout(){
 	input.nextLine();
 	System.out.println("Enter your Name");
 	String name=input.nextLine();
 	System.out.println("Enter new User ID");
     int passcode=input.nextInt();
 	if(!account.containsKey(passcode)){
 	account.put(passcode,name);
 	HashMap<Integer,Balance> bankBalance=new HashMap<Integer,Balance>();
 	accountBalance.put(passcode,bankBalance);
 	HashMap<Integer,GiftBalance> giftMap=new HashMap<Integer,GiftBalance>();
    accountGift.put(passcode,giftMap);
    ArrayList<Transaction> transaction=new ArrayList<Transaction>();
    giftTransaction.put(passcode,transaction);
 	return true;
 }
 return false;

 }

 
 void loginPage(){
 	input.nextLine();
System.out.println("Enter your User Name");
String name=input.nextLine();
System.out.println("Enter Your Password");
int pass=input.nextInt();

if(account.containsKey(pass)){
    if(account.get(pass).equals(name)){
    	System.out.println("_______________________\nWelcome "+ name+"\n ------------------------");
    	mainPage(name,pass);
    }
    else{
    	System.out.println(" Incorrect ID   !");
    }
 }
 else{
    System.out.println("User Name incorrect   !");
 }
}

void mainPage(String name, int passcode){
	Account account=new Account();
	Gift gift=new Gift();
	System.out.println("1. Account Summary \n 2. Create GiftCard \n 3. TopUp Gift Card \n  4. Close GiftCrad  \n  5. PurchesItem  \n 6. Bock or UnBlock Card \n 7. deposit\n 8. LogOut");
    

int ch=input.nextInt();
    if(ch==1){
    	account.summary(passcode,accountBalance);
          mainPage(name,passcode);
    }
    else if(ch==2){
    	if(account.createCard(passcode,accountGift,accountBalance)){
    		System.out.println("Card created");

    	}
    	else{
    		System.out.println("Card not Created !   *Please check the ID Number or Amount*\n");
    	}
    	mainPage(name,passcode);
    }
    else if(ch==3){
      System.out.println(account.topUp(passcode,accountGift,accountBalance));
      mainPage(name,passcode);  
    }	
      
    else if(ch==4){
        System.out.println(gift.closeGiftCard(passcode,accountGift,accountBalance));
      mainPage(name,passcode);  
    
    }
    else if(ch==5){
    	System.out.println(gift.purcheItem(passcode,accountGift,giftTransaction));
        mainPage(name,passcode);

    }
    	else if(ch==6){

    		System.out.println(gift.block_Unblock(passcode,accountGift));
            mainPage(name,passcode);

        }
    	else if(ch==7)
    		if(account.depasit(passcode,accountBalance)){
                   System.out.println("Amount credited  ! ");
                 mainPage(name,passcode);
    		}
           else{
           	   System.out.println("Enter Correct  Custemer ID   ! \n");
           	mainPage(name,passcode);
           }
    	else{
            System.out.println("Surver busy   !   please try again  **");
    		
    	}

}
}
class Transaction{
        int txt_no;
        int cardNO;
        int amount;
        public Transaction(int cardno,int amount,int txNO){
         cardNO=cardno;
         this.amount=amount;
         txt_no=txNO;
        }
    }
