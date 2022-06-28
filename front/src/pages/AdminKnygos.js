import Container from "../components/UI/Container";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";
import Loading from "../components/UI/Loading";
import Navigation from "../components/UI/Navigation";

export default function AdminKnygos() {
  const navigate = useNavigate();
  const [knygos, setKnygos] = useState();
  const [refresh, setRefresh] = useState(false);
  const kategorijos = useSelector((state) => state.kategorijos.kategorijos);

  useEffect(() => {
    axios
      .get(`${apiEndpoint}/api/knyga/all`)
      .then((response) => {
        setKnygos(response.data);
      })
      .catch((error) => {
        swal({
          text: "Įvyko klaida. " + error.response.data,
          button: "Gerai",
        });
      });
  }, [refresh]);

  const handleTrinti = (id) => {
    axios
      .delete(`${apiEndpoint}/api/knyga/${id}`)
      .then((response) => {
        swal({
          text: response.data,
          button: "Gerai",
        });
      })
      .then(() => {
        setRefresh((prevState) => !prevState);
      })
      .catch((error) => {
        swal({
          text: "Įvyko klaida. " + error.response.data,
          button: "Gerai",
        });
      });
  };

  const handleCreate = () => {
    navigate("/knyga/new");
  };

  const handleRedaguoti = (id) => {
    navigate("/knyga/" + id);
  };

  return (
    <>
      <Navigation />
      <Container>
        {!knygos && <Loading />}
        {knygos && (
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Pavadinimas</th>
                <th>Santrauka</th>
                <th>ISBN</th>
                <th>Puslapių</th>
                <th>Kategorija</th>
                <th>Veiksmai</th>
              </tr>
            </thead>
            <tbody>
              {knygos.map((item) => (
                <tr key={item.id}>
                  <td>{item.id}</td>
                  <td>{item.pavadinimas}</td>
                  <td>{item.santrauka}</td>
                  <td>{item.isbn}</td>
                  <td>{item.puslapiai}</td>
                  <td>
                    {
                      kategorijos.find(
                        (kategorija) => kategorija.id === item.kategorijaId
                      ).pavadinimas
                    }
                  </td>
                  <td>
                    <button
                      className="btn btn-danger me-2"
                      onClick={() => handleTrinti(item.id)}
                    >
                      Trinti
                    </button>
                    <button
                      className="btn btn-secondary me-2"
                      onClick={() => handleRedaguoti(item.id)}
                    >
                      Redaguoti
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
        <button className="btn btn-primary w-100" onClick={handleCreate}>
          Pridėti knygą
        </button>
      </Container>
    </>
  );
}
