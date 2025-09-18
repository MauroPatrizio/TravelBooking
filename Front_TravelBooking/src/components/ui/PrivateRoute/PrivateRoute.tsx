import { Outlet, Navigate } from "react-router-dom";
import { useIsLoggedStore } from "../../../store/isLoggedStore";

const PrivateRoute = () => {
	const { isLogged, setFlag } = useIsLoggedStore();

	setFlag(!isLogged);

	return isLogged ? (
		<Outlet />
	) : (
		<Navigate
			to="/login"
			replace
		/>
	);
};

export default PrivateRoute;
