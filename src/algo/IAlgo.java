package algo;

import utils.Landscape;
import utils.Order;

public interface IAlgo {

    /**
     * Compute the Algo
     * @param landscape
     * @return Best solution found
     */
    Order compute(Landscape landscape);
}
