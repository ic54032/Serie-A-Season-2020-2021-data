import React from 'react';
import {createRoot} from 'react-dom/client';
import './index.css';
import App from './App';
import {Auth0Provider} from "@auth0/auth0-react";

const domain = "dev-bzfzk5wtefjxosau.us.auth0.com";
const clientId = "vj34MlrcNptpRholqdoDMOOZ5Kd2ouSM";

const root = createRoot(document.getElementById('root'));
root.render(
        <Auth0Provider
            domain={domain}
            clientId={clientId}
            authorizationParams={
                {redirect_uri : window.location.origin}
            }
        >
            <App />
        </Auth0Provider>
);

