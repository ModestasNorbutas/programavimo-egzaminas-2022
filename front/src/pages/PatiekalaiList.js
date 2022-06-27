import Container from "../components/UI/Container";
import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";
import Loading from "../components/UI/Loading";
import Navigation from "../components/UI/Navigation";

export default function PatiekalaiList() {
  const { istaigaID } = useParams();

  const istaigosPavadinimas = useSelector(
    (state) => state.istaigos.istaigos[istaigaID]
  );

  const navigate = useNavigate();
  const [patiekalai, setPatiekalai] = useState();
  const [refresh, setRefresh] = useState(false);

  const handleCreate = () => {
    navigate(`/patiekalas/${istaigaID}/new`);
  };

  useEffect(() => {
    axios
      .get(`${apiEndpoint}/api/patiekalas/all/${istaigaID}`)
      .then((response) => {
        setPatiekalai(response.data);
      })
      .catch((error) => {
        swal({
          text: "Įvyko klaida. " + error.response.data,
          button: "Gerai",
        });
      });
  }, [refresh, istaigaID]);

  const handleTrinti = (id) => {
    axios
      .delete(`${apiEndpoint}/api/patiekalas/${id}`)
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

  const handleRedaguoti = (id) => {
    navigate(`/patiekalas/${istaigaID}/${id}`);
  };

  return (
    <>
      <Navigation />
      <Container>
        <h3>{istaigosPavadinimas}</h3>
        {!patiekalai && <Loading />}
        {patiekalai && (
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Pavadinimas</th>
                <th>Aprašymas</th>
                <th>Veiksmai</th>
              </tr>
            </thead>
            <tbody>
              {patiekalai.map((patiekalas) => (
                <tr key={patiekalas.id}>
                  <td>{patiekalas.id}</td>
                  <td>{patiekalas.pavadinimas}</td>
                  <td>{patiekalas.aprasymas}</td>
                  <td>
                    <button
                      className="btn btn-danger me-2"
                      onClick={() => handleTrinti(patiekalas.id)}
                    >
                      Trinti
                    </button>
                    <button
                      className="btn btn-secondary me-2"
                      onClick={() => handleRedaguoti(patiekalas.id)}
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
          Pridėti patiekalą
        </button>
      </Container>
    </>
  );
}
