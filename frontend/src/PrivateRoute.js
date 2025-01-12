import React from 'react';
import { useAuth0 } from "@auth0/auth0-react";
import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ element }) => {
    const { isAuthenticated } = useAuth0();
    return isAuthenticated ? element : <Navigate to="/" />;
};
export default PrivateRoute;