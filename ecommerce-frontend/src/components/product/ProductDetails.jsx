import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getProductById } from "../../services/productService";
import "./ProductDetails.css";
import { useCart } from "../../context/CartContext";

function ProductDetails() {

    const { id } = useParams();
    const navigate = useNavigate();
    const {addToCart} = useCart();

    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {

        const getProductUsingId = async () => {

            try {
                setLoading(true);

                const product = await getProductById(id);

                setProduct(product);

            } catch (error) {

                setError("Product id not found: " + id);

            } finally {

                setLoading(false);
            }
        };

        getProductUsingId();

    }, [id]);


    if (loading) {
        return (
            <div className="product-details-message">
                Product details are loading...
            </div>
        );
    }


    if (error !== null) {
        return (
            <div className="product-details-message">
                {error}
            </div>
        );
    }


    if (product === null) {
        return (
            <div className="product-details-message">
                Product not found
            </div>
        );
    }


    return (
        <div className="product-details-page">

            <button
                className="back-button"
                onClick={() => navigate("/product")}
            >
                ← Back to Products
            </button>


            <div className="product-details-container">

                {/* Product Image */}

                <div className="product-image-section">

                    <div className="product-image-placeholder">
                        🛍️
                    </div>

                </div>


                {/* Product Information */}

                <div className="product-info-section">

                    <p className="product-category">
                        ELECTRONICS
                    </p>

                    <h1>{product.name}</h1>

                    <div className="product-rating">
                        ★★★★★
                        <span> 4.8 (120 reviews)</span>
                    </div>

                    <p className="product-price">
                        ₹{product.price}
                    </p>

                    <p className="product-description">
                        {product.description}
                    </p>

                    <div className="stock-info">
                        <strong>Available Stock:</strong>{" "}
                        {product.quantity}
                    </div>


                    <div className="product-actions">

                        <button className="add-cart-button" onClick={() => addToCart(product)}>
                            Add to Cart
                        </button>

                    </div>


                    <div className="product-features">

                        <div>
                            🚚 <strong>Free Delivery</strong>
                            <p>Fast delivery to your location</p>
                        </div>

                        <div>
                            🔄 <strong>Easy Returns</strong>
                            <p>7 days return policy</p>
                        </div>

                        <div>
                            🔒 <strong>Secure Payment</strong>
                            <p>100% secure checkout</p>
                        </div>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default ProductDetails;
