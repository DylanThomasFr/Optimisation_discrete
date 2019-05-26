package algo;

import utils.Board;
import utils.Order;

public interface IAlgo {

    /**
     * Compute the Algo
     * @param board
     * @return Best solution found
     */
    Order compute(Board board);
}
