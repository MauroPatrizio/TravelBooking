import Search from "./searchBar/Search";
import Categories from "../Categories/Categories";
import ListImageCard from "../ListImageCard/ListImageCard";
import ProductsByPagination from "./productsByPagination/ProductsByPagination";
import CleanerButton from "../CleanerButton/CleanerButton";

const HomeContent = () => {
	return (
		<>
			<Search />
			<Categories />
			<CleanerButton />
			<ListImageCard />
			<ProductsByPagination />
		</>
	);
};

export default HomeContent;
