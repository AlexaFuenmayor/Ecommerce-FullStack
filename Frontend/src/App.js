import './App.css';
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
// import { ProtectedRoute, AdminRoute } from './service/Guard';
import Navbar from './component/common/Navbar';
import Footer from './component/common/footer';
import { CartProvider } from './component/context/CartContext';
import Home from './component/pages/Home';
import RegisterPage from './component/pages/RegisterPage'
import LoginPage from './component/pages/Login';
import ProductDetailsPage from './component/pages/ProductDetailsPage';
import CartPage from './component/pages/CartPage';


function App() {
  return (
    <BrowserRouter>
     <CartProvider>
       <Navbar />
       <Routes>
        {/* LAS RUTAS: */}
        <Route  exact path='/' element={<Home/>} />
        <Route path='/cart' element={<CartPage />} />
        <Route path='/register' element={<RegisterPage />}/>
        <Route path='/login' element={<LoginPage />}/>
        <Route path='/product/:productId' element={<ProductDetailsPage />}/>



       </Routes>
      <Footer />
     </CartProvider>
     
    </BrowserRouter>
    
  )
}

export default App;
