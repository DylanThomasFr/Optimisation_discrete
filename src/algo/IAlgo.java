package algo;

import algo.mapping.IMapping;
import utils.Landscape;
import utils.Order;

public interface IAlgo {

    /**
     * Compute the Algo
     * @param landscape
     * @return Best solution found
     */
    Order compute(Landscape landscape);

    int hashCode();

    boolean equals(Object obj);
}
