import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

import type { IProduct } from "../../types/IProduct"; // 👈 type import

const initialProductDetails: IProduct = {
	id: "",
	name: "",
	description: "",
	categoria: { id: "", title: "", description: "", imgUrl: "" },
	ciudad: { id: "", city: "", pais: { id: "", pais: "" } },
	imagen: [],
};

const Reservation = () => {
	const [product, setProduct] = useState<IProduct>(initialProductDetails);
	const [error, setError] = useState(false);

	const { id } = useParams<{ id: string }>();

	useEffect(() => {
		if (!id) return;

		axios
			.get<IProduct>(baseUrl + getProductsById + id)
			.then((response) => setProduct(response.data))
			.catch((err) => {
				console.error(`Error: ${err}`);
				setError(true);
			});
	}, [id]);

	if (error) {
		return (
			<>
				<Header />
				<ErrorMono />
				<Footer />
			</>
		);
	}

	if (product === initialProductDetails) {
		return (
			<>
				<Header />
				<Loader />
				<Footer />
			</>
		);
	}

	return (
		<>
			<Header />
			<ProductsHeaderTop
				productDetails={product}
				linkProps={`/alojamientos/${product.id}/${product.name.replace(/[+ ]|%20/g, "-")}`}
			/>
			<ReservationDetails productDetails={product} />
			<ProductsPolitics />
			<Footer />
		</>
	);
};

export default Reservation;
