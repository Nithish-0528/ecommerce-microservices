import { useLocation, useNavigate } from "react-router-dom";
import "./OrderSuccess.css";


export function OrderSuccess() {
    const navigate = useNavigate();
    const location = useLocation();

    const order = location.state?.order;

    return ( 
    
    <div className="order-success-page"> 
    <div className="success-card"> 
        <div className="success-icon"> ✓ </div>
         <h1>Order Placed Successfully!</h1>
          <p className="success-message"> 
            Thank you for your order. Your order has been placed successfully. 
            </p> {order && ( <div className="order-info"> 
                <p> <strong>Order ID:</strong>{" "} {order.id} </p>
                 <p> <strong>Payment:</strong>{" "} {order.paymentMethod} </p> 
                 </div>
                 
            )} 
            <button className="continue-shopping-button" 
            onClick={() => navigate("/product")} >
                 Continue Shopping </button> 
                 </div> 
                 
        </div> 
        );
}