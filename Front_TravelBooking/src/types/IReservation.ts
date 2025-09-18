import type { IProduct } from "./IProduct";

export interface IReservation {
  id: string;
  startDate: string;
  endDate: string;
  product: IProduct;
  userId: string;
}
