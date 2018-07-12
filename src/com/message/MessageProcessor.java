package com.message;

import java.util.*;

/**
 * MessageProcessor records and processes two types of messages, i.e. Sale and Operation.
 * A public method, i.e. processMessage, is available for clients.
 * There are two versions for the processMessage method. One for the Sale and one for the Operation messages.
 * Please note that the processMessage method is not thread-safe.
 */
public class MessageProcessor {

    private int countMessage=0;
    //saleTable is a hash table that stores a list of the all sale messages per each product type.
    private Map<String, Collection<SaleMessage>> saleTable = new HashMap<String, Collection<SaleMessage>>();

    //operationTable is a hash table that stores a list of the all operation messages per each product type.
    private Map<String, Collection<OperationMessage>> operationTable = new HashMap<String, Collection<OperationMessage>>();

    /**
     * records a sale message
     */
    public void processMessage(SaleMessage saleMsg){
        if(!checkStatus()) return;
        Collection<SaleMessage> messageList= saleTable.get(saleMsg.getProductType());
        if(messageList==null){
            messageList = new ArrayList<SaleMessage>();
            saleTable.put(saleMsg.getProductType(), messageList);
        }
        messageList.add(saleMsg);
        ++countMessage;
        if(countMessage==50)
            reportOperations();
    }

    /**
     * records and processes an operation message
     */
    public void processMessage(OperationMessage operationMsg){
        if(!checkStatus()) return;
        Collection<OperationMessage> operationList= operationTable.get(operationMsg.getProductType());
        if(operationList==null){
            operationList = new ArrayList<OperationMessage>();
            operationTable.put(operationMsg.getProductType(), operationList);
        }
        operationList.add(operationMsg);
        applyMessage(operationMsg); // process the message
        ++countMessage;
        if(countMessage==50)
            reportOperations();
    }

    /**
     * processes an operation, i.e. add, subtract, or multiply, on a product type.
     * The operation is applied on all the sale records for the product type.
      */
    private void applyMessage(OperationMessage operationMsg){
        Collection<SaleMessage> saleList= saleTable.get(operationMsg.getProductType());
        if(saleList!=null){
                switch (operationMsg.getOperation())
                {
                    case ADD:
                        for(SaleMessage saleMsg:saleList)
                            saleMsg.setValue(saleMsg.getValue()+operationMsg.getValue());
                        break;
                    case SUBTRACT:
                        for(SaleMessage saleMsg:saleList)
                            saleMsg.setValue(Math.abs(saleMsg.getValue()-operationMsg.getValue()));
                        break;
                    case MULTIPLY:
                        for(SaleMessage saleMsg:saleList)
                            saleMsg.setValue(saleMsg.getValue()*operationMsg.getValue());
                        break;
                }
        }
    }

    /**
     * checks the number of the received messages and calls the sales report per every 10 messages
     * @return returns false if 50 messages have already been received
     */
    private boolean checkStatus(){
        if(countMessage>=50){
            System.out.println("\nThe service is not accepting any more message. It has already reached the limit of 50 messages.");
            return false;
        }
        if(countMessage>0 && countMessage%10==0)
            reportSales(); //  calls the sales report per every 10 messages
        return true;
    }

    /**
     * reports all the received adjustment operations
     */
    private void reportOperations(){
        System.out.println("\nReport of adjustments");
        System.out.println("==============================================");
        Iterator it = operationTable.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            Collection<OperationMessage> optList= (Collection<OperationMessage>) entry.getValue();
            for (OperationMessage opt : optList) {
                System.out.println("product = "+ opt.getProductType() + " | Operation = "+ opt.getOperation()+ " | value = "+opt.getValue());
            }
        }
    }

    /**
     * reports the sales for each product type per every 10 messages
     */
    private void reportSales(){
        System.out.println("\nAn update report after getting "+countMessage+" messages");
        System.out.println("==============================================");
        Iterator it = saleTable.entrySet().iterator();
        while (it.hasNext()) {
            int num=0;
            double value=0;
            Map.Entry entry = (Map.Entry)it.next();
            Collection<SaleMessage> saleList= (Collection<SaleMessage>) entry.getValue();
            for (SaleMessage sale : saleList) {
                num+=sale.getNumber();
                value+=sale.getValue()*sale.getNumber();
            }
            System.out.println("product = "+ entry.getKey() + " | number of sales = "+ num+ " | total value = "+value);
        }
    }
}
