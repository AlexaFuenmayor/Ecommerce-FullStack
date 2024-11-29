import React from "react";
import { Link } from "react-router-dom";
import { useCart } from "../context/CartContext";
import '../../style/productList.css'

const ProductList = ({ products }) => {
  const { cart, dispatch } = useCart();

  const addToCart = (product) => {
    dispatch({ type: "ADD_ITEM", payload: product });
  };
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


  return (
    <div className="product-list row">
      {products.map((product, index) => {
        const cartItem = cart.find((item) => item.id === product.id);
        return (
          <div className="col-12 col-sm-6 col-md-4 col-lg-3 product-item" key={index}>
            <div className="card shadow-sm border-light">
              <Link to={`/product/${product.id}`} className="card-body text-decoration-none">
                <img
                  src={product.imageUrl}
                  alt={product.name}
                  className="card-img-top product-image"
                />
                <h5 className="card-title">{product.name}</h5>
                <p className="card-text">{product.description}</p>
                <span className="product-price">{product.price.toFixed(2)}$</span>
              </Link>

              {cartItem ? (
                <div className="quantity-controls text-center mb-3">
                  <button
                    onClick={() => decrementItem(product)}
                    className="btn btn-outline-danger btn-sm"
                  >
                    -
                  </button>
                  <span className="mx-2">{cartItem.quantity}</span>
                  <button
                    onClick={() => incrementItem(product)}
                    className="btn btn-outline-success btn-sm"
                  >
                    +
                  </button>
                </div>
              ) : (
                <button
                  onClick={() => addToCart(product)}
                  className="btn btn-outline-primary w-100"
                >
                  Añadir al carrito
                </button>
              )}
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default ProductList;
