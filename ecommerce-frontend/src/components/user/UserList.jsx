import { useEffect, useState } from "react";
import {getUserList} from '../../services/userService'

function UserList() {

    const [user, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        const getUsers = async () => {
            setLoading(true);
            try {
                const users = await getUserList();
                setUsers(users);

            }catch(error) {
                setError(error);

            }finally{
                setLoading(false);
            }
        }

        getUsers();
    }, [])

    if (loading) {
        return <><h2>Users loading ...</h2></>
    }

    if (error != null) {
        return <><h2>Users are not found</h2></>
    }

    return <>
                <div className="product-page">
            <h1>Users</h1>

            <div className="product-grid">
                {user.map((user) => (
                    <div className="product-card" key={user.id}>
                        <h2>{user.name}</h2>
                        <p>Email: {user.email}</p>
                        <p>Phone: {user.phone}</p>
                    </div>
                ))}
            </div>
        </div>
    </>
}

export default UserList;