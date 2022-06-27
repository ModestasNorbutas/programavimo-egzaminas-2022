import React, { useState } from "react";
import axios from "axios";
import { useDispatch } from "react-redux";
import { authActions } from "../store/auth-slice";
import { useNavigate } from "react-router-dom";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";

axios.defaults.withCredentials = true;

export default function Login() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const loginInstance = axios.create();

  const initState = {
    username: "",
    password: "",
    loginError: false,
    loggingIn: false,
  };

  const [data, setData] = useState(initState);

  loginInstance.interceptors.response.use(
    (response) => response,
    async (error) => {
      const expectedError =
        error.response &&
        error.response.status >= 400 &&
        error.response.status < 500;
      if (!expectedError) {
        swal("Įvyko klaida, puslapis nurodytu adresu nepasiekiamas");
        dispatch(authActions.error());
        setData({
          ...data,
          loginError: false,
          loggingIn: false,
          username: "",
          password: "",
        });
      } else if (error.response) {
        if (error.response.status === 401) {
          setData({
            ...data,
            loginError: true,
            loggingIn: false,
            username: "",
            password: "",
          });
        } else if (error.response.status === 403) {
          swal("Prieiga uždrausta");
          setData({
            ...data,
            loginError: false,
            loggingIn: false,
            username: "",
            password: "",
          });
        }
      }
    }
  );

  const handleChange = (event) => {
    validateText(event);
    setData({
      ...data,
      loginError: false,
      [event.target.name]: event.target.value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    setData({
      ...data,
      loginError: false,
      loggingIn: true,
    });
    let userData = new URLSearchParams();
    userData.append("username", data.username);
    userData.append("password", data.password);
    loginInstance
      .post(`${apiEndpoint}/login`, userData, {
        headers: { "Content-type": "application/x-www-form-urlencoded" },
      })
      .then((response) => {
        dispatch(authActions.login(response.data));
      })
      .then(() => {
        navigate("/home");
      })
      .catch(() => {});
  };

  const validateText = (event) => {
    const target = event.target;

    if (target.validity.valueMissing && target.id === "username") {
      target.setCustomValidity("Būtina įvesti naudotojo prisijungimo vardą");
    } else if (target.validity.valueMissing && target.id === "password") {
      target.setCustomValidity("Būtina įvesti slaptažodį");
    } else {
      target.setCustomValidity("");
    }
  };

  return (
    <div className="d-flex flex-column justify-content-center align-items-center overflow-hidden min-vh-100">
      <div className="card p-5">
        <form onSubmit={handleSubmit}>
          <h3>Prisijungti</h3>
          <div className="form-group mb-3">
            <label className="form-label" htmlFor="username">
              Naudotojo vardas <span className="fieldRequired">*</span>
            </label>
            <input
              type="text"
              className="form-control"
              name="username"
              id="username"
              value={data.username}
              onChange={handleChange}
              onInvalid={validateText}
              required
              data-toggle="tooltip"
              data-placement="top"
              title="Įveskite naudotojo prisijungimo vardą"
            />
          </div>

          <div className="form-group mb-3">
            <label className="form-label" htmlFor="password">
              Slaptažodis <span className="fieldRequired">*</span>
            </label>
            <input
              type="password"
              className="form-control"
              name="password"
              id="password"
              value={data.password}
              onChange={handleChange}
              onInvalid={validateText}
              required
              data-toggle="tooltip"
              data-placement="top"
              title="Įveskite naudotojo slaptažodį"
            />
          </div>

          <button
            type="submit"
            className="btn btn-primary float-end"
            id="btnLogin"
            disabled={data.loggingIn}
          >
            {data.loggingIn ? "Jungiamasi..." : "Prisijungti"}
          </button>
        </form>
        {data.loginError && (
          <span
            className="alert alert-danger mt-3"
            role="alert"
            id="incorrectLoginData"
          >
            Neteisingas prisijungimo vardas ir/arba slaptažodis!
          </span>
        )}
      </div>
    </div>
  );
}
