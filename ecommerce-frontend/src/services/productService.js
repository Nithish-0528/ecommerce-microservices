const productListApi = "http://localhost:8082/products?searchName=";
const productById = "http://localhost:8082/products/";

export const getProducts = async () => {
    console.log("get all products");

    const response = await fetch(productListApi);

    if(!response.ok) {
        throw new Error("Products not found");
    }

    const data = await response.json();

    return data;


}

export const getProductById = async (id) => {
    console.log("Get product by id", id);
    const response = await fetch(productById + id);

    if (!response.ok) {
        throw new Error(`Product with id ${id} not found`);
    }

    const data = await response.json();

    return data;
}