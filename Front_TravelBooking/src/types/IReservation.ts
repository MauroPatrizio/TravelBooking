import type { IProduct } from "./IProduct";

export interface IReservation {
	id: string;
	fechaInicio: string;
	fechaFin: string;
	horaComienzo?: string;
	producto: IProduct;
	usuario?: { id: string };
}
