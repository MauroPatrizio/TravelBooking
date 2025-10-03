import type { IReservation } from "./IReservation";

export interface ICategoria {
	id: string;
	title: string;
	description: string;
	imgUrl: string;
}

export interface IPais {
	id: string;
	pais: string;
}

export interface ICiudad {
	id: string;
	city: string;
	pais: IPais;
}

export interface IImagen {
	id: string;
	title: string;
	url: string;
	alt?: string;
}

export interface ICaracteristica {
	airConditioning: boolean;
	freeParking: boolean;
	kitchen: boolean;
	petsAllowed: boolean;
	pool: boolean;
	tv: boolean;
	wifi: boolean;
}

export interface IProduct {
	id: string;
	name: string;
	description: string;
	categoria: ICategoria;
	ciudad: ICiudad;
	imagen: IImagen[];
	caracteristica: ICaracteristica;
	reservas?: IReservation[];
	latitud?: number | string;
	longitud?: number | string;
	domicilio?: string;
}
