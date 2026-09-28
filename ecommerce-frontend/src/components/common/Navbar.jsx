import { Link } from "react-router-dom";

function Navbar () {
    return (
            <nav className="navbar">

            <div className="navbar-brand">
                E-Commerce
            </div>

            <div className="navbar-links">
                {/* <a href="/product">Products</a>
                <a href="/orders">My Orders</a>
                <a href="/users">Users</a> */}
                <Link to="/product">product</Link>
                <Link to="/orders">orders</Link>
                <Link to="/users">users</Link>
                <Link to="/cart">Cart</Link>
                <Link to="/checkout"> Checkout</Link>
            </div>

            <div className="navbar-cart">
                🛒 Cart
            </div>

        </nav>
    )
}

export default Navbar;