import React from "react";
// import '../../style/footer.css';
import { NavLink } from "react-router-dom";

const Footer = () => {
  return (
    <footer className="bg-dark text-white py-4">
      <div className="container text-center">
        <div className="row justify-content-center">
          {/* Links */}
          <div className="col-12">
            <ul className="list-unstyled d-flex flex-wrap justify-content-center gap-3 mb-3">
              <li>
                <NavLink 
                  to="https://www.instagram.com/multitienda_heizel/"
                  className="text-white text-decoration-none"
                >
                  Sobre nosotros
                </NavLink>
              </li>
              <li>
                <NavLink
                  to="https://www.instagram.com/multitienda_heizel/"
                  className="text-white text-decoration-none"
                >
                  Contáctanos
                </NavLink>
              </li>
              <li>
                <NavLink to="/" className="text-white text-decoration-none">
                  Términos y condiciones
                </NavLink>
              </li>
              <li>
                <NavLink to="/" className="text-white text-decoration-none">
                  Políticas y privacidad
                </NavLink>
              </li>
              <li>
                <NavLink to="/" className="text-white text-decoration-none">
                  Preguntas
                </NavLink>
              </li>
            </ul>
          </div>

          {/* Información adicional */}
          <div className="col-12">
            <p>&copy; 2024 Desarrollado por Alexandra Fuenmayor</p>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
