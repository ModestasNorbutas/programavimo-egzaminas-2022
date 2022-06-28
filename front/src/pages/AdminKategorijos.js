import Container from "../components/UI/Container";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { kategorijosActions } from "../store/kategorijos-slice";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";
import Loading from "../components/UI/Loading";
import Navigation from "../components/UI/Navigation";

export default function AdminKategorijos() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [kategorijos, setKategorijos] = useState();
  const [refresh, setRefresh] = useState(false);

  useEffect(() => {
    axios
      .get(`${apiEndpoint}/api/kategorija/all`)
      .then((response) => {
        setKategorijos(response.data);
        dispatch(kategorijosActions.add(response.data));
      })
      .catch((error) => {
        swal({
          text: "Įvyko klaida. " + error.response.data,
          button: "Gerai",
        });
      });
  }, [refresh, dispatch]);

  const handleTrinti = (id) => {
    axios
      .delete(`${apiEndpoint}/api/kategorija/${id}`)
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
    navigate("/kategorija/new");
  };

  const handleRedaguoti = (id) => {
    navigate("/kategorija/" + id);
  };

  // const handleMeniu = (id) => {
  //   navigate("/patiekalai/" + id);
  // };

  return (
    <>
      <Navigation />
      <Container>
        {!kategorijos && <Loading />}
        {kategorijos && (
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Pavadinimas</th>
                <th>Veiksmai</th>
              </tr>
            </thead>
            <tbody>
              {kategorijos.map((item) => (
                <tr key={item.id}>
                  <td>{item.id}</td>
                  <td>{item.pavadinimas}</td>
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
                    {/* <button
                      className="btn btn-primary"
                      onClick={() => handleMeniu(item.id)}
                    >
                      Patiekalai
                    </button> */}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
        <button className="btn btn-primary w-100" onClick={handleCreate}>
          Pridėti kategoriją
        </button>
      </Container>
    </>
  );
}
