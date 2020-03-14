import { createStore, combineReducers } from 'redux'
import { AsyncStorage } from 'react-native'
import { persistStore, persistReducer } from 'redux-persist'

import hotelReducer from './reducer/hotelReducer'

const reducers = combineReducers({hotelRdc: hotelReducer})

const persistConfig = {
    key: 'root',
    storage: AsyncStorage
};

const persistedReducer = persistReducer(persistConfig, reducers);

const store = createStore(persistedReducer)
let persistor = persistStore(store);

export { store, persistor }
