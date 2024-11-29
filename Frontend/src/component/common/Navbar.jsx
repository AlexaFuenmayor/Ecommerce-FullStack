import React, { useState, useEffect } from "react";
import "../../style/navbar.css";
import { NavLink, useNavigate, useLocation } from "react-router-dom";
import ApiService from "../../service/ApiService";
import logo from "../../logo-tienda.png";
import { useCart } from "../context/CartContext"; // Importa el contexto del carrito

const Navbar = () => {
  const [searchValue, setSearchValue] = useState("");
  const navigate = useNavigate();
  const location = useLocation(); // Obtén la ubicación actual de la ruta
  const { dispatch } = useCart(); // Obtén el dispatch del contexto del carrito

  // const isAdmin = ApiService.isAdmin();
  const isAuthenticated = ApiService.isAuthenticated();

  const handleSearchChange = (e) => {
    setSearchValue(e.target.value);
  };

  const handleSearchSubmit = async (e) => {
    e.preventDefault();
    navigate(`/?search=${searchValue}`);
  };

  const handleLogout = () => {
    localStorage.removeItem("token"); // Elimina el token del localStorage
    localStorage.removeItem("role"); // Elimina el rol del localStorage
    const confirm = window.confirm("Quieres salir de la sesión");
    if (confirm) {
      ApiService.logout();
      setTimeout(() => {
        dispatch({ type: "CLEAR_CART" }); // Limpia el carrito usando el contexto
        navigate("/login");
      }, 500);
    }
  };

  // Limpiar el input de búsqueda al cambiar de ruta
  useEffect(() => {
    setSearchValue(""); // Resetea el valor de búsqueda cuando la ruta cambia
  }, [location]);

  return (
    <nav className="navbar navbar-expand-lg navbar-custom">
      <div className="container">
        {/* Logo */}
        <NavLink to="/" className="navbar-brand">
          <img src={logo} alt="logo tienda" />
        </NavLink>

        {/* Toggler for Mobile */}
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        {/* Links and Search */}
        <div className="collapse navbar-collapse" id="navbarNav">
          {/* Search - Ahora está a la izquierda */}
          <div className="navbar-search me-auto">
            <form className="d-flex" onSubmit={handleSearchSubmit}>
              <input
                type="text"
                className="form-control me-2"
                placeholder="Buscar productos"
                value={searchValue}
                onChange={handleSearchChange}
              />
              <button className="btn btn-outline-light" type="submit">
                Buscar
              </button>
            </form>
          </div>

          {/* Links - Ahora están a la derecha */}
          <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <NavLink to="/" className="nav-link">
                Inicio
              </NavLink>
            </li>
            {!isAuthenticated && (
              <li className="nav-item">
                <NavLink to="/login" className="nav-link">
                  Iniciar Sesión
                </NavLink>
              </li>
            )}
            {isAuthenticated && (
              <li className="nav-item">
                <NavLink to="/" className="nav-link" onClick={handleLogout}>
                  Cerrar Sesión
                </NavLink>
              </li>
            )}
            <li className="nav-item">
              <NavLink to="/cart" className="nav-link">
                Carrito
              </NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
