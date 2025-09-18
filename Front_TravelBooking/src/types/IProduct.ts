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
}

export interface IProduct {
	id: string;
	name: string;
	description: string;
	categoria: ICategoria;
	ciudad: ICiudad;
	imagen: IImagen[];
}
