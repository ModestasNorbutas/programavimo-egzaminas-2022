import Container from "../components/UI/Container";
import { useSelector, useDispatch } from "react-redux";
import { cartActions } from "../store/cart-slice";
import Loading from "../components/UI/Loading";
import Navigation from "../components/UI/Navigation";

export default function UserCart() {
  const dispatch = useDispatch();
  const krepselis = useSelector((state) => state.cart.cart);

  const handleAddToCart = (patiekalas) => {
    dispatch(cartActions.add(patiekalas));
    // console.log(JSON.stringify(patiekalas));
  };

  const handleRemoveFromCart = (patiekalas) => {
    dispatch(cartActions.remove(patiekalas));
  };

  const handleClearCart = () => {
    dispatch(cartActions.clear());
  };

  return (
    <>
      <Navigation />
      <Container>
        <h3>Pirkinių krepšelis</h3>
        {!krepselis && <Loading />}
        {krepselis && (
          <table className="table">
            <thead>
              <tr>
                <th>Maitinimo įstaiga</th>
                <th>patiekalas</th>
                <th>kiekis</th>
                <th>
                  <button
                    className="btn btn-danger py-0"
                    onClick={() => handleClearCart()}
                  >
                    Ištuštinti
                  </button>
                </th>
              </tr>
            </thead>
            <tbody>
              {Object.values(krepselis).map((patiekalas) => (
                <tr key={patiekalas.id}>
                  <td>{patiekalas.istaigosPavadinimas}</td>
                  <td>{patiekalas.pavadinimas}</td>
                  <td>{patiekalas.kiekis}</td>
                  <td>
                    <button
                      className="btn btn-success me-2"
                      onClick={() => handleAddToCart(patiekalas)}
                    >
                      +
                    </button>
                    <button
                      className="btn btn-danger px-3"
                      onClick={() => handleRemoveFromCart(patiekalas)}
                    >
                      -
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
