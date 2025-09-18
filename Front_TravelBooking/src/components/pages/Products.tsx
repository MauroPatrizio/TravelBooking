import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

import type { IProduct } from "../../types/IProduct";

const initialProductDetails: IProduct = {
	id: "",
	name: "",
	description: "",
	categoria: { id: "", title: "", description: "", imgUrl: "" },
	ciudad: { id: "", city: "", pais: { id: "", pais: "" } },
	imagen: [],
};

const Products = () => {
	const [producto, setProducto] = useState<IProduct>(initialProductDetails);
	const [error, setError] = useState(false);

	const { id, location } = useParams<{ id: string; location: string }>();

	useEffect(() => {
		if (!id) return;

		sessionStorage.setItem("productId", id);
		if (location) sessionStorage.setItem("productLocation", location);

		axios
			.get<IProduct>(baseUrl + getProductsById + id)
			.then((response) => setProducto(response.data))
			.catch((err) => {
				console.error(`Error: ${err}`);
				setError(true);
			});
	}, [id, location]);

	if (error) {
		return (
			<>
				<Header />
				<ErrorMono />
				<Footer />
			</>
		);
	}

	if (producto === initialProductDetails) {
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
			<ProductsHeader productDetails={producto} />
			<ProductsImg productDetails={producto} />
			<ProductsDescription productDetails={producto} />
			<ProductsFeatures productDetails={producto} />
			<ProductsCalendar productDetails={producto} />
			<ProductsLocation productDetails={producto} />
			<ProductsPolitics />
			<Footer />
		</>
	);
};

export default Products;
