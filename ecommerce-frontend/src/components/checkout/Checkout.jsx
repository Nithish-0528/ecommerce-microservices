import { useState } from "react";
import { useCart } from "../../context/CartContext";
import "./Checkout.css";
import { placeOrder } from "../../services/orderService";
import { useNavigate } from "react-router-dom";

export default function Checkout() {

    const navigate = useNavigate();
    const { cartItems, clearCartItems } = useCart();

    const [paymentMethod, setPaymentMethod] = useState("COD");

    const [address, setAddress] = useState(
        {
            name: "Nithish",
            phone: "9952120906",
            address: "Maruthi Nilaya",
            city: "Bangalore",
            state: "Karnataka",
            pincode: "560075"
        }
    );

    const handleChange = (event) => {

        const { name, value } = event.target;

        setAddress((previousAddress) => ({ ...previousAddress, [name]: value }));

    }

    const handlePaymentChange = (event) => {
        setPaymentMethod(event.target.value);
    }

    const handleOrderSubmit = async (event) => {
        event.preventDefault();
        console.log("Address = ", address);
        console.log("Payment Method", paymentMethod);



        const orderRequest = {
            userId : 2,
            deliveryAddress: address,
            paymentMethod: paymentMethod,
            items: cartItems.map((item) => ({productId: item.id, quantity: item.quantity}))
        }

        try{
            const order = await placeOrder(orderRequest);
            console.log(order, "order created");
            clearCartItems();
            navigate("/order-success", {
                state: {
                    order: order
                }
            })
        } catch(error) {
            console.log("Error", error);
        }


    }

    const total = cartItems.reduce(
        (sum, item) => sum + item.quantity * item.price,
        0
    );

    return (<div className="checkout-page">
        <h1 className="checkout-title"> Checkout
        </h1> <div className="checkout-container">
            {/* Left Side */} <div className="checkout-left">
                {/* Order Summary */} <div className="checkout-items">
                    <h2>Order Summary</h2>
                    {cartItems.map((item) =>
                    (<div key={item.id}
                        className="checkout-item"
                    >
                        <div className="checkout-item-details">
                            <h3>{item.name}</h3>
                            <p> Quantity: {item.quantity} </p>
                        </div> <p className="checkout-item-price">
                            ₹{(item.price * item.quantity).toFixed(2)} </p>
                    </div>
                    ))}
                </div>
                {/* Delivery Address */}
                <div className="address-section">
                    <h2>Delivery Address</h2>
                    <form onSubmit={handleOrderSubmit}>
                        <div className="form-group">
                            <label> Full Name </label>
                            <input type="text" name="name"
                                value={address.name}
                                onChange={handleChange}
                                placeholder="Enter your full name"
                                required /> </div>
                        <div className="form-group">
                            <label> Phone Number </label>
                            <input type="tel" name="phone"
                                value={address.phone}
                                onChange={handleChange}
                                placeholder="Enter your phone number"
                                required /> </div>
                        <div className="form-group">
                            <label> Address </label>
                            <textarea name="address"
                                value={address.address}
                                onChange={handleChange}
                                placeholder="House / Flat / Street"
                                rows="3" required /> </div>
                        <div className="form-row">
                            <div className="form-group">
                                <label> City </label>
                                <input type="text" name="city"
                                    value={address.city}
                                    onChange={handleChange}
                                    placeholder="City"
                                    required />
                            </div>
                            <div
                                className="form-group">
                                <label> State </label>
                                <input type="text" name="state"
                                    value={address.state}
                                    onChange={handleChange}
                                    placeholder="State"
                                    required />
                            </div>
                        </div>
                        <div className="form-group">
                            <label> Pincode </label>
                            <input type="text"
                                name="pincode"
                                value={address.pincode}
                                onChange={handleChange}
                                placeholder="Enter pincode"
                                required />
                        </div>
                        {/* <button type="submit"
                            className="continue-button"
                        > Continue to Payment
                        </button> */}
                        {/* Payment Method */} <div className="payment-section">
                            <h2>Payment Method</h2> <label className="payment-option">
                                <input type="radio" name="paymentMethod" value="COD"
                                    checked={paymentMethod === "COD"} onChange={handlePaymentChange} />
                                <span>Cash on Delivery</span> </label> <label className="payment-option">
                                <input type="radio" name="paymentMethod" value="UPI" checked={paymentMethod === "UPI"}
                                    onChange={handlePaymentChange} />
                                <span>UPI</span> </label>
                            <label className="payment-option">
                                <input type="radio" name="paymentMethod"
                                    value="CARD" checked={paymentMethod === "CARD"}
                                    onChange={handlePaymentChange} />
                                <span>Credit / Debit Card</span>
                            </label> </div>
                        <button type="submit"
                            className="place-order-button" >
                            Place Order
                        </button>
                    </form>
                </div>
            </div>
            {/* Right Side */}
            <div className="checkout-summary">
                <h2>Price Details</h2>
                <div className="checkout-summary-row">
                    <span>Subtotal</span> <span> ₹{total.toFixed(2)} </span>
                </div> <div className="checkout-summary-row">
                    <span>Delivery</span> <span>Free</span>
                </div> <hr /> <div className="checkout-total">
                    <span>Total</span> <span> ₹{total.toFixed(2)}
                    </span>
                </div>
            </div>
        </div>
    </div>
    );

}
