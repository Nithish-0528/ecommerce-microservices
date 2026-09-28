const userListApi = "http://localhost:8081/users/all";

export const getUserList = async () => {

    const response = await fetch(userListApi);

    if (!response.ok) {
        throw new Error("Failed to fetch user list");
    }

    const data = await response.json();
    console.log(data, 'Data');
    return data;
}
