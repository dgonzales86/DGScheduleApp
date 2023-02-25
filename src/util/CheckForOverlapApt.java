package util;

import java.time.LocalDateTime;

public interface CheckForOverlapApt {
    boolean checkForOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2);

}
