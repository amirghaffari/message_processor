package com.demo;

import com.message.MessageProcessor;
import com.message.OperationMessage;

import java.util.ArrayList;

/**
 * demonstrates how to use the MessageProcessor class
 * Please note that the messages can be sent to the processor in any arbitrary order. This demonstration sends the
 * messages in a regular way for the sake of simplicity and trackability.
 */
public class Main {

    public static void main(String[] args) {

        // creating a list of products
        ArrayList<String> products=new ArrayList<String>();
        products.add("Apple");
        products.add("Orange");
        products.add("Bennana");
        products.add("Peach");
        products.add("Grape");
        products.add("Kiwifruit");

        // creating a list of sale messages with 47 elements
        // [(Apple, 1,1), (Orange, 2, 2), (Bennana, 3, 3), (Peach, 4, 4), (Grape, 5, 5), (Kiwifruit, 6, 6),... ,
        ArrayList<Sale> saleList=new ArrayList<Sale>();
        for(int i=0;i<47;++i){
            int productIndex = i% products.size();
            int number = productIndex+1; // Apple =>1 , Orange => 2, Bennana => 3, Peach => 4, Grape => 5, Kiwifruit => 6
            saleList.add(new Sale(products.get(productIndex), number, number));// create and add a sale message to the list
        }

        // creating a list of operation messages with 4 elements
        ArrayList<Operation> optList=new ArrayList<Operation>();
        optList.add(new Operation(products.get(0), 3, OperationMessage.Operation.ADD)); // add 3p to all the Apple sales
        optList.add(new Operation(products.get(1), 2, OperationMessage.Operation.MULTIPLY)); // multiply the Orange's value with 2
        optList.add(new Operation(products.get(2), 1, OperationMessage.Operation.ADD)); // add 1p to all the Bennana sales
        optList.add(new Operation(products.get(3), 1, OperationMessage.Operation.SUBTRACT)); // subtract 1 from all the Peach sales

        // optList contains 4 operation messages
        // saleList contains 47 sale messages
        MessageProcessor processor=new MessageProcessor();
        // sending all the 51 messages to the MessageProcessor
        // message number 11, 21, 31, and 41 are the operation messages, the others are sale messages
        // expecting the last message (51th) will be ignored by the processor as it only accepts 50 messages

        int saleCounter=0;
        int optCounter=0;
        while(saleCounter+optCounter<51){
            if(saleCounter>0 && saleCounter%10==0) // per every 10 sale messages, send an operation message
            {
                processor.processMessage(optList.get(optCounter++));//sending an operation message

            }
            processor.processMessage(saleList.get(saleCounter++));//sending a sale message
        }
    }
}
