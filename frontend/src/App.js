import React from 'react';
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import Datatable from './Datatable';
import { useAuth0 } from "@auth0/auth0-react";
import './App.css';
import Profile from './Profile';
import PrivateRoute from "./PrivateRoute";

const LoginButton = () => {
    const { loginWithRedirect } = useAuth0();
    return <button onClick={() => loginWithRedirect()}>Prijava</button>;
};

const LogoutButton = () => {
    const { logout } = useAuth0();
    return <button onClick={() => logout({logoutParams:{ returnTo: window.location.origin }})}>Odjava</button>;
};

const RefreshDataButton = () => {
    const handleRefresh = async () => {
        // Call your backend API to export data to CSV and JSON
        await fetch('http://localhost:8080/teams/refresh', { method: 'POST' });
        alert('Data refreshed successfully');
    };

    return <button onClick={handleRefresh}>Osvježi preslike</button>;
};


function App() {
    const { isAuthenticated } = useAuth0();
    return (
        <Router>
            <div className="App">
                <div style={{display: 'flex',justifyContent: "space-evenly"}}>
                    <Link to="/datatable">Go to Data Table</Link>
                    {!isAuthenticated && <LoginButton />}
                    {isAuthenticated && <LogoutButton />}
                    {isAuthenticated && <Link to="/profile">Korisnički profil</Link> }
                    {isAuthenticated && <RefreshDataButton />}
                </div>
                <Routes>
                    <Route path="/datatable" element={<Datatable />} />
                    <Route path="/profile"element={<PrivateRoute element={<Profile />} />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;