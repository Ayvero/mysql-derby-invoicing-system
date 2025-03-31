# 💻 Java Invoicing System - DAO Pattern & MySQL/Derby  
![Java](https://img.shields.io/badge/Java-DAO%20Pattern-blue)  
![Database](https://img.shields.io/badge/Database-MySQL%20%7C%20Derby-green)  

## 📝 Description  
This project is a **database-driven invoicing system** implemented in **Java** using the **DAO (Data Access Object) pattern**. It supports **MySQL and Derby databases** and allows initial data loading from **CSV files**.  

### ✨ Features  
✅ DAO implementation for database interactions  
✅ MySQL and Derby support  
✅ CSV-based data import  
✅ Retrieve the highest revenue product  
✅ Rank customers by total billing  

## 🚀 Technologies  
- Java  
- MySQL / Derby  
- JDBC  
- DAO Pattern  


## 📌 How to Use  
1. Clone the repository:  
   ```sh
   git clone https://github.com/Ayvero/mysql-derby-invoicing-system.git

2. Configure MySQL or Derby as the database.

3. Load initial data from CSV files (datasets folder).

Run the Main.java to execute queries.

🔬 Example Functionality

ProductoDTO productoConMayorRecaudacion = service.getProductoConMayorRecaudacion();
System.out.println("Producto con Mayor Recaudación: " + productoConMayorRecaudacion.getNombre());



