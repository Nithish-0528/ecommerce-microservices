import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import Navbar from './components/common/Navbar'
import ProductList from './components/product/productList'
import UserList from './components/user/userList'
import ProductDetails from './components/product/ProductDetails'
import Cart from './components/cart/cart'
import CartProvider from './context/CartContext'
import Checkout from './components/checkout/checkout'
import { OrderSuccess } from './components/order/OrderSuccess'
import OrderList from './components/order/OrderList'

function App() {
console.log("APPLICATION STARTED");
  return (
    <>
    <CartProvider>
      <Navbar />
          <Routes>

              <Route path="/product"  element={<ProductList />} />

              <Route path="/product/:id"  element={<ProductDetails />} />

              <Route path="/cart" element={<Cart />} />

              <Route path='/checkout' element={<Checkout />} />

              <Route path='/order-success' element={<OrderSuccess />} />

              <Route path='/orders' element={<OrderList />} />

          </Routes>
      </CartProvider>
      {/* <ProductList /> */}
      {/* <UserList /> */}
    </>
  )
}

export default App
