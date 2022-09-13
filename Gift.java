import java.util.*;
class Gift{
	    
     static Scanner input=new Scanner(System.in);
     public static int sNo=1;

     String closeGiftCard(int given,HashMap<Integer,HashMap<Integer,GiftBalance>> accountGift,HashMap<Integer,HashMap<Integer,Balance>>accountBalance){
       System.out.println("Enter the CardNumber");
       int cardno=input.nextInt();
       System.out.println("Enter the PIN no Of the Card");
       int pin=input.nextInt();
       if(accountGift.get(given).containsKey(cardno)){
            if(accountGift.get(given).get(cardno).getPin()==pin){
               int amount=accountGift.get(given).get(cardno).getBalance();
               int mainBalance=accountBalance.get(given).get(given).getBalance();
                  accountBalance.get(given).get(given).setBalance(mainBalance+amount);
                  accountGift.get(given).remove(cardno);
                  	 
              System.out.println("CardNo\tCustId \tPIN\tGiftCardBalance \tStatus");
              if(accountGift.get(given).isEmpty())
              	   System.out.println("\tNo cards found !");
                for(Map.Entry <Integer,GiftBalance> gift:accountGift.get(given).entrySet()){
                	System.out.println("\n"+gift.getKey()+"\t"+ gift.getValue().custId+"\t"+gift.getValue().getPin()+"\t \t"+gift.getValue().getBalance()+"\t \t "+gift.getValue().status+"\n");
                }
                System.out.println("CustomerID      Balance");

		System.out.println("\n   "+given+"            "+accountBalance.get(given).get(given).getBalance());

		return ("Card Closed \n");
            }
            else
            	return ("PIN  missMatched");

       }
       return ("CardNumber incorrect");

     }

 

		String purcheItem(int given,HashMap<Integer,HashMap<Integer,GiftBalance>> accountGift,HashMap<Integer,ArrayList<Transaction>>giftTransaction){
			System.out.println("Enter the CARD Number");
			int cardno=input.nextInt();
			System.out.println("Enter the PIN ");
			int pin=input.nextInt();
			System.out.println("Enter the amount of Purches");
			int amount=input.nextInt();

			if(accountGift.get(given).containsKey(cardno)){
				if(accountGift.get(given).get(cardno).getPin()==pin){
					if(!accountGift.get(given).get(cardno).status.equals("Active")){
                      return ("Card Blocked  try  to use another Card");
					}
                      if(accountGift.get(given).get(cardno).getBalance()>=amount){
                           int balance=accountGift.get(given).get(cardno).getBalance();
                           accountGift.get(given).get(cardno).setBalance(balance-amount);
                           Transaction history=new Transaction(cardno,amount,sNo);
                           giftTransaction.get(given).add(history);
                           sNo++;
                           System.out.println("TXT_no\t   Card_NO\t Amount");
            
                           for(int i=0; i<giftTransaction.get(given).size();i++){
                           	System.out.println(+giftTransaction.get(given).get(i).txt_no+"\t    "+giftTransaction.get(given).get(i).cardNO+"\t\t "+giftTransaction.get(given).get(i).amount);
                           }

                           return("Transaction successful");
                      }
                      else
                      	return ("Amount inSufficient");
				}
				else{
					return ("PIN not Matched");
				}
			}
			return ("Card no InCorrect");
		}



		String block_Unblock(int given,HashMap<Integer,HashMap<Integer,GiftBalance>> accountGift){
			System.out.println("     *Select One   *\n1. Block\n2. UnBlock ");
			int option=input.nextInt();
			String status;
			if(option==1){
             status="Blocked";
			}
			else if(option==2){
             status="Active";
			}
			else
              return ("Enter correct option");

             System.out.println("Enter the Card number");
             int cardNo=input.nextInt();
             System.out.println("Enter the PIN");
             int pin=input.nextInt();
          if(accountGift.get(given).containsKey(cardNo)){
          	  if(accountGift.get(given).get(cardNo).getPin()==pin){
                   if(!accountGift.get(given).get(cardNo).status.equals(status)){
                     accountGift.get(given).get(cardNo).setStatus(status);
                     System.out.println("CardNo\tCustId \tPIN\tGiftCardBalance \tStatus");
                     for(Map.Entry <Integer,GiftBalance> gift:accountGift.get(given).entrySet()){
                	System.out.println("\n"+gift.getKey()+"\t"+ gift.getValue().custId+"\t"+gift.getValue().getPin()+"\t \t"+gift.getValue().getBalance()+"\t \t "+gift.getValue().status+"\n");
                }
                    System.out.println();
                     return (status+"ed");
                   }
                   else{
                   	return ("Card Alredy "+status);
                   }
          	  }
          	  else
          	  	return ("PIN InCorrect");
          }
          return "card not Found !";
		}
	}

	