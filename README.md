A small message processing application
======
This is an implementation of a small message processing application in Java. A `HashMap` data structure is used to be able to store and retrieve a list of messages per each product type with a constant time.
There are two types of messages supported by the message processor, i.e. Sale and Operation. The Sale and Operation messages need to implement the `SaleMessage` and `OperationMessage` interfaces respectively. The `processMessage` public method accepts a message as an argument and does the process accordingly based on the message type.
