import React, { useState, useEffect } from 'react';
import { Table, Button, Form, Container, Row, Col, Pagination } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Datatable.css';

function Datatable() {
    const [jsonData, setJsonData] = useState([]);
    const [csvData, setCsvData] = useState([]);
    const [filteredJSONData, setFilteredJSONData] = useState([]);
    const [filteredCSVData, setFilteredCSVData] = useState([]);
    const [searchText, setSearchText] = useState('');
    const [selectedAttribute, setSelectedAttribute] = useState('all');
    const [columns, setColumns] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [itemsPerPage] = useState(8);

    useEffect(() => {
        const fetchJSONData = async () => {
            try {
                const response =
                    await fetch('http://localhost:8080/teams/json');
                const jsonData = await response.json();
                const dataArray = Object.keys(jsonData).map(key => {
                    return { id: key, ...jsonData[key] };
                });
                if (Array.isArray(dataArray)) {
                    setJsonData(dataArray);
                    setFilteredJSONData(dataArray);
                    if (dataArray.length > 0) {
                        setColumns(Object.keys(dataArray[0]));
                    }
                } else {
                    console.error('Fetched JSON data is not an array');
                }
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        const fetchCSVData = async () => {
            try {
                const response =
                    await fetch('http://localhost:8080/teams/csv');
                const csvText = await response.text();
                const rows = csvText.split('\n').map(row => row.split(','));
                const headers = rows[0].filter(header => header !== 'id');
                const csvData = rows.slice(1).map(row => {
                    let obj = {};
                    row.forEach((val, index) => {
                        obj[headers[index]] = val;
                    });
                    return obj;
                });
                setCsvData(csvData);
                setFilteredCSVData(csvData);
                setColumns(headers);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchJSONData();
        fetchCSVData();
    }, []);

    const handleSearch = (text, attribute) => {
        if (!text.trim()) {
            setFilteredJSONData(jsonData);
            setFilteredCSVData(csvData);
            return;
        }

        const filterData = (data) => {
            return data.filter(item => {
                if (attribute === 'all') {
                    return Object.values(item).some(value => {
                        if (typeof value === 'number') {
                            return value === Number(text);
                        }
                        return value && value.toString().toLowerCase().includes(text.toLowerCase());
                    });
                }
                if (typeof item[attribute] === 'number') {
                    return item[attribute] === Number(text);
                }
                return item[attribute] && item[attribute].toString().toLowerCase().includes(text.toLowerCase());
            });
        };

        setFilteredJSONData(filterData(jsonData));
        setFilteredCSVData(filterData(csvData));
    };

    const downloadCSV = () => {
        const headers = columns.join(',');
        const rows = filteredCSVData.map(item =>
            columns.map(col => item[col]).join(',')
        ).join('\n');
        const csvContent = `${headers}\n${rows}`;

        const blob = new Blob([csvContent], { type: 'text/csv' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'data.csv';
        a.click();
        window.URL.revokeObjectURL(url);
    };

    const downloadJSON = () => {
        const jsonString = JSON.stringify(filteredJSONData, null, 2);
        const blob = new Blob([jsonString], { type: 'application/json' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'data.json';
        a.click();
        window.URL.revokeObjectURL(url);
    };

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    const indexOfLastItem = currentPage * (itemsPerPage);
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = filteredCSVData.slice(indexOfFirstItem, indexOfLastItem);

    const renderPagination = () => {
        const pageNumbers = [];
        for (let i = 1; i <= Math.floor(filteredCSVData.length / itemsPerPage); i++) {
            pageNumbers.push(i);
        }

        return (
            <Pagination>
                {pageNumbers.map(number => (
                    <Pagination.Item key={number} active={number === currentPage} onClick={() => handlePageChange(number)}>
                        {number}
                    </Pagination.Item>
                ))}
            </Pagination>
        );
    };

    return (
        <Container className="p-4 max-w-6xl mx-auto">
            <div className="mb-6">
                <h1 className="text-2xl font-bold mb-4">Data Visibility Dashboard</h1>

                <Row className="mb-6">
                    <Col>
                        <Button onClick={downloadCSV} variant="primary">Download filtered CSV</Button>
                    </Col>
                    <Col>
                        <Button onClick={downloadJSON} variant="primary">Download filtered JSON</Button>
                    </Col>
                </Row>

                <Row className="mb-6">
                    <Col>
                        <Form.Control
                            type="text"
                            placeholder="Search..."
                            value={searchText}
                            onChange={(e) => {
                                setSearchText(e.target.value);
                                handleSearch(e.target.value, selectedAttribute);
                            }}
                        />
                    </Col>
                    <Col>
                        <Form.Control
                            as="select"
                            value={selectedAttribute}
                            onChange={(e) => {
                                setSelectedAttribute(e.target.value);
                                handleSearch(searchText, e.target.value);
                            }}
                        >
                            <option value="all">All Fields</option>
                            {columns.map(column => (
                                <option key={column} value={column}>
                                    {column}
                                </option>
                            ))}
                        </Form.Control>
                    </Col>
                </Row>

                <Table striped bordered hover className="custom-table">
                    <thead>
                    <tr>
                        {columns.map(column => (
                            <th key={column}>{column}</th>
                        ))}
                    </tr>
                    </thead>
                    <tbody>
                    {currentItems.map((item, index) => (
                        <tr key={index}>
                            {columns.map(column => (
                                <td key={column}>{item[column]}</td>
                            ))}
                        </tr>
                    ))}
                    </tbody>
                </Table>
                {renderPagination()}
            </div>
        </Container>
    );
}

export default Datatable;