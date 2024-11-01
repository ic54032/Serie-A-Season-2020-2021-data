import React from 'react';
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import Datatable from './Datatable';
import './App.css';

function App() {
    return (
        <Router>
            <div className="App">
                <nav>
                    <Link to="/datatable">Go to Data Table</Link>
                </nav>
                <Routes>
                    <Route path="/datatable" element={<Datatable />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;