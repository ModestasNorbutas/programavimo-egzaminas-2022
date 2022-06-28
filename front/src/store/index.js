import { configureStore } from "@reduxjs/toolkit";
import authSlice from "./auth-slice";
import kategorijosSlice from "./kategorijos-slice";

const store = configureStore({
  reducer: {
    auth: authSlice.reducer,
    kategorijos: kategorijosSlice.reducer,
  },
});

export default store;
