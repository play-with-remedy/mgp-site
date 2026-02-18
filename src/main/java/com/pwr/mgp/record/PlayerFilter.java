package com.pwr.mgp.record;

public record PlayerFilter(
    Long organizationId,
    Long tournamentId
) {
    public PlayerFilter {
        if (organizationId != null && tournamentId != null) {
            throw new IllegalArgumentException(
                    "You cannot specify both organizationId and tournamentId"
            );
        }
    }
}
