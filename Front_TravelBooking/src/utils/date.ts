export const toStartOfDay = (d: Date): Date => {
	const x = new Date(d);
	x.setHours(0, 0, 0, 0);
	return x;
};

export const addDays = (d: Date, days: number): Date => {
	const x = new Date(d);
	x.setDate(x.getDate() + days);
	return x;
};

export const expandReservationsToDates = (
	reservations: { fechaInicio: string; fechaFin: string }[] = []
): Date[] => {
	const out: Date[] = [];
	for (const r of reservations) {
		const start = new Date(`${r.fechaInicio}T00:00:00`);
		const end = new Date(`${r.fechaFin}T00:00:00`);
		for (let cur = start; cur <= end; cur = addDays(cur, 1)) {
			out.push(new Date(cur));
		}
	}
	return out;
};

export const countOverlaps = (
	start?: Date | null,
	end?: Date | null,
	excluded: Date[] = []
): number => {
	if (!start || !end) return 0;
	const s = toStartOfDay(start);
	const e = toStartOfDay(end);
	if (s > e) return 0;
	const excludedMs = new Set(excluded.map((d) => toStartOfDay(d).getTime()));
	let count = 0;
	for (let cur = s; cur <= e; cur = addDays(cur, 1)) {
		if (excludedMs.has(cur.getTime())) count++;
	}
	return count;
};
