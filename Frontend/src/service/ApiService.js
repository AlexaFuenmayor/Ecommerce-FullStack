import axios from "axios";

export default class ApiService{
    static BASE_URL = "http://localhost:8080";

    static getHeader(){
        const  token = localStorage.getItem("token");
        return {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
        };

    }

    //ENDPOINTS: AUTH, USERS, API:

    static async registerUser(registration){
        const response = await 
        axios.post(`${this.BASE_URL}/auth/register`, 
             registration) //ESTE ES EL BODY
        return response.data
    }

    static async loginUser(loginDetails){
        const response = await 
        axios.post(`${this.BASE_URL}/auth/login`, 
             loginDetails) //ESTE ES EL BODY
        return response.data
    }

    static async getLoggedInUserInfo(){
        const response = await 
        axios.get(`${this.BASE_URL}/user/my-info`, {
            headers: this.getHeader()
        });
        return response.data
    }


    //ENDPOINTS: PRODUCT:

    static async addProduct(formData){
        const response = await 
        axios.post(`${this.BASE_URL}/product/create`, 
             formData,{
                headers: {
                    ...this.getHeader(),
                    "Content-Type": "multipart/form-data" 
                }
             });
        return response.data
    }

        //ENDPOINTS: ACTUALIZAR PRODUCT:

        static async updateProduct(productId, formData){
            const response = await 
            axios.put(`${this.BASE_URL}/product/update${productId}`, 
                 formData, {
                    headers: {
                        ...this.getHeader(),
                        "Content-Type": "multipart/form-data" 
                    }
                 }); //ESTE ES EL BODY
            return response.data
        }

        static async getAllProducts(){
            const response = await 
            axios.get(`${this.BASE_URL}/product/get-all`) 
            return response.data
        }

        static async searchProducts(searchValue){
            const response = await 
            axios.get(`${this.BASE_URL}/product/search`, {
                params: {searchValue}
                });
                console.log("searchValue:", searchValue);
            return response.data
        }
        

        static async getAllProductsByCategoryId(categoryId){
            const response = await 
            axios.get(`${this.BASE_URL}/product/get-by-category-id/${categoryId}`) 
            return response.data
        }

        static async  getProductById(productId){
            const response = await 
            axios.get(`${this.BASE_URL}/product/get-by-product-id/${productId}`) 
            return response.data
        }

        static async deleteProduct(productId){
            const response = await 
            axios.delete(`${this.BASE_URL}/product/delete/${productId}`,{
                headers: this.getHeader()
            });
            return response.data
        }

        //CATEGORY 

        static async createCategory(body){
            const response = await axios.post(`${this.BASE_URL}/category/create`, body, {
                headers: this.getHeader()
            })
            return response.data;
        }

        static async getAllCategory(){
            const response = await axios.get(`${this.BASE_URL}/category/get-all`)
            return response.data;
        }

        static async getCategoryById(categoryId){
            const response = await axios.get(`${this.BASE_URL}/category/get-category-by-id/${categoryId}`)
            return response.data;
        }

        static async updateCategory(categoryId, body){
            const response = await axios.put(`${this.BASE_URL}/category/update/${categoryId}`, body, {
                headers: this.getHeader()
            })
            return response.data;
        }

        static async deleteCategory(categoryId){
            const response = await 
            axios.delete(`${this.BASE_URL}/category/delete/${categoryId}`,{
                headers: this.getHeader()
            });
            return response.data
        }


        //ORDER

        static async createOrder(body) {
            const response = await axios.post(`${this.BASE_URL}/order/create`, body, {
                headers: this.getHeader()
            })
            return response.data;
        }
    
        static async getAllOrders() {
            const response = await axios.get(`${this.BASE_URL}/order/filter`, {
                headers: this.getHeader()
            })
            return response.data;
        }
    
        static async getOrderItemById(itemId) {
            const response = await axios.get(`${this.BASE_URL}/order/filter`, {
                headers: this.getHeader(),
                params: {itemId}
            })
            return response.data;
        }
    
        static async getAllOrderItemsByStatus(status) {
            const response = await axios.get(`${this.BASE_URL}/order/filter`, {
                headers: this.getHeader(),
                params: {status}
            })
            return response.data;
        }
    
        static async updateOrderitemStatus(orderItemId, status) {
            const response = await axios.put(`${this.BASE_URL}/order/update-item-status/${orderItemId}`, {}, {
                headers: this.getHeader(),
                params: {status}
            })
            return response.data;
        }
    
    
    
    
        //ADDRESS  
        static async saveAddress(body) {
            const response = await axios.post(`${this.BASE_URL}/address/save`, body, {
                headers: this.getHeader()
            })
            return response.data;
        }
    
        ///AUTHEMNTICATION CHECKER 
        static logout(){
            localStorage.removeItem('token')
            localStorage.removeItem('role')
        }
    
        static isAuthenticated(){
            const token = localStorage.getItem('token')
            return !!token
        }
    
        static isAdmin(){
            const role = localStorage.getItem('role')
            return role === 'ADMIN'
        }



}