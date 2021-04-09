import axios from 'axios';

export default axios.create({
    baseURL: 'http://localhost:8081/api',
    // baseURL: 'http://i3c201.p.ssafy.io/api',
    headers: {
        'Content-type': 'application/json'
    }
});