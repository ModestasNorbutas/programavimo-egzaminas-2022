import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Container from "../components/UI/Container";
import Navigation from "../components/UI/Navigation";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";

export default function EditKategorija() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [pavadinimas, setPavadinimas] = useState("");

  let title = "Kategorijos ID: " + id;
  let buttonText = "Atnaujinti duomenis";
  if (id === "new") {
    title = "Nauja kategorija";
    buttonText = "Sukurti kategoriją";
  }

  useEffect(() => {
    setPavadinimas("");
    if (id !== "new") {
      axios
        .get(`${apiEndpoint}/api/kategorija/${id}`)
        .then((response) => {
          setPavadinimas(response.data.pavadinimas);
        })
        .catch((error) => {
          swal({
            text: "Įvyko klaida. " + error.response.data,
            button: "Gerai",
          });
        });
    }
  }, [id]);

  const handleClick = (event) => {
    event.preventDefault();
    const data = {
      id: id === "new" ? null : id,
      pavadinimas: pavadinimas,
    };

    if (id === "new") {
      axios
        .post(`${apiEndpoint}/api/kategorija/`, data)
        .then((response) => {
          swal({
            text: response.data,
            button: "Gerai",
          });
        })
        .then(() => {
          navigate("/home");
        })
        .catch((error) => {
          swal({
            text: "Įvyko klaida. " + error.response.data,
            button: "Gerai",
          });
        });
    } else {
      axios
        .put(`${apiEndpoint}/api/kategorija/`, data)
        .then((response) => {
          swal({
            text: response.data,
            button: "Gerai",
          });
        })
        .then(() => {
          navigate("/home");
        })
        .catch((error) => {
          swal({
            text: "Įvyko klaida. " + error.response.data,
            button: "Gerai",
          });
        });
    }
  };

  return (
    <>
      <Navigation />
      <Container>
        <h3>{title}</h3>
        <form>
          <div className="mb-3">
            <label htmlFor="pavadinimas" className="form-label">
              Pavadinimas
            </label>
            <input
              type="text"
              className="form-control"
              id="pavadinimas"
              name="pavadinimas"
              value={pavadinimas}
              onChange={(e) => setPavadinimas(e.target.value)}
            />
          </div>
          <button
            className="btn btn-primary w-100"
            onClick={(e) => handleClick(e)}
          >
            {buttonText}
          </button>
        </form>
      </Container>
    </>
  );
}
