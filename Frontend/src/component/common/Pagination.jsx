import  React  from "react";
import '../../style/pagination.css'


const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  const pageNumbers = [];

  for (let i = 1; i <= totalPages; i++) {
    pageNumbers.push(i)
  }

  return (
    <nav aria-label="Page navigation">
      <ul className="pagination justify-content-center">
        {pageNumbers.map((number) => (
          <li 
            key={number} 
            className={`page-item ${number === currentPage ? 'active' : ''}`}
          >
            <button 
              onClick={() => onPageChange(number)} 
              className="page-link"
            >
              {number}
            </button>
          </li>
        ))}
      </ul>
    </nav>
  );
}

export default Pagination;
