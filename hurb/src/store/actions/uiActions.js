
import { START_LOADING, STOP_LOADING } from "./actionTypes";

export const uiStartLoading = () => {
  return {
    type: START_LOADING,
  };
};

export const uiStopLoading = () => {
  return {
    type: STOP_LOADING,
  };
};