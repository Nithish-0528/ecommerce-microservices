import { useNavigate } from "react-router-dom";

function ProductCard( {product}) {

    const navigate = useNavigate();

    const handleViewProductClick = () => {
        navigate(`/product/${product.id}`)
    }

    return (
                <div className="product-card" key={product.id}>

                        <h2>{product.name}</h2>

                        <p>{product.description}</p>

                        <p className="price">
                            ₹{product.price}
                        </p>

                        <p>
                            Available Stock: {product.quantity}
                        </p>

                        <button onClick={() => {handleViewProductClick()}}>
                            View Product
                        </button>

                </div>
    )
}

export default ProductCard;