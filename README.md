## **📦 E-commerce Project**  
### **🛒 A Modern Online Store Application**  

---

### **✨ Features**  
- **User Authentication:**  
  - Secure registration and login system with JWT-based authentication.  
- **Product Catalog:**  
  - Browse products with detailed information: name, price, and description.  
- **Shopping Cart:**  
  - Add and manage items before proceeding to checkout. *(Payment gateway coming soon!)*
- **Responsive Design:**  
  - Optimized for mobile, tablet, and desktop views.  

---

### **🚀 Deployment**  
- **Frontend:** Hosted on [Netlify](#)  
- **Backend:** Spring Boot API running locally (ensure it's up for full functionality).  

---

### **🛠️ Tech Stack**  
- **Frontend:** React JS  
- **Backend:** Spring Boot (Java)  
- **Database:** MySQL  
- **Authentication:** JWT  

---

### **📋 Prerequisites**  
1. **Frontend Requirements:**  
   - Web browser (e.g., Chrome, Firefox).  
   - Link to live site: [Netlify Deployment](#).  

2. **Backend Requirements:**  
   - Java 17 installed.  
   - MySQL database running.  
   - IDE (e.g., IntelliJ IDEA) for backend setup.  

---

### **📖 Installation Instructions**  

#### **Clone the repository:**  
```bash
git clone https://github.com/your-repo/ecommerce.git
cd ecommerce
```

#### **Frontend Setup:**  
1. Navigate to the `frontend` directory:  
   ```bash
   cd frontend
   ```
2. Install dependencies:  
   ```bash
   npm install
   ```
3. Start the React development server:  
   ```bash
   npm start
   ```

#### **Backend Setup:**  
1. Import the project into your Java IDE.  
2. Configure `application.properties` with your MySQL credentials:  
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```
3. Run the Spring Boot application.  

#### **Database Setup:**  
- Import the provided SQL file into your MySQL instance:  
   ```bash
   mysql -u your-username -p your-database-name < database.sql
   ```  

---

### **📂 Project Structure**  

#### **Frontend:**  
```
/frontend  
  ├── /src  
  │   ├── /components  
  │   ├── /pages  
  │   └── /styles  
```

#### **Backend:**  
```
/backend  
  ├── /src/main/java/com/project/ecommerce  
  │   ├── /controllers  
  │   ├── /services  
  │   └── /repositories  
```

---

### **💻 How to Use**  
1. Access the deployed app via [Netlify](#).  
2. Register or log in to start using the application.  
3. Browse products, add them to your cart, and proceed to checkout.  

---

### **📅 Future Updates**  
- **Admin Dashboard:** Manage products, categories, and users.  
- **Payment Gateway Integration:** Enable secure online payments.  

---

### **📢 Feedback & Contributions**  
- Issues and pull requests are welcome!  
- Contact me at: alexa.dev.fm@gmail.com  

---

**Made with 💻 and ☕ by Alexandra Fuenmayor**  
