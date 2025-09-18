import { Link } from "react-router-dom";
import type { FC } from "react";

import { useOpenToggleStore } from "../../../../store/openToggleStore";
import styles from "./LoggedUserNav.module.css";

interface IProps {
	handleSessionClose: VoidFunction;
}

const LoggedUserNav: FC<IProps> = ({ handleSessionClose }) => {
	const { openNav } = useOpenToggleStore();

	const userName = localStorage.getItem("userName") || "";
	const userLastname = localStorage.getItem("userLastname") || "";

	const avatar =
		userName && userLastname
			? `${userName.charAt(0).toUpperCase()}${userLastname.charAt(0).toUpperCase()}`
			: "";

	return (
		<div
			className={
				openNav
					? `${styles.user} ${styles.menuItem}`
					: `${styles.userTablet} ${styles.close}`
			}
		>
			<div className={styles.userContainer}>
				<li className={styles.avatar}>{avatar}</li>
				<div className={styles.usernameComponent}>
					<p>Hola,</p>
					<Link to="/perfil">
						<p className={styles.username}>{`${userName} ${userLastname}`}</p>
					</Link>
				</div>
			</div>
			<p className={`${styles.logout} ${openNav ? styles.open : styles.close}`}>
				¿Deseas&nbsp;<li onClick={handleSessionClose}>cerrar sesión</li>?
			</p>
		</div>
	);
};

export default LoggedUserNav;
