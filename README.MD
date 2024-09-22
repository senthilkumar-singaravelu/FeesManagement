# Fee Management API

This API manages transactions related to student fees. It allows you to create transactions and retrieve receipts for students.

## Endpoints

### Create Transaction

**POST** `/api/v1/receipts/transactions`

This endpoint creates a new transaction for a student fees.

#### Request

- **Content-Type**: `application/json`
- **Body**: A JSON object representing the transaction.

request

{

  "studentId": 1,
  "transactionId": 1,
  "amount": 100.00,
  "transactionDate": "2023-09-22T12:00:00",
  "cardType": "Visa",
  "referenceNumber": "ABC123",
  "status": "SUCCESS"

}


### View Receipts Transaction

**GET**  /api/v1/receipts/students/{studentId}

Response
Status Code:

200 OK if the receipt is found.
404 Not Found if no receipt exists for the given student ID.

Response
{

  "studentId": 1,
  "studentName": "John Doe",
  "grade": "10",
  "transactionId": 1,
  "amount": 100.00,
  "transactionDate": "2023-09-22T12:00:00",
  "cardType": "Visa",
  "referenceNumber": "ABC123",
  "status": "SUCCESS"

}

### Swagger UI http://localhost:8081/swagger-ui.html
  
 ### API Spec : under resources Receiptspec.json