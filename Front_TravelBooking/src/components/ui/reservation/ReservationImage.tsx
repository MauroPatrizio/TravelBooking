import type { FC } from "react";
import type { IProduct } from "../../../types/IProduct";

interface IProps {
	product: IProduct;
}

const ReservationImage: FC<IProps> = ({ product }) => {
	return (
		<div className="reservationD__container__card__img">
			{product.imagen.map(
				(ele) =>
					ele.title === "principal" && (
						<img
							key={ele.id.toString()}
							src={ele.url}
							alt={ele.alt ?? "img"}
						/>
					)
			)}
		</div>
	);
};

export default ReservationImage;
