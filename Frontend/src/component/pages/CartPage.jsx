import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ApiService from "../../service/ApiService";
import { useCart } from "../context/CartContext"
import '../../style/cart.css'

const CartPage = () =>{
    
    const {cart, dispatch} = useCart();
    const [message, setMessage] = useState(null);
    const navigate = useNavigate();

    
      const incrementItem = (product) => {
        dispatch({ type: "INCREMENT_ITEM", payload: product });
      };
    
      const decrementItem = (product) => {
        const cartItem = cart.find((item) => item.id === product.id);
        if (cartItem && cartItem.quantity > 1) {
          dispatch({ type: "DECREMENT_ITEM", payload: product });
        } else {
          dispatch({ type: "REMOVE_ITEM", payload: product });
        }
      }
      const totalPrice = cart.reduce((total, item)=> total + item.price * item.quantity, 0);
      
      const handleCheckout = async () =>{
        if (!ApiService.isAuthenticated()) {
            setMessage("Debes iniciar sesión para comprar")
            setTimeout(()=>{
                setMessage('')
                navigate("/login")
            }, 3000);
            return;

            
        }
        const orderItems = cart.map(item=>({
            productId: item.id,
            quantity: item.quantity
        }));

        const orderRequest = {
            totalPrice,
            items: orderItems,
        }

        try {
            const response = await ApiService.createOrder(orderRequest);
            setMessage(response.message)

            if (response.status === 200) {
                dispatch({type: 'CLEAR-CART'})
                
            }
        } catch (error) {
            setMessage(error.response?.data?.message || error.message || 'Error al realizar el pedido')
            setTimeout(()=>{
                setMessage('')
            }, 3000);

            
        }

      };

      return (
        <div className="container cart-page my-5">
            <h1 className="text-center mb-4">Carrito</h1>
            {message && <p className="response-message text-center text-success">{message}</p>}
    
            {cart.length === 0 ? (
                <p className="text-center">Tu carrito está vacío</p>
            ) : (
                <div>
                    <ul className="list-unstyled">
                        {cart.map(item => (
                            <li key={item.id} className="cart-item d-flex align-items-center mb-4">
                                <img src={item.imageUrl} alt={item.name} className="cart-item-image"/>
                                <div className="cart-item-details ms-3">
                                    <h2 className="cart-item-name">{item.name}</h2>
                                    <p className="cart-item-description">{item.description}</p>
                                    <div className="quantity-controls d-flex align-items-center">
                                        <button className="btn btn-sm btn-outline-danger me-2" onClick={() => decrementItem(item)}>-</button>
                                        <span className="quantity">{item.quantity}</span>
                                        <button className="btn btn-sm btn-outline-success ms-2" onClick={() => incrementItem(item)}>+</button>
                                    </div>
                                    <span className="cart-item-price">${item.price.toFixed(2)}</span>
                                </div>
                            </li>
                        ))}
                    </ul>
                    <h2 className="text-end">Total: ${totalPrice.toFixed(2)}</h2>
                    <div className="text-center">
                        <button className="btn btn-custom w-100" onClick={handleCheckout}>Verificar</button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default CartPage;

