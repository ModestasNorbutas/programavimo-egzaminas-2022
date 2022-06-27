import Container from "../components/UI/Container";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { cartActions } from "../store/cart-slice";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";
import Loading from "../components/UI/Loading";
import Navigation from "../components/UI/Navigation";

export default function PatiekalaiListUser() {
  const { istaigaID } = useParams();
  const dispatch = useDispatch();

  const istaigosPavadinimas = useSelector(
    (state) => state.istaigos.istaigos[istaigaID]
  );

  const [patiekalai, setPatiekalai] = useState();

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
  }, [istaigaID]);

  const handleAddToCart = (patiekalas) => {
    dispatch(cartActions.add(patiekalas));
    // console.log(JSON.stringify(patiekalas));
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
                <th>Pavadinimas</th>
                <th>Aprašymas</th>
                <th>Veiksmai</th>
              </tr>
            </thead>
            <tbody>
              {patiekalai.map((patiekalas) => (
                <tr key={patiekalas.id}>
                  <td>{patiekalas.pavadinimas}</td>
                  <td>{patiekalas.aprasymas}</td>
                  <td>
                    <button
                      className="btn btn-primary me-2"
                      onClick={() => handleAddToCart(patiekalas)}
                    >
                      Į krepšelį
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </Container>
    </>
  );
}
