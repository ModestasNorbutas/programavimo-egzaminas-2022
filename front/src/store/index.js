import { configureStore } from "@reduxjs/toolkit";
import authSlice from "./auth-slice";
import istaigosSlice from "./istaigos-slice";
import cartSlice from "./cart-slice";
import kategorijosSlice from "./kategorijos-slice";

const store = configureStore({
  reducer: {
    auth: authSlice.reducer,
    istaigos: istaigosSlice.reducer,
    cart: cartSlice.reducer,
    kategorijos: kategorijosSlice.reducer,
  },
});

export default store;
