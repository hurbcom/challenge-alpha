import axios from 'axios';

const api = axios.create({
    baseURL: `https://www.hurb.com/search/api`,
});

export default api;
