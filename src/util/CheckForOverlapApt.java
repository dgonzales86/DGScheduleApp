package util;

import java.time.LocalDateTime;

/**
 * CheckForOverlapApt Interface
 */
public interface CheckForOverlapApt {
    /**
     * Interface method used to implement methods used to check for overlapping times.
     * @param start1 - first start time
     * @param end1 - first end time
     * @param start2 - second start time
     * @param end2 - second end time
     * @return - returns boolean value
     */
    boolean checkForOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2);

}
