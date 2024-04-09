#!/bin/bash

cd src/bankapp

#javac command to compile

/usr/bin/javac Menu.java BankAccount.java

cd ..

if [ $? -eq 0 ]; then
    # If compilation was successful, run the Menu class
    /usr/bin/java bankapp.Menu
else
    echo "Compilation failed. Check your code for errors."
fi

