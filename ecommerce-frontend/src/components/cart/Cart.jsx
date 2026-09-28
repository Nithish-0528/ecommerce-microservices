import { useCart } from "../../context/CartContext";
import { useNavigate } from "react-router-dom";
import "./Cart.css";

function Cart() {

    const {
        cartItems,
        increaseQuantity,
        decreaseQuantity,
        removeFromCart
    } = useCart();

    const navigate = useNavigate();

    const total = cartItems.reduce(
        (sum, item) => sum + item.quantity * item.price,
        0
    );

    return (
        <div className="cart-page">

            <h1 className="cart-title">Your Cart</h1>

            {cartItems.length === 0 ? (

                <div className="empty-cart">
                    <p>Your cart is currently empty.</p>
                </div>

            ) : (

                <div className="cart-container">

                    {/* Cart Items */}
                    <div className="cart-items">

                        {cartItems.map((item) => (

                            <div
                                key={item.id}
                                className="cart-item"
                            >

                                <div className="cart-item-details">

                                    <h2>{item.name}</h2>

                                    <p className="cart-item-price">
                                        ₹{item.price.toFixed(2)}
                                    </p>

                                </div>

                                <div className="quantity-section">

                                    <button
                                        onClick={() =>
                                            decreaseQuantity(item.id)
                                        }
                                    >
                                        −
                                    </button>

                                    <span>{item.quantity}</span>

                                    <button
                                        onClick={() =>
                                            increaseQuantity(item.id)
                                        }
                                    >
                                        +
                                    </button>

                                </div>

                                <button
                                    className="remove-button"
                                    onClick={() =>
                                        removeFromCart(item.id)
                                    }
                                >
                                    Remove
                                </button>

                            </div>

                        ))}

                    </div>

                    {/* Cart Summary */}
                    <div className="cart-summary">

                        <h2>Order Summary</h2>

                        <div className="summary-row">
                            <span>Subtotal</span>
                            <span>₹{total.toFixed(2)}</span>
                        </div>

                        <div className="summary-row">
                            <span>Delivery</span>
                            <span>Free</span>
                        </div>

                        <hr />

                        <div className="summary-total">
                            <span>Total</span>
                            <span>₹{total.toFixed(2)}</span>
                        </div>

                        <button
                            className="checkout-button"
                            onClick={() => navigate("/checkout")}
                        >
                            Proceed to Checkout
                        </button>

                    </div>

                </div>
            )}

        </div>
    );
}

export default Cart;
