import { Link, useNavigate } from "react-router-dom";
import { imgBars } from "../../styleAux/fontAwesoneIcon";
import LoggedUserNav from "./LoggedUserNav";
import SocialNet from "../SocialNet";
import SweetAlert from "../../helpers/SweetAlert";

import { useOpenToggleStore } from "../../../../store/openToggleStore";
import { useIsLoggedStore } from "../../../../store/isLoggedStore";
import { useFilterStore } from "../../../../store/filterStore";

import styles from "./NavBar.module.css";

const NavBar = () => {
	const { openNav, handleToggle } = useOpenToggleStore();
	const { isLogged, setIsLogged } = useIsLoggedStore();
	const { setFavoritos } = useFilterStore();
	const navigate = useNavigate();

	const handleSessionClose: VoidFunction = () => {
		SweetAlert.messageCloseSession("¿Estás seguro que quieres cerrar sesión?", () => {
			setIsLogged(false);
			localStorage.removeItem("usuario");
			localStorage.removeItem("login");
			localStorage.removeItem("userToken");
			sessionStorage.removeItem("productId");
			sessionStorage.removeItem("productLocation");
			localStorage.removeItem("userName");
			localStorage.removeItem("userLastname");
			localStorage.removeItem("userEmail");
			localStorage.removeItem("userId");
			setFavoritos([]);
			navigate("/", { replace: true });
		});
	};

	const handleModalMenu: React.MouseEventHandler<HTMLUListElement> = (e) => {
		e.stopPropagation();
	};

	return (
		<nav
			onClick={handleToggle}
			className={openNav ? styles.modalContainer : ""}
		>
			<ul
				onClick={handleModalMenu}
				className={`${styles.menu} ${openNav ? styles.modalOpen : ""}`}
			>
				<div className={openNav ? styles.menuFirst : ""}>
					{openNav ? (
						<li
							className={styles.modalClose}
							onClick={handleToggle}
						>
							X
						</li>
					) : (
						<li
							className={styles.toggle}
							onClick={handleToggle}
						>
							{imgBars}
						</li>
					)}
					{isLogged ? (
						<LoggedUserNav handleSessionClose={handleSessionClose} />
					) : (
						<li
							className={openNav ? `${styles.open} ${styles.menuItem}` : styles.close}
						>
							Menú
						</li>
					)}
				</div>

				{!isLogged && (
					<>
						{window.location.pathname === "/signup" ? null : (
							<Link
								className={`${styles.item} ${openNav ? styles.open : styles.close}`}
								to="/signup"
							>
								<li>Crear cuenta</li>
							</Link>
						)}
						{window.location.pathname === "/login" ? null : (
							<Link
								className={`${styles.item} ${openNav ? styles.open : styles.close}`}
								to="/login"
							>
								<li>Iniciar sesión</li>
							</Link>
						)}
					</>
				)}

				{openNav && (
					<div className={styles.socialNetMB}>
						<SocialNet />
					</div>
				)}
			</ul>
		</nav>
	);
};

export default NavBar;
