Payment Points
==============
Welcome to the Java application that calculates the final price and earned points for a customer's purchase, based on the selected payment method. 
This application integrates with shops and online stores and provides a variety of payment methods, rates, and points based on the customer's choice of payment.

To make a payment, the application provides a set of requests and responses, making it easy to integrate into any e-commerce system. 
In addition, the system allows users to view sales data, broken down into hours, and also shows a list of sales with the corresponding points given to the customer.

New Payment Methods can be added to the system dynamically, without the need to restart the application.

### Requirements
- JDK 17+
- Maven
- PostgreSQL 14+ (With database created and user with all privileges to create objects)
- Git

### How to run
1. Clone the repository
2. To generate artifacts Run `mvn clean install` in the root directory
3. To launch application Run `mvn spring-boot:run` in the root directory
4. Application should be available on `localhost:8080`

Liquibase will automatically create all the necessary tables and fill them with a data at application startup.

### Testing
Test aren't the subject right now as we discussed earlier. 
Will be implemented if other parts of the project are somehow "good enough" to consider. 

### Performance Testing
Aren't the subject right now as we discussed earlier. 
Will be implemented if other parts of the project are somehow "good enough" to consider.

For example all primary key indexes should be made of HASH type or be changed to a monotonic numeric id.
Obviously need some caching for the most used queries.
JMeter can be used for performance testing.

### Example request entities
PaymentRequest
```json
{
  "customerId": "12345",
  "price": "100.00",
  "priceModifier": 0.95,
  "paymentMethod": "MASTERCARD",
  "datetime": "2022-09-01T00:00:00Z",
  "additionalItem": {
    "last4": "1234"
  }
}
```
PaymentRequest
```json
{ "finalPrice": "95.00", "points": 5 }
```
SalesRequest
```json
{
    "startDateTime": "2022-09-01T00:00:00Z",
    "endDateTime": "2022-09-01T23:59:59Z"
}
```

```json
{
  "sales": [
    {
      "datetime": "2022-09-01T00:00:00Z",
      "sales": "1000.00",
      "points": 10
    },
    {
      "datetime": "2022-09-01T01:00:00Z",
      "sales": "2000.00",
      "points": 20
    },
    {
      "datetime": "2022-09-02T00:00:00Z",
      "sales": "5000.00",
      "points": 75
    }
  ]
}
```

### Known issues
- Spring Boot 3 version of library that brings support of gRPC is not yet released.
Obviously this is a refactoring candidate for the future.
- Protobuf still doesn't support "BigDecimal" natively.