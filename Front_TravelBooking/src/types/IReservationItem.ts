import type { IProduct } from "./IProduct";

export interface IReservationItem {
	id: number;
	producto: IProduct;
	fechaInicio: string;
	fechaFin: string;
	horaComienzo: string;
}
