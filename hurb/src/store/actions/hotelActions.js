
import { SET_ALL_HOTELS } from "./actionTypes";
import fetcher from '../../service/api';

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
      let result = await fetcher.get(search);
      console.log("result from search", result);
      return result;
    } catch(err) {
      console.log("Error while getting hotels from server", err.message);
    } finally {
      dispatch(uiStopLoading());
    }
  };
};