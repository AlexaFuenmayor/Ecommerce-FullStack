import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useCart } from "../context/CartContext"; // Importa el contexto del carrito
import ApiService from "../../service/ApiService";
import '../../style/register.css';

const LoginPage = () => {
    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });

    const [message, setMessage] = useState(null);
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await ApiService.loginUser(formData);
            if (response.status === 200) {
                setMessage("El usuario inició sesión correctamente");
                localStorage.setItem('token', response.token);
                localStorage.setItem('role', response.role);
                setTimeout(() => {
                    navigate("/");
                }, 4000);
            }
        } catch (error) {
            setMessage(error.response?.data.message || error.message || "El usuario no puede iniciar sesión");
        }
    };



    return (
        <div className="container my-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card shadow-sm p-4">
                        <h2 className="text-center mb-4">Iniciar sesión</h2>
                        {message && <p className="mensaje text-success">{message}</p>}
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label htmlFor="email" className="form-label">Correo electrónico: </label>
                                <input
                                    type="email"
                                    id="email"
                                    name="email"
                                    className="form-control"
                                    value={formData.email}
                                    onChange={handleChange}
                                    required
                                />
                            </div>

                            <div className="mb-3">
                                <label htmlFor="password" className="form-label">Contraseña: </label>
                                <input
                                    type="password"
                                    id="password"
                                    name="password"
                                    className="form-control"
                                    value={formData.password}
                                    onChange={handleChange}
                                    required
                                />
                            </div>

                            <button type="submit" className="btn btn-custom w-100">Iniciar sesión</button>

                            <p className="text-center mt-3">
                                ¿No tienes una cuenta? <a href="/register" className="text-custom">Regístrate</a>
                            </p>
                        </form>

                        {/* Botón de Cerrar Sesión */}
                        {/* <button onClick={handleLogout} className="btn btn-danger w-100 mt-3">
                            Cerrar sesión
                        </button> */}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;
