import axios from 'axios';

const api = axios.create({
    baseURL: "https://61b9882a38f69a0017ce60b0.mockapi.io/"
});

export default api;