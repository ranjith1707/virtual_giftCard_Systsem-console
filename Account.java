import java.util.*;
class Account{
	static int cardnumber=1;
	Scanner input=new Scanner(System.in);
	

	boolean depasit(int userID,HashMap<Integer,HashMap<Integer,Balance>> accountBalance){
		System.out.println("Enter id");
		int custumId=input.nextInt();
		System.out.println("Enter Amount");
		int balance=input.nextInt();
		if(custumId==userID){
		Balance amount=new Balance(custumId,balance);
		accountBalance.get(userID).put(custumId,amount);

	  return true;
      }
       return false;
	}


	boolean summary(int given,HashMap<Integer,HashMap<Integer,Balance>> accountBalance){
         if(accountBalance.get(given).isEmpty())
			System.out.println("\n balance Not Found! \n");
		else{
		System.out.println("CustomerID      Balance");

		System.out.println("   "+given+"            "+accountBalance.get(given).get(given).getBalance());
	}
      return true;
	}


	boolean createCard(int given,HashMap<Integer,HashMap<Integer,GiftBalance>> accountGift,HashMap<Integer,HashMap<Integer,Balance>>accountBalance){
       System.out.println("Enter your ID Number");
        int id=input.nextInt();
        System.out.println("Enter PIN ");
        int pin=input.nextInt();
        System.out.println("Enter the amount fo Create gift card");
        int amount=input.nextInt();
        int cardNo=cardnumber;
        cardnumber++;
        if(id==given && !accountBalance.get(given).isEmpty()){
        	int bal=accountBalance.get(given).get(id).getBalance();
        	if(bal>amount){
                 GiftBalance gb=new GiftBalance(id,pin,amount,cardNo);
                 accountGift.get(given).put(cardNo,gb);
                 accountBalance.get(given).get(given).setBalance(bal-amount);	

              System.out.println("CardNo\tCustId \tPIN\tGiftCardBalance \tStatus");
                for(Map.Entry <Integer,GiftBalance> gift:accountGift.get(given).entrySet()){
                	System.out.println("\n"+gift.getKey()+"\t"+ gift.getValue().custId+"\t"+gift.getValue().getPin()+"\t \t"+gift.getValue().getBalance()+"\t \t "+gift.getValue().status+"\n");
                }
                System.out.println("CustomerID      Balance");

		System.out.println("\n   "+given+"            "+accountBalance.get(given).get(given).getBalance());


                 return true;	
        	}
        
    }
    return false;
	}


    String topUp(int given,HashMap<Integer,HashMap<Integer,GiftBalance>> accountGift,HashMap<Integer,HashMap<Integer,Balance>>accountBalance){

      System.out.println("Enter the Card Number");
      int cardno=input.nextInt();
      System.out.println("Enter Amount ");
      int amount=input.nextInt();
      if(accountGift.get(given).containsKey(cardno)&& !accountBalance.get(given).isEmpty()){
      	if(accountBalance.get(given).get(given).getBalance()>=amount ){
           int bal=accountBalance.get(given).get(given).getBalance();
           int cardbalance=accountGift.get(given).get(cardno).getBalance();
            accountGift.get(given).get(cardno).setBalance(cardbalance+amount);
                 accountBalance.get(given).get(given).setBalance(bal-amount);             
            


        System.out.println("CardNo\tCustId \tPIN\tGiftCardBalance \tStatus");
                for(Map.Entry <Integer,GiftBalance> gift:accountGift.get(given).entrySet()){
                	System.out.println("\n"+gift.getKey()+"\t"+ gift.getValue().custId+"\t"+gift.getValue().getPin()+"\t \t"+gift.getValue().getBalance()+"\t \t "+gift.getValue().status+"\n");
                }
                System.out.println("CustomerID      Balance");

		System.out.println("\n   "+given+"            "+accountBalance.get(given).get(given).getBalance());
		return ("TopUp Successful");

      	}
      	else{
      		return ("Amount Not Sufficient");
      	}
      }
  
  	if(!accountBalance.get(given).isEmpty())
  		return ("Amount not Sufficient");
  	return ("Card No incorrect  !");
    }

}
class GiftBalance{
	private int cardNo;
	int custId;
	private int pin;
	private int balance;
	String status="Active";
	GiftBalance(int costumId,int pin, int balance,int cardNo){
		custId=costumId;
	    this.pin=pin;
	    this.balance=balance;
	    this.cardNo=cardNo;
	}
	public int getBalance(){
		return balance;
	}
	public void setBalance(int balance){
		this.balance=balance;
	}
	public void setStatus(String status){
     this.status=status;
	}
	public int getPin(){
		return pin;
	}
}


class Balance{
	private int custId;
    private int balance;
	Balance(int id,int rube){
      custId=id;
      balance=rube;
	}
	public int getBalance(){
		return balance;
	}
	public void setBalance(int balance){
       this.balance=balance;
	}
}