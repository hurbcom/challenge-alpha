import axios from 'axios';

let hurbApiUrl = (location) => 
    `https://search.hotelurbano.com/api?q=Rio%20de%20Janeiro`

export const getHotels = (location = '') => { 
    return new Promise ((resolve, reject) => {
        axios.get(hurbApiUrl(location), (err, response, body) => {
            if(err){
                reject(err);
            }
            resolve(JSON.parse(body));
        });
    });
}