
import { SET_ALL_HOTELS } from "./actionTypes";
import { fetcher } from '../../service/api';

import { uiStartLoading, uiStopLoading } from './uiActions'

export const setHotels = hotels => {
  return {
    type: SET_ALL_HOTELS,
    hotels,
  };
};

export const fetchHotels = (search = "") => {
  return async (dispatch) => {
    dispatch(uiStartLoading());
    try {
      let { results } = await fetcher.get(search);
      return results.map(hotel => {
        return {
          name: hotel.name,
          image: hotel.image,
          price: hotel.price.current_price,
          city: hotel.address.city,
          state: hotel.address.state,
          amenities: hotel.amenities.slice(0, 3),
          stars: hotel.stars || null,
        }
      });
    } catch(err) {
      console.log("Error while getting hotels from server", err.message);
    } finally {
      dispatch(uiStopLoading());
    }
  };
};