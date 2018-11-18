import axios from 'axios';

let hurbApiUrl = (location) => 
    `https://search.hotelurbano.com/api?q=${location}`

export const getHotels = (location = '') => { 
    return new Promise ((resolve, reject) => {
        axios.get(hurbApiUrl(location)).then(({data}) => {
            console.log(data);
            resolve(data);
        }).catch(err => {
            reject(err);
        })
    });
}