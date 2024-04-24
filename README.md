# NJKA Bank Application Project :bank:
### Team Members :busts_in_silhouette: :
**N**adiyah Ahmad, **J**alal Abdul-Yahaya, **K**arim Zaggoti, & **A**nders Pesti
### Project Description:
- Introducing NJKA Bank, your go-to command-line banking app for effortless financial management. After downloading our repo, and simply launching with `./script.sh`, financial management is at your fingertips. With our intuitive interface, easily create accounts, monitor your account balances, transfer funds between accconts, pay loans, and track expenses anytime, anywhere. Simplify your banking experience with NJKA Bank and take control of your finances with just a few clicks!
  
## Iteration One
*Wednesday March 27th, 2024*

**We were able to implement the following features during this iteration:**
- `deposit` Users can deposit money into an account 
- `withdraw` Users can withdraw money from an account

**We intend to implement the following features by the next iteration(s):**
- Users is able to create multiple accounts whose information is stored/saved
- User should not be able to withdraw and transfer money that they do not have
- User is able to access additional information about their account; specifically what type of account it is and its routing number 

**The following is/are things that we have implemented but do not currently work**
- `script.sh` Our script runs the program but has issues accessing our class in our code

**The following are instructions on how to run the program from the command line**
- We have provided a script `script.sh` that allows users to run our program. You can call this script by using the command ‘./script.sh’. Running this script runs into errors, though, that we plan to address in iteration two.

## Iteration Two
*Wednesday, April 10th, 2024*

**We were able to implement the following features during this iteration:**
- `saveAccountData` Users can create multiple accounts whose information is stored/saved
- `getAllAccountData` On start, each user that is saved in the text file is added to a HashMap so users can keep their balance from a previous use of the program
- `processingUserSelection` Users should not be able to withdraw and transfer money that they do not have
- `displayingOptions` Users can perform multiple operations in one session
- `displayingOptions` User can exit the program

**We intend to implement the following features by the next iteration(s):**
- User can access additional information about their account; specifically what type of account it is and its routing number
- User can take out loans and repay loans
- Possible deeper implementation of loans with an interest rate based on time passed 

**To our knowledge** we do not have any features that we have implemented but do not currently work

**The following are instructions on how to run the program from the command line**
- `./script.sh` is the command to input to run our program! It works and should prompt the user and start the program. 

## Iteration Three
*Wednesday, April 24th, 2024*

**We were able to implement the following features during this iteration:**
- Users are able to transfer money to another user's bank account
- Users are able to take out loans and take them back
- Users are able to choose between opening a savings account or a checkings account
- Routing number, account type, and outstanding loan balance are persisted in the text file
- User logging back in must enter their routing number to verify their identity
