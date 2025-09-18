import { Navigate, Route, Routes } from "react-router-dom";
import { Home } from "../components/pages/Home";

export default function AppRouter() {
	return (
		<Routes>
			<Route
				path="/"
				element={<Navigate to="home" />}
			/>
			<Route
				path="/home"
				element={<Home />}
			/>
		</Routes>
	);
}
