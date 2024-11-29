import React, {useEffect, useState} from "react";
import { useParams } from "react-router-dom";
import { useCart } from "../context/CartContext";
import ApiService from "../../service/ApiService";
import '../../style/productDetailsPage.css';


const ProductDetailsPage = () => {

    const {productId} = useParams();
    const {cart, dispatch} = useCart();
    const [product, setProduct] = useState(null);

    useEffect(()=>{
        fetchProduct();
    }, [productId])

    const fetchProduct = async () => {
        try {
            const response = await ApiService.getProductById(productId);
            setProduct(response.product);
            
        } catch (error) {
            console.log(error.message || error)
        }
    }

    
    const addToCart = () => {
        if (product) {
            dispatch({type: 'ADD_ITEM', payload: product});   
        }
    }

    const incrementItem = () => {
        if(product){
            dispatch({type: 'INCREMENT_ITEM', payload: product});
 
        }
    }

    const decrementItem = () => {
        if (product) {
            const cartItem = cart.find(item => item.id === product.id);
            if (cartItem && cartItem.quantity > 1) {
                dispatch({type: 'DECREMENT_ITEM', payload: product}); 
            }else{
                dispatch({type: 'REMOVE_ITEM', payload: product}); 
            }
            
        }
    }

    if (!product) {
        return <p>Loading product details ...</p>
    }

    const cartItem = cart.find(item => item.id === product.id);

    return (
        <div className="product-detail container">
          <div className="row">
            <div className="col-md-6">
              <img
                src={product?.imageUrl}
                alt={product?.name}
                className="img-fluid rounded mb-4"
              />
            </div>
            <div className="col-md-6">
              <h1>{product?.name}</h1>
              <p>{product?.description}</p>
              <span className="price">${product.price.toFixed(2)}</span>
      
              {/* Condiciones del carrito */}
              {cartItem ? (
                <div className="quantity-controls mt-4">
                  <button
                    className="btn btn-outline-secondary quantity-btn"
                    onClick={decrementItem}
                  >
                    -
                  </button>
                  <span className="mx-3">{cartItem.quantity}</span>
                  <button
                    className="btn btn-outline-secondary quantity-btn"
                    onClick={incrementItem}
                  >
                    +
                  </button>
                </div>
              ) : (
                <button
                  className="btn btn-primary btn-medium mt-4"
                  onClick={addToCart}
                >
                  Añadir al carrito
                </button>
              )}
            </div>
          </div>
        </div>
      );
}

export default ProductDetailsPage;