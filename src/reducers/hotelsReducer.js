
import * as constants from '../constants/hotels';

const hotelsReducer = (state = [], action) => {
    switch (action.type) {
        case constants.FETCH_HOTELS_SUCCESS: 
            return action.hotels;
        default:
            return state;
    }
};

export default hotelsReducer;