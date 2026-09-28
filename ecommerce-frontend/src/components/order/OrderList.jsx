import { useEffect, useState } from "react";
import { getAllOrders } from "../../services/orderService";
import "./OrderList.css";

function OrderList() {

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [orders, setOrders] = useState([]);

    useEffect(() => {

        const getOrders = async () => {

            setLoading(true);

            try {

                const list = await getAllOrders();

                setOrders(list);

            } catch (error) {

                setError("Failed to load orders");

            } finally {

                setLoading(false);
            }
        };

        getOrders();

    }, []);


    if (loading) {
        return (
            <div className="orders-message">
                Orders are loading...
            </div>
        );
    }


    if (error !== null) {
        return (
            <div className="orders-message error">
                {error}
            </div>
        );
    }


    if (orders.length === 0) {
        return (
            <div className="orders-page">

                <h1>My Orders</h1>

                <div className="empty-orders">
                    <h2>No Orders Yet</h2>
                    <p>
                        You haven't placed any orders yet.
                    </p>
                </div>

            </div>
        );
    }


    return (

        <div className="orders-page">

            <h1 className="orders-title">
                My Orders
            </h1>

            <div className="orders-list">

                {orders.map((order) => (

                    <div
                        key={order.id}
                        className="order-card"
                    >

                        {/* Header */}

                        <div className="order-header">

                            <div>
                                <p className="order-label">
                                    Order ID
                                </p>

                                <h2>
                                    #{order.id}
                                </h2>
                            </div>

                            <span
                                className={`order-status ${order.status?.toLowerCase()}`}
                            >
                                {order.status}
                            </span>

                        </div>


                        {/* Order Details */}

                        <div className="order-details">

                            <div className="order-detail">

                                <span className="detail-label">
                                    Customer
                                </span>

                                <span>
                                    {order.name}
                                </span>

                            </div>


                            <div className="order-detail">

                                <span className="detail-label">
                                    Location
                                </span>

                                <span>
                                    {order.city}
                                </span>

                            </div>


                            <div className="order-detail">

                                <span className="detail-label">
                                    Payment
                                </span>

                                <span>
                                    {order.paymentMethod}
                                </span>

                            </div>


                            <div className="order-detail">

                                <span className="detail-label">
                                    Total
                                </span>

                                <span className="order-price">
                                    ₹{order.totalPrice?.toFixed(2)}
                                </span>

                            </div>

                        </div>

                    </div>

                ))}

            </div>

        </div>
    );
}

export default OrderList;
