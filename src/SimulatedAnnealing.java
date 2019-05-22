public class SimulatedAnnealing {

    /**
     * Algorithme du recuit simulé
     * @param s0
     * @param i0
     */
    public void simulate(int s0, int i0){
        int s = s0;
        int g = s0;
        int fmin = E(s);
        int k = 0;
        while(k < kmax && e > emax){
            sn := voisin(s)
            en := E(sn)
            si en < e ou aléatoire() < P(en - e, temp(k/kmax)) alors
            s := sn; e := en
            si e < m alors
            g := s; m := e;
            k := k + 1
            retourne g
        }
    }

    /**
     * Calcul de la fitness
     * @return
     */
    public int fitness(){

    }
}
