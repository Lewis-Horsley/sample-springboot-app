vaudience-test provides a RESTful API for adding and querying Contacts stored within an in-memory H2 database.

Controller:  
Provides RESTful functionality and the connection between application and client.  

Service:  
Provides business logic for a request that has been sent.

Repository:  
Provides database functionality. 

Contact:  
Defines the Contact object that will be stored.

JSON Request Body Format
- 

{
    "name":"John Smith",  
    "dateOfBirth":"1999-06-08",  
    "address":{
        "addressField":"1 Street",
        "addressField2":"Coventry",
        "city":"London",
        "postcode":"SW11AB"
    }   
}

USAGE
- 
Once running, the application should be on localhost:8080

To add a Contact:  
POST request to 'localhost:8080/contacts' 

To get all Contacts:  
GET request to 'localhost:8080/contacts'

To filter Contacts by Postcode:  
GET request to 'localhost:8080/contacts/{postcode}'


