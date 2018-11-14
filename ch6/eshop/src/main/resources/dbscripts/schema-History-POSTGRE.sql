CREATE TABLE IF NOT EXISTS PurchaseHistory (
  id SERIAL NOT NULL PRIMARY KEY,
  customerId integer,
  productId integer,
  createdDate time NULL
);
