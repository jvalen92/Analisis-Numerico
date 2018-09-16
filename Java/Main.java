public class Main {
    private static final int N = 100;
    private static final double TOL = 1e-7;

    public static void main(String[] args) {
        UnaVariable.busquedaIncremental(-3, 0.5, N);
        UnaVariable.biseccion(0, 1, TOL, N);
        UnaVariable.reglaFalsa(0, 1, TOL, N);
        UnaVariable.newton(TOL, 0.5, N);
        UnaVariable.puntoFijo(TOL, -0.5, N);
        UnaVariable.secante(TOL, 0.5, 1, N);
        UnaVariable.raicesMultiples(1, TOL, N);
        OptimizacionUnaVariable.steffensen(-0.5, N, TOL);
        OptimizacionUnaVariable.muller(0.5, 1, 1.5, TOL, N);
        SistemaDeEcuaciones.det(new double[][]{{2, -1, 0, 3}, {1, 0.5, 3, 8}, {0, 13, -2, 11}, {14, 5, -2, 3}});
        Graficador.graficar();
    }
}