import { createSlice } from "@reduxjs/toolkit";

const initialAuthState = {
  isAuthenticated: null,
  username: null,
  role: null,
  error: null,
};

const authSlice = createSlice({
  name: "auth",
  initialState: initialAuthState,
  reducers: {
    login(state, action) {
      state.isAuthenticated = true;
      state.username = action.payload.username;
      state.role = action.payload.role;
      state.error = null;
    },
    logout(state, action) {
      state.isAuthenticated = false;
      state.username = null;
      state.role = null;
      state.error = null;
    },
    error(state, action) {
      state.isAuthenticated = false;
      state.username = null;
      state.role = null;
      state.error = action.payload;
    },
  },
});

export const authActions = authSlice.actions;
export default authSlice;
