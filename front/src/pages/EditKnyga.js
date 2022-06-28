import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import Container from "../components/UI/Container";
import Navigation from "../components/UI/Navigation";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";

export default function EditKnyga() {
  const { id } = useParams();
  const navigate = useNavigate();
  const kategorijos = useSelector((state) => state.kategorijos.kategorijos);

  const [pavadinimas, setPavadinimas] = useState("");
  const [santrauka, setSantrauka] = useState("");
  const [isbn, setIsbn] = useState("");
  const [puslapiai, setPuslapiai] = useState("");
  const [kategorijaId, setKategorijaId] = useState("");

  let title = "Knygos ID: " + id;
  let buttonText = "Atnaujinti duomenis";
  if (id === "new") {
    title = "Nauja knyga";
    buttonText = "Sukurti knygą";
  }

  useEffect(() => {
    setPavadinimas("");
    if (id !== "new") {
      axios
        .get(`${apiEndpoint}/api/knyga/${id}`)
        .then((response) => {
          setPavadinimas(response.data.pavadinimas);
          setSantrauka(response.data.santrauka);
          setIsbn(response.data.isbn);
          setPuslapiai(response.data.puslapiai);
          setKategorijaId(response.data.kategorijaId);
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
      santrauka: santrauka,
      isbn: isbn,
      nuotrauka: null,
      puslapiai: parseInt(puslapiai),
      kategorijaId: kategorijaId,
    };

    if (id === "new") {
      axios
        .post(`${apiEndpoint}/api/knyga/`, data)
        .then((response) => {
          swal({
            text: response.data,
            button: "Gerai",
          });
        })
        .then(() => {
          navigate("/knygos");
        })
        .catch((error) => {
          swal({
            text: "Įvyko klaida. " + error.response.data,
            button: "Gerai",
          });
        });
    } else {
      axios
        .put(`${apiEndpoint}/api/knyga/`, data)
        .then((response) => {
          swal({
            text: response.data,
            button: "Gerai",
          });
        })
        .then(() => {
          navigate("/knygos");
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
          <div className="mb-3">
            <label htmlFor="santrauka" className="form-label">
              Santrauka
            </label>
            <textarea
              className="form-control"
              id="santrauka"
              name="santrauka"
              value={santrauka}
              onChange={(e) => setSantrauka(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="isbn" className="form-label">
              ISBN
            </label>
            <input
              type="text"
              className="form-control"
              id="isbn"
              name="isbn"
              value={isbn}
              onChange={(e) => setIsbn(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="puslapiai" className="form-label">
              Puslapių kiekis
            </label>
            <input
              type="number"
              className="form-control"
              id="puslapiai"
              name="puslapiai"
              value={puslapiai}
              onChange={(e) => setPuslapiai(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="kategorija" className="form-label">
              Kategorija
            </label>
            <select
              className="form-control"
              id="kategorija"
              name="kategorija"
              value={kategorijaId}
              onChange={(e) => setKategorijaId(e.target.value)}
            >
              <option value="" disabled selected>
                Pasirinkite kategoriją
              </option>
              {kategorijos.map((kategorija) => (
                <option value={kategorija.id} key={kategorija.id}>
                  {kategorija.pavadinimas}
                </option>
              ))}
            </select>
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
