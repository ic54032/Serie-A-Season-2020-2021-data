import React from 'react';
import { useAuth0 } from "@auth0/auth0-react";

const Profile = () => {
    const { user, isAuthenticated, isLoading } = useAuth0();

    if(isLoading) {
        return <div>Loading...</div>;
    }

    return (
        isAuthenticated && (
            <div>
                <h2>Profil</h2>
                <img src={user.picture} alt="Profile"/>
                <p>Name: {user.name}</p>
                <p>Email: {user.email}</p>
                <p>Nickname: {user.nickname}</p>
            </div>
        )
    );
};

export default Profile;