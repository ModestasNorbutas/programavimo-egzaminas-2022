import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  kategorijos: [],
};

const kategorijosSlice = createSlice({
  name: "kategorijos",
  initialState: initialState,
  reducers: {
    add(state, action) {
      state.kategorijos = action.payload;
    },
  },
});

export const kategorijosActions = kategorijosSlice.actions;
export default kategorijosSlice;
