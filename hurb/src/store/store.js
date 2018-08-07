import {
  createStore,
  combineReducers,
  compose,
  applyMiddleware
} from "redux";

import thunk from 'redux-thunk'

import uiReducer from "./reducers/uiReducer";
import hotelReducer from "./reducers/hotelReducer";

const rootReducer = combineReducers({
  hotel: hotelReducer,
  ui: uiReducer,
});

const configStore = () => {
  return createStore(rootReducer, compose(applyMiddleware(thunk)));
};

export default configStore;