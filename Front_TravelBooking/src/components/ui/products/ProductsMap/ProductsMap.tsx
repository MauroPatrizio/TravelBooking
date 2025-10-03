import type { FC } from "react";
import { useState, memo } from "react";
import { GoogleMap, InfoWindow, LoadScript, Marker } from "@react-google-maps/api";
import styles from "./ProductsMap.module.css";
import {
	imgWifi,
	imgAirCond,
	imgParking,
	imgKitchen,
	imgPet,
	imgPool,
	imgTv,
} from "../../../../styleAux/fontAwesomeIcons";
import type { IProduct } from "../../../../types/IProduct";

interface IProps extends IProduct {
	latitud?: string | number;
	longitud?: string | number;
	domicilio?: string;
}

const ProductsMap: FC<IProps> = (product) => {
	const lat =
		typeof product.latitud === "string" ? parseFloat(product.latitud) : Number(product.latitud);
	const lng =
		typeof product.longitud === "string"
			? parseFloat(product.longitud)
			: Number(product.longitud);
	const center = { lat, lng };

	const [isOpen, setIsOpen] = useState<boolean>(false);

	const handleMarkerClick: VoidFunction = () => setIsOpen(true);
	const handleClose: VoidFunction = () => setIsOpen(false);

	const apiKey = (import.meta as any).env?.VITE_GOOGLE_MAPS_API_KEY ?? "";

	return (
		<LoadScript googleMapsApiKey={apiKey}>
			<GoogleMap
				id="marker-example"
				mapContainerClassName={styles.mapContainer}
				zoom={18}
				center={center}
			>
				<Marker
					position={center}
					onClick={handleMarkerClick}
				>
					{isOpen && (
						<InfoWindow
							onCloseClick={handleClose}
							position={center}
						>
							<div className={styles.info}>
								<h3 className={styles.title}>{product.name}</h3>
								<div className={styles.icons}>
									{product.caracteristica.airConditioning && imgAirCond}
									{product.caracteristica.freeParking && imgParking}
									{product.caracteristica.kitchen && imgKitchen}
									{product.caracteristica.petsAllowed && imgPet}
									{product.caracteristica.pool && imgPool}
									{product.caracteristica.tv && imgTv}
									{product.caracteristica.wifi && imgWifi}
								</div>
								<p className={styles.addr}>{product.domicilio}</p>
								<p className={styles.addr}>
									{product.ciudad.city}, {product.ciudad.pais.pais}
								</p>
							</div>
						</InfoWindow>
					)}
				</Marker>
			</GoogleMap>
		</LoadScript>
	);
};

export default memo(ProductsMap);
