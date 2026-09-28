
const createOrderApi = "http://localhost:8083/orders"
export const placeOrder = async (order) => {

    const response = await fetch(createOrderApi, {
        method: "POST",
        body: JSON.stringify(order),
        headers: {
            "Content-Type" : "application/json"
        }
    });

    if (!response.ok) {
        throw new Error("Order not created");
    }
    const data = await response.json();

    return data;

}

export const getAllOrders = async () => {

    const response = await fetch(createOrderApi);

    if (!response.ok) {
        throw new Error("List of orders not found");
    }

    const data = await response.json();

    return data;
    
}