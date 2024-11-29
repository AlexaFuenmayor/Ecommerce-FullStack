import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import ProductList from "../common/ProductList";
import Pagination from "../common/Pagination";
import ApiService from "../../service/ApiService";
// import '../../style/home.css'

const Home = () => {
  const location = useLocation();

  const [products, setProducts] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [error, setError] = useState(null);

  const itemsPerPage = 4;

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        let allProducts = [];
        const queryparams = new URLSearchParams(location.search);
        const searchItem = queryparams.get('search'); //tiene que ver con el método handleSearchSubmit en el componente Navbar, donde se llama a navigate(`/search=${searchValue}`)
        if (searchItem) {
          const response = await ApiService.searchProducts(searchItem);

          //productList es uno de los key obj JSON que trae la petición getAllProducts
          allProducts = response.productList || [];
        } else {
          const response = await ApiService.getAllProducts();
          allProducts = response.productList || [];
        }

        setTotalPages(Math.ceil(allProducts.length /itemsPerPage));
        setProducts(
          allProducts.slice(
            (currentPage - 1) * itemsPerPage,
            currentPage * itemsPerPage
          )
        );
      } catch (error) {
        setError(
            error.response?.data?.message ||
            error.message ||
            "No se pueden obtener los productos"
        );
      }
    };

    fetchProducts();
  }, [location.search, currentPage])

  return (
    <div className="container mt-5">
      {error ? (
        <div className="alert alert-danger text-center" role="alert">
          {error}
        </div>
      ) : (
        <div>
          <div className="row">
            <ProductList products={products} />
          </div>
          <div className="d-flex justify-content-center mt-4">
            <Pagination
              currentPage={currentPage}
              totalPages={totalPages}
              onPageChange={(page) => setCurrentPage(page)}
            />
          </div>
        </div>
      )}
    </div>
  );
}

export default Home;
