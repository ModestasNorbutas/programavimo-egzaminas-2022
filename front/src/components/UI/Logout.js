import React from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { authActions } from "../../store/auth-slice";
import apiEndpoint from "../Services/apiEndpoint";
import axios from "axios";
import swal from "sweetalert";

export default function Logout() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogout = () => {
    axios
      .post(`${apiEndpoint}/logout`)
      .then(() => {
        dispatch(authActions.logout());
        navigate("/");
      })
      .catch(() => {
        swal({
          text: "Įvyko klaida",
          button: "Gerai",
        });
      });
  };

  return (
    <div>
      <button className="btn btn-primary" onClick={handleLogout}>
        Atsijungti
      </button>
    </div>
  );
}
