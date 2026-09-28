import { useEffect, useState } from "react";
import { getProducts } from "../../services/productService";
import ProductCard from "./ProductCard";

function ProductList() {

    const [ products, setProduct ] =  useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {

        const getProductsList = async () => {

            setLoading(true);
            try{
            const productData = await getProducts();
                setProduct(productData);
            } catch(error) {
                console.log(error, 'Error from product service');
                setError(error);
            } finally {
                setLoading(false);
            }
            
        }

        getProductsList();

    }, [])

    if (error !== null && error !== "") {

        return <><h2> Products not found  </h2></>

    }

    if (loading) {
        return <><h2>Loading ... </h2></>
    }

    return (
        <div className="product-page">

            <h1>Products</h1>

            <div className="product-grid">

                {products.map((product) => (

                    <ProductCard key={product.id} product={ product }/>

                ))}

            </div>

        </div>
    );
}

export default ProductList;