export interface MateriaStatsDTO {
    nombreMateria: string;
    totalInscritos: number;
}

export interface DecanoDashboardDTO {
    totalEstudiantesActivos: number;
    totalMateriasOfertadas: number;
    metricasMaterias: MateriaStatsDTO[];
}